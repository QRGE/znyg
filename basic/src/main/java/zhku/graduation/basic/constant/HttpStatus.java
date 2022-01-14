package zhku.graduation.basic.constant;

import java.util.Arrays;
import java.util.Objects;

public enum HttpStatus {


    SUCCESS(200, "OK"),
    PARAM_ERROR(10001, "Param error"),
    PARAM_MISSING(10002, "Miss param"),
    OPERATION_FAILURE(10003, "something fail"),
    NO_DATA(10004, "no data"),
    REQUEST_FAILURE(10005, "request error");

    private int code;

    private String msg;

    HttpStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static HttpStatus valueOf(Integer code) {
        return Arrays.stream(values())
                .filter(httpStatus -> Objects.equals(code, httpStatus.code))
                .findAny()
                .orElse(OPERATION_FAILURE);
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
