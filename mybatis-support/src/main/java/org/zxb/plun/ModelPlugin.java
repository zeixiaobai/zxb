package org.zxb.plun;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description: model 模板增强
 * @author: zjx
 * @time: 2020/1/10 20:51
 */
public class ModelPlugin extends PluginAdapter {


    private String mgfileTitle = "《由Mybatis Generator插件自动生成，多次生成，会覆盖，不要手动增加字段》";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addImportedType("lombok.Data");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        topLevelClass.addJavaDocLine("/**\n" +
                " * " + mgfileTitle + "\n" +
                " * \n" +
                " * @author Mybatis Generator\n" +
                " * @date " + dateFormatter.format(new Date()) + "\n" +
                " */");
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        topLevelClass.addJavaDocLine("/**\n" +
                " * " + mgfileTitle + "\n" +
                " * \n" +
                " * @author Mybatis Generator\n" +
                " * @date " + dateFormatter.format(new Date()) + "\n" +
                " */");
        return true;
    }
}
