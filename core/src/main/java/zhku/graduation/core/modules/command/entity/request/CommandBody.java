package zhku.graduation.core.modules.command.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qr
 * @date 2022/3/2 17:52
 */
@ApiModel("命令请求体")
@Getter
@Setter
public class CommandBody {

    @ApiModelProperty("命令请求题")
    private String command;
}
