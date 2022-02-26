package zhku.graduation.basic.request;

import zhku.graduation.basic.constant.Constant;

import java.util.Date;

public interface BaseTimeRequest {

    Date getStartTime();

    void setStartTime(Date startTime);

    Date getEndTime();

    void setEndTime(Date endTime);

    Integer getOrderType();

    void setOrderType(Integer orderType);

    default void handleOrderType() {
        Integer orderType = this.getOrderType();
        if (orderType == null || orderType != Constant.OrderType.DESC.getType()) {
            this.setOrderType(Constant.OrderType.ASC.getType());
        }
    }
}
