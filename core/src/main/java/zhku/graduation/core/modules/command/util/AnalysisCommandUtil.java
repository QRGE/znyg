package zhku.graduation.core.modules.command.util;

/**
 * 控制命令格式为“KxxddssB”，
 *  “K”为数据包开始标志，
 *  “xx”为鱼缸编号，范围为“00-99”，
 *  “dd”为设置的温度目标值，
 *  “ss”分别代表要求灯光和除菌器开启或者关闭，
 *  “s”取值为”Y”表示开启，取值为“N”表示关闭，
 *  字母“B”为数据包结束标志和表示控制命令来自web程序。"
 * @author qr
 * @date 2022/3/1 17:07
 */
public class AnalysisCommandUtil {

    private final static int START = 0;

    private final static int END = 7;

    private final static char COMMAND_START = 'K';

    private final static char COMMAND_END = 'B';

    public static String getNodeId(String command) {
        if (isInValidCommand(command)) {
            throw new IllegalArgumentException("不合法的控制命令");
        }
        return command.substring(1, 3);
    }

    public static String getTemperature(String command) {
        if (isInValidCommand(command)) {
            throw new IllegalArgumentException("不合法的控制命令");
        }
        return command.substring(3, 5);
    }

    public static String getLight(String command) {
        if (isInValidCommand(command)) {
            throw new IllegalArgumentException("不合法的控制命令");
        }
        return command.substring(5, 6);
    }

    public static String getDegerming(String command) {
        if (isInValidCommand(command)) {
            throw new IllegalArgumentException("不合法的控制命令");
        }
        return command.substring(6, 7);
    }

    private static boolean isInValidCommand(String command) {
        char[] chars = command.toCharArray();
        return chars[START] != COMMAND_START || chars[END] != COMMAND_END;
    }
}
