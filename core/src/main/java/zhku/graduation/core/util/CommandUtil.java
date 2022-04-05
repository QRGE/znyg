package zhku.graduation.core.util;

/**
 * @author qr
 * @date 2022/4/5 15:06
 */
public class CommandUtil {

    /**
     * 基础控制命令
     */
    private static String BASIC = "xx J KxxJlluusB 0";

    // region 用于替换控制命令重的字符串
    private static final String UP = "uu";
    private static final String LOW = "ll";
    private static final String HEATER_AUTO = "s";
    private static final String NODE = "xx";
    // endregion

    /**
     * 创建温度的上下限命令
     */
    public static String createTemperatureLimitCommand(Integer nodeId, Double up, Double low){
        return BASIC
                .replace(NODE, fillZero(nodeId))
                .replace(LOW, low.intValue() + "")
                .replace(UP, up.intValue() + "")
                .replace(HEATER_AUTO, "I");
    }

    private static String fillZero(Integer nodeId) {
        if (nodeId < 10) {
            return "0" + nodeId;
        }else {
            return nodeId+"";
        }
    }
}
