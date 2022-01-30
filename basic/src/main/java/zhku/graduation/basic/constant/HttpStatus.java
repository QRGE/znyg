package zhku.graduation.basic.constant;

import java.util.Arrays;
import java.util.Objects;

public enum HttpStatus {


    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    PARAM_ERROR(10001, "请求参数错误"),
    PARAM_MISSING(10002, "缺少必要参数"),
    NO_DATA(10004, "查无数据"),
    REQUEST_FAILURE(10005, "请求失败"),
    AUTH_ERROR(10006, "认证错误");

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
                .orElse(ERROR);
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
