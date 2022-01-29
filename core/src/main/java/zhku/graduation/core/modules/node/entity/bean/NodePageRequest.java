package zhku.graduation.core.modules.node.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import zhku.graduation.basic.request.BasePageRequest;

/**
 * @author QR
 * @since 2022-01-29
 */
@Getter
@Setter
@ToString
@ApiModel(value = "鱼缸节点信息表分页请求")
public class NodePageRequest extends BasePageRequest{


}
