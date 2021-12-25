package zhku.graduation.basic.constant;

/**
 * @author 萧
 * @date:2021-08-22 10:27 下午
 **/
public class SystemState {

    public enum ResponseState {
        /**
         *
         */
        STATE_SUCCESS(200, "操作成功"),
        STATE_PARAMS_ERROR(1001, "参数错误"),
        STATE_PARAMS_MISSING(1002, "缺少必要参数"),
        STATE_OPERATION_FAILURE(1003, "操作失败"),
        STATE_DATA_ERROR(1004, "数据错误"),
        STATE_NO_RECORD(1005, "查无数据"),
        STATE_REQUEST_FAILURE(1006, "请求失败");

        private int code;
        private String msg;

        ResponseState(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
