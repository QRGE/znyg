package zhku.graduation.basic.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings("unused")
public interface Constant {

	Integer DEFAULT_PAGE = 1;

	Integer DEFAULT_PAGE_SIZE = 20;

	@AllArgsConstructor
	@Getter
	enum Sex {
	    MAN(1, "男"),
		WOMAN(2, "女")
	    ;

	    int type;
	    String name;

	    public static Sex valueOf(Integer type) {
	        return Arrays.stream(values()).filter(c -> Objects.equals(c.getType(),type)).findAny().orElse(MAN);
	    }
	}

	@AllArgsConstructor
	@Getter
	enum OrderType {

	    ASC(1),
		DESC(2);

	    int type;

	    public static OrderType valueOf(Integer type) {
	        return Arrays.stream(values()).filter(c -> Objects.equals(c.getType(),type)).findAny().orElse(ASC);
	    }
	}

	@AllArgsConstructor
	@Getter
	enum CommandStatus {
	    NOT_START(0, "未执行"),
		HAD_SENT(1, "已发送"),
		FINISHED(2, "已完成");

	    int type;
	    String name;

	    public static CommandStatus valueOf(Integer type) {
	        return Arrays.stream(values()).filter(c -> Objects.equals(c.getType(),type)).findAny().orElse(NOT_START);
	    }
	}

	@AllArgsConstructor
	@Getter
	enum Role {
	    ADMIN(1, "管理员"),
		COMMON_USER(2, "普通用户")
	    ;

	    int type;
	    String name;

	    public static Role valueOf(Integer type) {
	        return Arrays.stream(values()).filter(c -> Objects.equals(c.getType(),type)).findAny().orElse(COMMON_USER);
	    }
	}

	@AllArgsConstructor
	@Getter
	enum TagType {
		DANGER(0, "danger"),
	    PRIMARY(1, "primary"),
		SUCCESS(2, "success")
	    ;

	    int type;
	    String name;

	    public static TagType valueOf(Integer type) {
	        return Arrays.stream(values()).filter(c -> Objects.equals(c.getType(),type)).findAny().orElse(PRIMARY);
	    }
	}

	@AllArgsConstructor
	@Getter
	enum CommandInstrumentStatus {
	    OPEN("Y", "开启"),
		CLOSE("N", "关闭")
	    ;

	    String type;
	    String name;

	    public static CommandInstrumentStatus select(String type) {
	        return Arrays.stream(values()).filter(c -> Objects.equals(c.getType(),type)).findAny().orElse(CLOSE);
	    }
	}

	@AllArgsConstructor
	@Getter
	public enum Status {
	    CLOSE(0, "关闭"),
		OPEN(1, "开启")
	    ;

	    int type;
	    String name;

	    public static Status valueOf(Integer type) {
	        return Arrays.stream(values()).filter(c -> Objects.equals(c.getType(),type)).findAny().orElse(CLOSE);
	    }
	}

	String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 生成 mock 数据任务
	 */
	String JOB_MOCK = "jobDetail-1";
	/**
	 * 获取最新的监控记录
	 */
	String JOB_GET_LATEST_RECORD = "jobDetail-2";

	String TRIGGER_1 = "Trigger-1";

	String TRIGGER_2 = "Trigger-2";

	/**
	 * 正常状态
	 */
    Integer STATUS_NORMAL = 0;

	/**
	 * 禁用状态
	 */
    Integer STATUS_DISABLE = -1;

	/**
	 * 删除标志
	 */
    Integer IS_DEL_1 = 1;

	/**
	 * 未删除
	 */
    Integer IS_DEL_0 = 0;

	/**
	 * 系统日志类型： 登录
	 */
    int LOG_TYPE_1 = 1;
	
	/**
	 * 系统日志类型： 操作
	 */
    int LOG_TYPE_2 = 2;

    /**
     * 系统日志类型： 退出登陆
     */
    int LOG_TYPE_3 = 3;

	/**
	 * 操作日志类型： 查询
	 */
    int OPERATE_TYPE_QUERY = 1;
	
	/**
	 * 操作日志类型： 添加
	 */
    int OPERATE_TYPE_ADD = 2;
	
	/**
	 * 操作日志类型： 更新
	 */
    int OPERATE_TYPE_UPDATE = 3;
	
	/**
	 * 操作日志类型： 删除
	 */
    int OPERATE_TYPE_REMOVE = 4;

    /**
     * 操作日志类型：新增或修改
     */
    int OPERATE_TYPE_SAVE_OR_UPDATE = 5;
	
	/**
	 * 操作日志类型： 导入
	 */
    int OPERATE_TYPE_IMPORT = 6;
	
	/**
	 * 操作日志类型： 导出
	 */
    int OPERATE_TYPE_EXPORT = 7;
	
	
	/**
     * {@code 500 Server Error} (HTTP/1.0 - RFC 1945)
     * */
    int SC_SERVER_ERROR = 500;

    /**
     * {@code 200 OK} (HTTP/1.0 - RFC 1945)
     * */
    Integer SC_OK_200 = 200;
    
    /**访问权限认证未通过 510*/
    Integer SC_NO_AUTH =510;

    /**
     * Token缓存时间：3600s，即一小时
     * */
    int  TOKEN_EXPIRE_TIME  = 3600;
    
    /**
     * 状态(0无效1有效)
     */
    int STATUS_0 = 0;
    int STATUS_1 = 1;

	/**
	 * token 标记
	 */
	String TOKEN = "X-Access-Token";

}
