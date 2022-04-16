package zhku.graduation.core.modules.limit.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author QR
 * @since 2022-04-16
 */
@Getter
@Setter
@ToString
@ApiModel(value = "上位机 端控制命令记录列表请求")
public class CommandRecordHostListRequest {

    @ApiModelProperty("患者id")
    private Integer patientId;

}
