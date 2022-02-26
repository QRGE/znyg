package zhku.graduation.basic.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qr
 * @date: 2021-08-26 12:32 上午
 **/
@ApiModel("基础分页请求")
@Data
public class BasePageRequest implements Serializable {

    @ApiModelProperty("分页页数")
    private Integer page;

    private Integer pageStart;

    @ApiModelProperty("单页数据量")
    private Integer pageSize;

}
