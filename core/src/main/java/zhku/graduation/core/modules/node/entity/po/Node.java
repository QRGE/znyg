package zhku.graduation.core.modules.node.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import zhku.graduation.core.modules.node.entity.bean.NodeDetail;


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
@TableName("znyg_node")
public class Node {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 鱼缸名称
     */
    @TableField("name")
    private String name;

    /**
     * 摆放位置
     */
    @TableField("location")
    private String location;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 更新者id
     */
    @TableField("update_by")
    private Integer updateBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建者id
     */
    @TableField("create_by")
    private Integer createBy;

    /**
     * 删除状态, 1-是, 0-否
     */
    @TableField("is_del")
    @TableLogic
    private Integer isDel;

    public Node init() {
        createTime = new Date();
        isDel = 0;
        return this;
    }

    public Node parseFromDto(NodeDetail dto) {
        id = dto.getId();
        name = dto.getName();
        location = dto.getLocation();
        description = dto.getDescription();
        return this;
    }


}
