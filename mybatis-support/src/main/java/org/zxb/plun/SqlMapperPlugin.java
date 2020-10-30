package org.zxb.plun;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.XmlConstants;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: sql 模板增强
 * @author: zjx
 * @time: 2020/1/10 21:11
 */
public class SqlMapperPlugin extends PluginAdapter {
    private String template = ".generator";
    private String fileTitle = "《扩展XML操作，由Mybatis Generator插件自动生成，多次生成，不会覆盖》";
    private String mgfileTitle = "《由Mybatis Generator插件自动生成，多次生成，会覆盖，不要手动增加sql》";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 开启xml覆盖
     *
     * @param sqlMap
     * @param introspectedTable
     * @return {@link boolean}
     * @author zjx
     * @date 2020/10/30 14:13
     */
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {

        try {
            java.lang.reflect.Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.setBoolean(sqlMap, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 修改生成的自动生成xml的namespace
     *
     * @param document
     * @param introspectedTable
     * @return {@link boolean}
     * @author zjx
     * @date 2020/10/30 15:12
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        String daoBaseName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Dao";
        String targetJavaPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();
        // 判断是否.generator结尾
        if (targetJavaPackage.indexOf(template) == -1) {
            return true;
        }
        targetJavaPackage = targetJavaPackage.substring(0, targetJavaPackage.indexOf(template));

        document.getRootElement().getAttributes().remove(0);
        document.getRootElement().getAttributes().add(new Attribute("namespace", targetJavaPackage + "." + daoBaseName));

        StringBuilder sb = new StringBuilder();
        sb.append("<!-- ")
                .append("\r\n")
                .append("  " )
                .append(mgfileTitle)
                .append("\r\n")
                .append("  generator date：");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        sb.append(dateFormatter.format(new Date()));
        sb.append('.').append("\r\n").append("  -->");
        document.getRootElement().addElement(0, new TextElement(sb.toString()));


        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {
        String daoBaseName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Dao";
        String targetPackage = context.getSqlMapGeneratorConfiguration().getTargetPackage();
        String targetJavaPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();
        String targetProject = context.getSqlMapGeneratorConfiguration().getTargetProject();

        // 判断是否.generator结尾
        if (targetPackage.indexOf(template) == -1 && targetJavaPackage.indexOf(template) == -1) {
            return null;
        }
        targetPackage = targetPackage.substring(0, targetPackage.indexOf(template));
        targetJavaPackage = targetJavaPackage.substring(0, targetJavaPackage.indexOf(template));

        String targetPackagePath = targetPackage.replaceAll("\\.", "/");
        StringBuilder filePath = new StringBuilder();
        filePath.append(targetProject)
                .append(File.separator)
                .append(targetPackagePath)
                .append(File.separator)
                .append(daoBaseName)
                .append(".xml");
        if (new File(filePath.toString()).exists()) {
            System.out.println(filePath + "文件存在，不生成");
            return null;
        }

        String domainType = introspectedTable.getBaseRecordType();
        Document document = new Document(
                XmlConstants.MYBATIS3_MAPPER_CONFIG_PUBLIC_ID,
                XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
        XmlElement root = new XmlElement("mapper");
        document.setRootElement(root);
        root.addAttribute(new Attribute("namespace", targetJavaPackage + "." + daoBaseName));
        root.addElement(new TextElement("<!--"));
        root.addElement(new TextElement("  " + fileTitle));
        root.addElement(new TextElement(
                "  这是一个继承自父xxxMapper.xml的配置文件，扩展的sql语句操作都放在这个文件"));
        root.addElement(new TextElement(
                "  注意：不要使用与父mapper相同的statement"));
        StringBuilder sb = new StringBuilder();
        sb.append("  generator date：");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        sb.append(dateFormatter.format(new Date()));
        sb.append('.');
        root.addElement(new TextElement(sb.toString()));
        root.addElement(new TextElement("-->"));

        GeneratedXmlFile gxf = new GeneratedXmlFile(document,
                daoBaseName + ".xml",
                targetPackage,
                targetProject,
                false, context.getXmlFormatter());

        List<GeneratedXmlFile> list = new ArrayList<>(1);
        list.add(gxf);

        return list;
    }

}
