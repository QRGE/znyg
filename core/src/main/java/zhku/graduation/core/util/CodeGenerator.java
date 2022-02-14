package zhku.graduation.core.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import zhku.graduation.basic.controller.BaseController;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static zhku.graduation.core.util.NameTool.entitySuffixName;
import static zhku.graduation.core.util.NameTool.tableSuffix;

/**
 * !!! 表的备注很重要, 用的使用看下有没有缺需不需要修改
 * !!! 表的备注很重要, 用的使用看下有没有缺需不需要修改
 * !!! 表的备注很重要, 用的使用看下有没有缺需不需要修改
 * @author qr
 * @date 2021/11/17 3:28 PM
 */
@SuppressWarnings("all")
public class CodeGenerator {

    /***************************************看着改******************************************/
    // 表名
    private static final String TABLE_NAME = "znyg_monitor_record";
    // 表前缀
    private static final String TABLE_PREFIX = "znyg_";
    // 模块名
    private static final String FUNCTION_MODULE = "record";
    // 用来接受数据的名字后缀
    private static String DTO_SUFFIX = "Detail";
    // 存放模版的文件夹路径
    private static String TEMPLATE_PATH = "templates";
    /**************************************************************************************/

    private static final String PARENT_PACKAGE = "zhku.graduation.core.modules";


    /**
     * 项目 / maven 模块名
     */
    private static final String PROJECT_MODULE = "core";

    private static final String AUTHOR = "QR";

    /*************************************数据库相关*****************************************/
    // 我本地的 mysql 的版本是 8.0+ 的
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/znyg?characterEncoding=UTF-8&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "QRWUDI666";
    /**************************************************************************************/

    private static final String PROJECT_PATH = System.getProperty("user.dir");

    private static final String OUTPUT_DIR = PROJECT_PATH + File.separator +  PROJECT_MODULE + "/src/main/java";

    private static final String EXTAR_PATH = OUTPUT_DIR + "/zhku/graduation/core/modules/" + FUNCTION_MODULE + "/entity/bean/";

    private static String TABLE_SUFFIX = tableSuffix(TABLE_NAME, TABLE_PREFIX);

    /**
     * 也可以用 ?uncap_first
     */
    private static String ENTITY_SUFFIX_SMALL_HUMP = entitySuffixName(TABLE_NAME, TABLE_PREFIX, true);
    private static String ENTITY_SUFFIX_BIG_HUMP = entitySuffixName(TABLE_NAME, TABLE_PREFIX, false);

    private static String DTO_NAME = ENTITY_SUFFIX_BIG_HUMP + DTO_SUFFIX;   // 完整的名字
    private static String DTO_CLASS_NAME = ENTITY_SUFFIX_BIG_HUMP + DTO_SUFFIX + ".java";  // 类名

    private static String PAGE_REQUEST_NAME = ENTITY_SUFFIX_BIG_HUMP + "PageRequest";
    private static String PAGE_REQUEST_CLASS_NAME = PAGE_REQUEST_NAME + ".java";

    private static String LIST_REQUEST_NAME = ENTITY_SUFFIX_BIG_HUMP + "ListRequest";
    private static String LIST_REQUEST_CLASS_NAME = LIST_REQUEST_NAME + ".java";

    private static String LIST_INFO_NAME = ENTITY_SUFFIX_BIG_HUMP + "ListInfo";
    private static String LIST_INFO_CLASS_NAME = LIST_INFO_NAME + ".java";

    private static Map<String, String> EXTAR_FILE_MAP = new HashMap<>();
    static {
        EXTAR_FILE_MAP.put(DTO_CLASS_NAME, "templates/dto.java.ftl");
        EXTAR_FILE_MAP.put(PAGE_REQUEST_CLASS_NAME, TEMPLATE_PATH + "/PageRequest.java.ftl");
        EXTAR_FILE_MAP.put(LIST_REQUEST_CLASS_NAME, TEMPLATE_PATH + "/ListRequest.java.ftl");
        EXTAR_FILE_MAP.put(LIST_INFO_CLASS_NAME, TEMPLATE_PATH + "/ListInfo.java.ftl");
    }


    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
            // 全局配置
            .globalConfig(builder -> builder
                .author(AUTHOR)
                .enableSwagger()
                .dateType(DateType.ONLY_DATE)
                .disableOpenDir()
                .commentDate("yyyy-MM-dd")
                .outputDir(OUTPUT_DIR)
                .fileOverride()
            )
            // 模版配置
            .templateConfig(
                builder -> builder
                    .entity(TEMPLATE_PATH + "/entity.java")
                    .controller(TEMPLATE_PATH + "/controller.java")
                    .mapper(TEMPLATE_PATH + "/mapper.java")
                    .mapperXml(TEMPLATE_PATH + "/mapper.xml")
                    .service(TEMPLATE_PATH + "/service.java")
                    .serviceImpl(TEMPLATE_PATH + "/serviceImpl.java")
            )
            // 包配置
            .packageConfig(builder -> builder
                .parent(PARENT_PACKAGE)
                .moduleName(FUNCTION_MODULE)
                .entity("entity.po")
            )
            .injectionConfig(builder -> builder
                    // 这里的 objectMap 和 customMap 都可以用来往 freemakr 里面加东西
                    .beforeOutputFile((tableInfo, objectMap) -> {
                        objectMap.put("entitySuffixNameSmallHump", ENTITY_SUFFIX_SMALL_HUMP);
                        objectMap.put("entitySuffixNameBigHump", ENTITY_SUFFIX_BIG_HUMP);
                        objectMap.put("dtoFullName", DTO_NAME);
                        objectMap.put("entitySimpleName", TABLE_SUFFIX);
                        objectMap.put("pageRequestName", PAGE_REQUEST_NAME);
                        objectMap.put("listRequestName", LIST_REQUEST_NAME);
                        objectMap.put("listInfo", LIST_INFO_NAME);
                    })
                    // 添加需要额外生成的文件
                    .customFile(EXTAR_FILE_MAP)
            )
            // 模版引擎配置
            .templateEngine(new FreemarkerTemplateEngine() {
                @Override
                protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
                    String entityName = tableInfo.getEntityName();
                    String otherPath = this.getPathInfo(OutputFile.entity);
                    customFile.forEach((key, value) -> {
                        String fileName = EXTAR_PATH + key;
                        this.outputFile(new File(fileName), objectMap, value);
                    });
                }
            })
            // 策略配置
            .strategyConfig(builder -> builder
                .enableSkipView()
                .addInclude(TABLE_NAME.split(","))
                .addTablePrefix("znyg")
                .entityBuilder()
                    .disableSerialVersionUID()
                    .enableLombok()
                    .enableTableFieldAnnotation()
                    .logicDeleteColumnName("is_del")
                    .idType(IdType.AUTO)
                .mapperBuilder()
                    .enableBaseColumnList()
                    .enableBaseResultMap()
                    .enableMapperAnnotation()
                .controllerBuilder()
                    .enableRestStyle()
                    .superClass(BaseController.class)
            )
            .execute();
    }
}
