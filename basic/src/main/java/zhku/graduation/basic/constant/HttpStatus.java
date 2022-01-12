package zhku.graduation.basic.constant;

public enum HttpStatus {
    /**
     *
     */
    SUCCESS(200, "OK"),
    PARAM_ERROR(1001, "Param error"),
    PARAM_MISSING(1002, "Miss param"),
    OPERATION_FAILURE(1003, "something fail"),
    NO_DATA(1005, "no data"),
    REQUEST_FAILURE(1006, "request error");

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
