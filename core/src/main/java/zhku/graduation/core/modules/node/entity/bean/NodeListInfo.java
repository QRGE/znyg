package zhku.graduation.core.modules.node.entity.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.node.entity.po.Node;
import java.util.Date;

/**
 * <p>
 * 鱼缸节点信息表
 * </p>
 *
 * @author QR
 * @since 2022-01-29
 */
@Getter
@Setter
@ApiModel(value = "鱼缸节点信息表列表信息", description = "鱼缸节点信息表")
public class NodeListInfo {

    private Integer id;

    @ApiModelProperty("鱼缸名称")
    private String name;

    @ApiModelProperty("摆放位置")
    private String location;

    @ApiModelProperty("描述")
    private String description;


    @ApiModelProperty("更新者id")
    private Integer updateBy;


    @ApiModelProperty("创建者id")
    private Integer createBy;


    public NodeListInfo (Node po) {
        id = po.getId();
        name = po.getName();
        location = po.getLocation();
        description = po.getDescription();
    }
}
