package zhku.graduation.core.util;

import cn.hutool.core.util.StrUtil;

/**
 * @author qr
 * @date 2021/12/2 13:12
 */
public class NameTool {

    /**
     * 获取去除表前缀的表名
     * @param tableName 表名
     * @param tableSuffix 表前缀
     * @return 去除表前缀后的名字
     */
    public static String tableSuffix(String tableName, String tableSuffix){
        return tableName.substring(tableSuffix.length());
    }

    /**
     * 获取简单类名的大驼峰形式
     * @param smallHump 是否开启小驼峰, false则为大驼峰
     */
    public static String entitySuffixName(String tableName, String tableSuffix, boolean smallHump) {
        String tableSuffixName = tableSuffix(tableName, tableSuffix);
        String[] strings = tableSuffixName.split("_");
        StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(StrUtil.upperFirst(s));
        }
        if (smallHump) {
            return StrUtil.lowerFirst(builder.toString());
        }else {
            return builder.toString();
        }
    }

    public static String prefixName(String name, String separator) {
        String[] strings = split(name, separator);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length-1; i++) {
            builder.append(strings[i]).append(separator);
        }
        return builder.toString();
    }

    /**
     * 获取 name 的末尾部分并且大写
     */
    public static String upperSuffixName(String name, String separator) {
        String[] strings = split(name, separator);
        return StrUtil.upperFirst(strings[strings.length-1]);
    }

    public static String[] split(String name, String separator) {
        return name.split(separator);
    }
}
