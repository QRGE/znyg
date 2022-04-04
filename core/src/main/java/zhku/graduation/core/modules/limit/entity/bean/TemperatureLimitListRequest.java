package zhku.graduation.core.modules.limit.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author QR
 * @since 2022-04-04
 */
@Getter
@Setter
@ToString
@ApiModel(value = "鱼缸温度限制列表请求")
public class TemperatureLimitListRequest {

    @ApiModelProperty("患者id")
    private Integer patientId;

}
