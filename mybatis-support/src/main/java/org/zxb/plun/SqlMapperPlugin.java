package org.zxb.plun;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: sql 模板增强
 * @author: zjx
 * @time: 2020/1/10 21:11
 */
public class SqlMapperPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }


    private static String LINE = "\n";
    private static String ALIAS = "obj";
    private static String ALIAS_WHERE = "whe";
    private static String SET_FIELFD = "Set_Sql_Filed";
    private static String SET_FIELFD_AND_VALUE = "Set_Sql_Filed_Value";
    private static String SET_SQL_VALUE = "Set_Sql_Value";
    private static String WHERE_SQL = "Where_Sql";
    private static String SELECT = "select";
    private static String INSERT = "insert";
    private static String DELETE = "delete";
    private static String UPDATE = "update";


    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        setSqlField(document, introspectedTable, 2);
        setSqlFieldValue(document, introspectedTable, 3);
        setSqlValue(document, introspectedTable, 4);
        where(document, introspectedTable, 5);
        select(document, introspectedTable, 6);
        insert(document, introspectedTable, 7);
        delete(document, introspectedTable, 8);
        update(document, introspectedTable, 9);
        return true;
    }

    private void setSqlField(Document document, IntrospectedTable introspectedTable, int index) {
        XmlElement rootElement = document.getRootElement();
        XmlElement selectiveSql = new XmlElement("sql");
        selectiveSql.addAttribute(new Attribute("id", SET_FIELFD));
        StringBuffer sb = new StringBuffer();
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        for (IntrospectedColumn allColumn : allColumns) {
            sb.append("<if test=\"");
            sb.append(ALIAS);
            sb.append(".");
            sb.append(allColumn.getJavaProperty());
            sb.append(" != null\" >");
            sb.append(allColumn.getActualColumnName());
            sb.append(",</if>");
            sb.append(LINE);
        }
        selectiveSql.addElement(new TextElement(sb.toString()));
        rootElement.addElement(index, selectiveSql);
    }

    private void setSqlFieldValue(Document document, IntrospectedTable introspectedTable, int index) {
        XmlElement rootElement = document.getRootElement();
        XmlElement selectiveSqlValue = new XmlElement("sql");
        selectiveSqlValue.addAttribute(new Attribute("id", SET_FIELFD_AND_VALUE));
        StringBuffer sb = new StringBuffer();
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        for (IntrospectedColumn allColumn : allColumns) {
            sb.append("<if test=\"");
            sb.append(ALIAS);
            sb.append(".");
            sb.append(allColumn.getJavaProperty());
            sb.append(" != null\" >#{");
            sb.append(ALIAS);
            sb.append(".");
            sb.append(allColumn.getJavaProperty());
            sb.append(",jdbcType=");
            sb.append(allColumn.getJdbcTypeName());
            sb.append("},</if>");
            sb.append(LINE);
        }
        selectiveSqlValue.addElement(new TextElement(sb.toString()));
        rootElement.addElement(index, selectiveSqlValue);
    }

    private void setSqlValue(Document document, IntrospectedTable introspectedTable, int index) {
        XmlElement rootElement = document.getRootElement();
        XmlElement selectiveSqlValue = new XmlElement("sql");
        selectiveSqlValue.addAttribute(new Attribute("id", SET_SQL_VALUE));
        StringBuffer sb = new StringBuffer();
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        for (IntrospectedColumn allColumn : allColumns) {
            sb.append("<if test=\"");
            sb.append(ALIAS);
            sb.append(".");
            sb.append(allColumn.getJavaProperty());
            sb.append(" != null\" >");
            sb.append(allColumn.getActualColumnName());
            sb.append(" = #{");
            sb.append(ALIAS);
            sb.append(".");
            sb.append(allColumn.getJavaProperty());
            sb.append(",jdbcType=");
            sb.append(allColumn.getJdbcTypeName());
            sb.append("}</if>");
            sb.append(LINE);
        }
        selectiveSqlValue.addElement(new TextElement(sb.toString()));
        rootElement.addElement(index, selectiveSqlValue);
    }

    private void where(Document document, IntrospectedTable introspectedTable, int index) {
        XmlElement rootElement = document.getRootElement();
        XmlElement selectiveSqlValue = new XmlElement("sql");
        selectiveSqlValue.addAttribute(new Attribute("id", WHERE_SQL));
        StringBuffer sb = new StringBuffer();
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        sb.append("<choose> <when test=\"_parameter.containsKey('whe')\"> ");
        sb.append(LINE);
        for (IntrospectedColumn allColumn : allColumns) {
            sb.append("<if test=\"");
            sb.append(ALIAS_WHERE);
            sb.append(".");
            sb.append(allColumn.getJavaProperty());
            sb.append(" != null\" > AND ");
            sb.append(allColumn.getActualColumnName());
            sb.append(" = #{");
            sb.append(ALIAS_WHERE);
            sb.append(".");
            sb.append(allColumn.getJavaProperty());
            sb.append(",jdbcType=");
            sb.append(allColumn.getJdbcTypeName());
            sb.append("}</if>");
            sb.append(LINE);
        }
        sb.append(" </when>");
        sb.append(LINE);
        sb.append("<otherwise>");
        sb.append(LINE);
        sb.append("<if test=\""+ALIAS+" != null \">");
        for (IntrospectedColumn allColumn : allColumns) {
            sb.append("<if test=\"");
            sb.append(ALIAS);
            sb.append(".");
            sb.append(allColumn.getJavaProperty());
            sb.append(" != null\" > AND ");
            sb.append(allColumn.getActualColumnName());
            sb.append(" = #{");
            sb.append(ALIAS);
            sb.append(".");
            sb.append(allColumn.getJavaProperty());
            sb.append(",jdbcType=");
            sb.append(allColumn.getJdbcTypeName());
            sb.append("}</if>");
            sb.append(LINE);
        }
        sb.append("</if>");
        sb.append(" </otherwise> </choose>");
        selectiveSqlValue.addElement(new TextElement(sb.toString()));
        rootElement.addElement(index, selectiveSqlValue);
    }

    private void select(Document document, IntrospectedTable introspectedTable, int index) {
        XmlElement rootElement = document.getRootElement();
        XmlElement selectiveSqlValue = new XmlElement("select");
        selectiveSqlValue.addAttribute(new Attribute("id", SELECT));
        selectiveSqlValue.addAttribute(new Attribute("resultMap", "BaseResultMap"));
 //       selectiveSqlValue.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        StringBuffer sb = new StringBuffer();
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();

        sb.append("select <include refid=\"Base_Column_List\" />  from ");
        sb.append(introspectedTable.getTableConfiguration().getTableName());
        sb.append(LINE);
        sb.append("<where> <include refid=\""+WHERE_SQL+"\" /> </where>");

        selectiveSqlValue.addElement(new TextElement(sb.toString()));
        rootElement.addElement(index, selectiveSqlValue);
    }

    private void insert(Document document, IntrospectedTable introspectedTable, int index) {
        XmlElement rootElement = document.getRootElement();
        XmlElement selectiveSqlValue = new XmlElement("insert");
        selectiveSqlValue.addAttribute(new Attribute("id", INSERT));
      //  selectiveSqlValue.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        StringBuffer sb = new StringBuffer();
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        sb.append("insert into ");
        sb.append(introspectedTable.getTableConfiguration().getTableName());
        sb.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        sb.append("<include refid=\""+SET_FIELFD+"\" />");
        sb.append("</trim>");
        sb.append(LINE);
        sb.append("<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">");
        sb.append("<include refid=\""+SET_FIELFD_AND_VALUE+"\" />");
        sb.append(" </trim>");
        selectiveSqlValue.addElement(new TextElement(sb.toString()));
        rootElement.addElement(index, selectiveSqlValue);
    }

    private void delete(Document document, IntrospectedTable introspectedTable, int index) {
        XmlElement rootElement = document.getRootElement();
        XmlElement selectiveSqlValue = new XmlElement("insert");
        selectiveSqlValue.addAttribute(new Attribute("id", DELETE));
   //     selectiveSqlValue.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        StringBuffer sb = new StringBuffer();
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        sb.append("delete from ");
        sb.append(introspectedTable.getTableConfiguration().getTableName());
        sb.append("<where> <include refid=\""+WHERE_SQL+"\" /> </where>");
        selectiveSqlValue.addElement(new TextElement(sb.toString()));
        rootElement.addElement(index, selectiveSqlValue);
    }

    private void update(Document document, IntrospectedTable introspectedTable, int index) {
        XmlElement rootElement = document.getRootElement();
        XmlElement selectiveSqlValue = new XmlElement("update");
        selectiveSqlValue.addAttribute(new Attribute("id", UPDATE));
  //      selectiveSqlValue.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        StringBuffer sb = new StringBuffer();
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        sb.append("update ");
        sb.append(introspectedTable.getTableConfiguration().getTableName());
        sb.append("<set>");
        sb.append("<include refid=\""+SET_SQL_VALUE+"\" />");
        sb.append("</set>");
        sb.append("<where> <include refid=\""+WHERE_SQL+"\" /> </where>");
        selectiveSqlValue.addElement(new TextElement(sb.toString()));
        rootElement.addElement(index, selectiveSqlValue);
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean sqlMapBaseColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean sqlMapBlobColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }
}
