package zhku.graduation.basic.constant;

@SuppressWarnings("unused")
public interface Constant {

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
