package zhku.graduation.basic.controller;

import lombok.extern.slf4j.Slf4j;
import zhku.graduation.basic.constant.HttpStatus;
import zhku.graduation.basic.request.BasePageRequest;
import zhku.graduation.basic.vo.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * @author: qr
 */
@SuppressWarnings("all")
@Slf4j
public class BaseController {

    protected Result error(HttpStatus status) {
        return Result.error(status.getCode(), status.getMsg());
    }

    protected Integer getPageSize(Integer param, Integer defaultValue) {
        param = getParam(param, defaultValue);
        if (param < 1) {
            param = defaultValue;
        }
        return param;
    }

    protected Integer getIntParam(Integer param, Integer defaultValue) {
        return getParam(param, defaultValue);
    }

    protected String getStringParam(String param, String defaultValue) {
        return getParam(param, defaultValue);
    }

    protected Double getDoubleParam(Double param, Double defaultValue) {
        return getParam(param, defaultValue);
    }

    private <T> T getParam(T param, T defaultValue) {
        if (param == null) {
            param = defaultValue;
        }
        return param;
    }

    protected boolean isIntParamInvalid(Integer value) {
        return value == null || value < 1;
    }


    protected String getParamsAsString(HttpServletRequest request) {
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(
                    request.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            log.info("BaseController.getJSONParams.[request] " + sb);
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void handlePageRequest(BasePageRequest request) {
        Integer pageNum = request.getPageNum();
        Integer pageSize = request.getPageSize();
        pageNum = getIntParam(pageNum, 1);
        pageSize = getPageSize(pageSize, 20);
        request.setPageNum(pageNum);
        request.setPageSize(pageSize);
    }

}
