package cn.javabb.codegen;

import cn.javabb.codegen.engine.BeetlTemplateEnginePlus;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2022/04/28 21:15
 */

public class CodeGenApp {
    // 输出位置
    //private static final String OUTPUT_LOCATION = System.getProperty("user.dir");
    private static final String OUTPUT_LOCATION = "D:/codegen";  // 不想生成到项目中可以写磁盘路径
    // 输出目录
    private static final String OUTPUT_DIR = "/src/main/java";
    // 作者名称
    private static final String AUTHOR = "javabb";
    // 是否在xml中添加二级缓存配置
    private static final boolean ENABLE_CACHE = false;
    // 数据库连接配置
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_bbs_test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "mima123";
    // 包名
    private static final String PACKAGE_NAME = "cn.javabb";
    // 模块名
    private static final String MODULE_NAME = "sys";
    // 需要生成的表
    private static final String[] TABLE_NAMES = new String[]{
            "sys_user",
            "sys_role"
    };
    // 需要去除的表前缀
    private static final String[] TABLE_PREFIX = new String[]{
            "sys_",
            "tb_"
    };
    // 不需要作为查询参数的字段
    private static final String[] PARAM_EXCLUDE_FIELDS = new String[]{
            "tenant_id",
            "create_time",
            "update_time"
    };
    // 查询参数使用String的类型
    private static final String[] PARAM_TO_STRING_TYPE = new String[]{
            "Date",
            "LocalDate",
            "LocalTime",
            "LocalDateTime"
    };
    // 查询参数使用EQ的类型
    private static final String[] PARAM_EQ_TYPE = new String[]{
            "Integer",
            "Boolean",
            "BigDecimal"
    };
    // 是否添加权限注解
    private static final boolean AUTH_ANNOTATION = true;
    // 是否添加日志注解
    private static final boolean LOG_ANNOTATION = true;
    // controller的mapping前缀
    private static final String CONTROLLER_MAPPING_PREFIX = "/api";
    // 模板所在位置
    private static final String TEMPLATES_DIR = "/src/main/java/cn/javabb/codegen/templates/btl";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(OUTPUT_LOCATION + OUTPUT_DIR);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setEnableCache(ENABLE_CACHE);
        gc.setSwagger2(true);
        gc.setIdType(IdType.AUTO);
        gc.setEntityName("%sDO");
        gc.setMapperName("%sDao");
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DB_DRIVER);
        dsc.setUsername(DB_USERNAME);
        dsc.setPassword(DB_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(MODULE_NAME);
        pc.setParent(PACKAGE_NAME);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(TABLE_NAMES);
        strategy.setTablePrefix(TABLE_PREFIX);
        strategy.setSuperControllerClass(PACKAGE_NAME + ".common.core.web.BaseController");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setLogicDeleteFieldName("deleted");
        mpg.setStrategy(strategy);

        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(TEMPLATES_DIR + "/controller.java");
        templateConfig.setEntity(TEMPLATES_DIR + "/entity.java");
        templateConfig.setMapper(TEMPLATES_DIR + "/mapper.java");
        templateConfig.setXml(TEMPLATES_DIR + "/mapper.xml");
        templateConfig.setService(TEMPLATES_DIR + "/service.java");
        templateConfig.setServiceImpl(TEMPLATES_DIR + "/serviceImpl.java");
        mpg.setTemplate(templateConfig);
        mpg.setTemplateEngine(new BeetlTemplateEnginePlus());

        // 自定义模板配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("packageName", PACKAGE_NAME);
                map.put("paramExcludeFields", PARAM_EXCLUDE_FIELDS);
                map.put("paramToStringType", PARAM_TO_STRING_TYPE);
                map.put("paramEqType", PARAM_EQ_TYPE);
                map.put("authAnnotation", AUTH_ANNOTATION);
                map.put("logAnnotation", LOG_ANNOTATION);
                map.put("controllerMappingPrefix", CONTROLLER_MAPPING_PREFIX);
                this.setMap(map);
            }
        };

        String templatePath = TEMPLATES_DIR + "/param.java.btl";
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return OUTPUT_LOCATION + OUTPUT_DIR + "/"
                        + PACKAGE_NAME.replace(".", "/")
                        + "/" + pc.getModuleName() + "/param/"
                        + tableInfo.getEntityName() + "Param" + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        mpg.execute();

    }
}
