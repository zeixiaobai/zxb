package org.zxb.plun;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.config.PropertyRegistry;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: mapper 模板增强
 * @author: zjx
 * @time: 2020/1/10 21:12
 */
public class MapperPlugin extends PluginAdapter {

    private String template = ".generator";

    private String fileTitle = "《扩展Dao操作，由Mybatis Generator插件自动生成，多次生成，不会覆盖》";

    private String mgfileTitle = "《由Mybatis Generator插件自动生成，多次生成，会覆盖,不要手动增加方法》";

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        interfaze.addJavaDocLine("/**\n" +
                " * " + mgfileTitle + "\n" +
                " * \n" +
                " * @author Mybatis Generator\n" +
                " * @date " + dateFormatter.format(new Date()) + "\n" +
                " */");

        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        String daoBaseName = introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Dao";
        String targetPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();
        String targetProject = context.getJavaClientGeneratorConfiguration().getTargetProject();

        // 判断是否.generator结尾
        if (targetPackage.indexOf(template) == -1) {
            return null;
        }
        targetPackage = targetPackage.substring(0, targetPackage.indexOf(template));
        String targetPackagePath = targetPackage.replaceAll("\\.", "/");
        StringBuilder filePath = new StringBuilder();
        filePath.append(targetProject)
                .append(File.separator)
                .append(targetPackagePath)
                .append(File.separator)
                .append(daoBaseName)
                .append(".java");
        if (new File(filePath.toString()).exists()) {
            System.out.println(filePath + "文件存在，不生成");
            return null;
        }

        CompilationUnit unit = generateCompilationUnit(daoBaseName, targetPackage, introspectedTable);

        List<GeneratedJavaFile> list = new ArrayList<>();

        GeneratedJavaFile gjf = new GeneratedJavaFile(unit,
                context.getJavaClientGeneratorConfiguration()
                        .getTargetProject(),
                context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                context.getJavaFormatter());
        list.add(gjf);
        return list;
    }


    private CompilationUnit generateCompilationUnit(String daoBaseName, String targetPackage, IntrospectedTable introspectedTable) {
        //实体类类型
        // String entityClazzType = introspectedTable.getBaseRecordType();
        // xxxMapper类类型
        String mapperInterfaceType = introspectedTable.getMyBatis3JavaMapperType();

        String domainObjectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

        StringBuilder builder = new StringBuilder();

        //继承接口 xxxMapper.java
        FullyQualifiedJavaType superClassType = new FullyQualifiedJavaType(
                builder.append(domainObjectName)
                        .append("Mapper").toString()
        );
        Interface dto = new Interface(targetPackage + "." + daoBaseName);
        dto.addSuperInterface(superClassType);
        dto.setVisibility(JavaVisibility.PUBLIC);

        //导入xxxMapper类所在的包
        FullyQualifiedJavaType modelJavaType = new FullyQualifiedJavaType(mapperInterfaceType);
        dto.addImportedType(modelJavaType);


        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dto.addJavaDocLine("/**\n" +
                " * " + fileTitle + "\n" +
                " * \n" +
                " * @author Mybatis Generator\n" +
                " * @date " + dateFormatter.format(new Date()) + "\n" +
                " */");

        return dto;
    }

}
