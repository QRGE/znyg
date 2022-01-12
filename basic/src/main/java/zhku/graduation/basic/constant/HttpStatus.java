package zhku.graduation.basic.constant;

public enum HttpStatus {
    /**
     *
     */
    STATE_SUCCESS(200, "OK"),
    STATE_PARAMS_ERROR(1001, "Param error"),
    STATE_PARAMS_MISSING(1002, "Miss param"),
    STATE_OPERATION_FAILURE(1003, "something fail"),
    STATE_NO_RECORD(1005, "no data"),
    STATE_REQUEST_FAILURE(1006, "request error");

    private int code;
    private String msg;

    HttpStatus(int code, String msg) {
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
