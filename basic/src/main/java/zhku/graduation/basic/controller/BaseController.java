package zhku.graduation.basic.controller;

import lombok.extern.slf4j.Slf4j;
import zhku.graduation.basic.constant.Constant;
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

    protected Integer getPageSize(Integer pageSize, Integer defaultPageSize) {
        pageSize = getParam(pageSize, defaultPageSize);
        return pageSize < 1 ? defaultPageSize : pageSize;
    }

    protected Integer getPage(Integer page, Integer defaultPage) {
        page = getParam(page, defaultPage);
        return page < 1 ? defaultPage : page;
    }

    private <T> T getParam(T param, T defaultValue) {
        return param == null ? defaultValue : param;
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
        Integer page = request.getPage();
        Integer pageSize = request.getPageSize();
        page = getPage(page, Constant.DEFAULT_PAGE);
        pageSize = getPageSize(pageSize, Constant.DEFAULT_PAGE_SIZE);
        request.setPageStart((page-1) * pageSize);
        request.setPage(page);
        request.setPageSize(pageSize);
    }

}
