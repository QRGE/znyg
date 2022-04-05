package zhku.graduation.core.util;

/**
 * @author qr
 * @date 2022/4/5 15:06
 */
public class CommandUtil {

    /**
     * 加热基础控制命令
     */
    private static final String HEATER_BASIC = "xx J KxxJlluusB 0";

    /**
     * 基础命令
     */
    private static final String BASIC = "xx O KxxOlluusB 0";

    // region 用于替换控制命令重的字符串
    private static final String UP = "uu";
    private static final String LOW = "ll";
    private static final String HEATER_AUTO = "s";
    private static final String NODE = "xx";
    private static final String OBJ = "O";
    private static final String STATUS = "s";
    // endregion

    /**
     * 创建温度的上下限命令
     */
    public static String createTemperatureLimitCommand(Integer nodeId, Double up, Double low){
        return HEATER_BASIC
                .replace(NODE, fillZero(nodeId))
                .replace(LOW, low.intValue() + "")
                .replace(UP, up.intValue() + "")
                .replace(HEATER_AUTO, "I");
    }

    public static String createHeaterCommand(Integer nodeId, Integer status, String obj){
        return BASIC.replace(NODE, fillZero(nodeId))
                .replace(OBJ, obj)
                .replace(STATUS, getStatusText(status));
    }

    private static String getStatusText(Integer status) {
        return status == 0 ? "N" : "Y";
    }

    private static String fillZero(Integer nodeId) {
        if (nodeId < 10) {
            return "0" + nodeId;
        }else {
            return nodeId+"";
        }
    }
}
