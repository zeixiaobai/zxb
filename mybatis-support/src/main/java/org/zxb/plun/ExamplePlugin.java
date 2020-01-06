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
        args = {Connection.class})})
public class ExamplePlugin implements Interceptor {

    private ThreadLocal threadLocal = new ThreadLocal();

    private Properties properties = new Properties();

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = getUnProxyObject(invocation);
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        String sql = getSql(metaObject);
        if (!checkSelect(sql)) {
            // 不是select语句，进入责任链下一层
            return invocation.proceed();
        }

        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        Object parameterObject = boundSql.getParameterObject();

        String page = null;
        if (page == null) {
            // 没有传入page对象，不执行分页处理，进入责任链下一层
            return invocation.proceed();
        }

        return changeSql(invocation, metaObject, boundSql);
        // implement pre processing if need
//        Object returnObject = invocation.proceed();
        // implement post processing if need
//        return returnObject;
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
     * 从代理对象中分离出真实对象
     * @param invocation
     * @return
     */
    private StatementHandler getUnProxyObject(Invocation invocation) {
        // 取出被拦截的对象
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStmtHandler = SystemMetaObject.forObject(statementHandler);
        Object object = null;
        // 分离代理对象
        while (metaStmtHandler.hasGetter("h")) {
            object = metaStmtHandler.getValue("h");
            metaStmtHandler = SystemMetaObject.forObject(object);
        }
        return object == null ? statementHandler : (StatementHandler) object;
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
     * @param metaObject
     * @param boundSql
     * @return
     */
    private int getTotal(Invocation invocation, MetaObject metaObject, BoundSql boundSql) {
        // 获取当前的mappedStatement对象
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 获取配置对象
        Configuration configuration = mappedStatement.getConfiguration();
        // 获取当前需要执行的sql
        String sql = getSql(metaObject);
        // 改写sql语句，实现返回数据总数 $_paging取名是为了防止数据库表重名
        String countSql = "select count(1) as total from (" + sql + ") $_paging";
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

        // 返回总数据数
        return total;
    }


    /**
     * 修改当前查询的sql
     *
     * @param invocation
     * @param metaObject
     * @param boundSql
     * @return
     */
    private Object changeSql(Invocation invocation, MetaObject metaObject, BoundSql boundSql) throws Exception {
        // 获取当前查询的sql
        String sql = getSql(metaObject);
        // 修改sql，$_paging_table_limit取名是为了防止数据库表重名
        String newSql = "select * from (" + sql + ") $_paging_table_limit limit ?, ?";
        // 设置当前sql为修改后的sql
        setSql(metaObject, newSql);

        // 获取PreparedStatement对象
        PreparedStatement pstmt = (PreparedStatement) invocation.proceed();
        // 获取sql的总参数个数
        int parameCount = pstmt.getParameterMetaData().getParameterCount();
        // 设置分页参数
        pstmt.setInt(parameCount - 1, 1);
        pstmt.setInt(parameCount, 10);

        return pstmt;
    }

    /**
     * 获取当前查询的sql
     *
     * @param metaObject
     * @return
     */
    private String getSql(MetaObject metaObject) {
        return (String) metaObject.getValue("delegate.boundSql.sql");
    }

    /**
     * 设置当前查询的sql
     *
     * @param metaObject
     */
    private void setSql(MetaObject metaObject, String sql) {
        metaObject.setValue("delegate.boundSql.sql", sql);
    }
}
