package zhku.graduation.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author qr
 * @date 2022/2/26 15:26
 */
@ApiModel("分页对象")
@Getter
@Setter
public class Page <T>{

    @ApiModelProperty("记录列表")
    private List<T> records;

    @ApiModelProperty("总记录")
    private Long count;

    @ApiModelProperty("总页数")
    private Long totalPage;

}
