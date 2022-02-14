package zhku.graduation.core.modules.record.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author QR
 * @since 2022-02-14
 */
@Getter
@Setter
@ToString
@ApiModel(value = "监测记录表列表请求")
public class MonitorRecordListRequest {

    @ApiModelProperty("患者id")
    private Integer patientId;

}
