package org.zxb.plun;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


// ExamplePlugin.java
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class})})
public class ExamplePlugin implements Interceptor {

    private ThreadLocal threadLocal = new ThreadLocal();

    private Properties properties = new Properties();

    public Object intercept(Invocation invocation) throws Throwable {
        // 取出被拦截的对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        // 如果是代理类直接跳过
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        if (!metaObject.hasGetter("h")) {
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            if (checkSelect(sql)) {
                Object obj = threadLocal.get();
                if (obj != null) {
                    Page page = (Page) obj;
                    getTotal(invocation, boundSql, page);
                    changeSql(boundSql, page);
                }
            }
        }
        // implement pre processing if need
        Object returnObject = invocation.proceed();
        // implement post processing if need
        return returnObject;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public ThreadLocal getThreadLocal() {
        return threadLocal;
    }

    /**
     * 判断是否是select语句
     *
     * @param sql
     * @return
     */
    private boolean checkSelect(String sql) {
        return sql.trim().toLowerCase().indexOf("select") == 0;
    }


    /**
     * 获取数据总数
     *
     * @param invocation
     * @param boundSql
     * @param page
     * @return
     */
    private void getTotal(Invocation invocation, BoundSql boundSql, Page page) {
        MetaObject metaObject = SystemMetaObject.forObject(invocation.getTarget());
        // 获取当前的mappedStatement对象
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 获取配置对象
        Configuration configuration = mappedStatement.getConfiguration();
        // 获取当前需要执行的sql
        String sql = boundSql.getSql();
        // 改写sql语句，实现返回数据总数 $_paging取名是为了防止数据库表重名
        String countSql = "select count(1) as total from (" + sql + ") total_talbe";
        // 获取拦截方法参数，拦截的是connection对象
        Connection connection = (Connection) invocation.getArgs()[0];
        PreparedStatement pstmt = null;
        int total = 0;

        try {
            // 预编译查询数据总数的sql语句
            pstmt = connection.prepareStatement(countSql);
            // 构建boundSql对象
            BoundSql countBoundSql = new BoundSql(configuration, countSql, boundSql.getParameterMappings(),
                    boundSql.getParameterObject());
            // 构建parameterHandler用于设置sql参数
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(),
                    countBoundSql);
            // 设置sql参数
            parameterHandler.setParameters(pstmt);
            //执行查询
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 设置总数据数
        page.setTotal(total);
    }


    /**
     * 修改当前查询的sql
     * @param boundSql
     * @param page
     * @return
     */
    private void changeSql(BoundSql boundSql, Page page) throws Exception {
        // 获取当前查询的sql
        String sql = boundSql.getSql();
        // 反射修改sql
        MetaObject metaStmtHandler = SystemMetaObject.forObject(boundSql);
        String newSql = sql+" limit "+ page.getPageNum() + "," + page.getEverypageNum();
        // 设置当前sql为修改后的sql
        metaStmtHandler.setValue("sql", newSql);
    }

}
