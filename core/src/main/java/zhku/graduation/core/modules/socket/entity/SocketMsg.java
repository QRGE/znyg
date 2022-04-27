package zhku.graduation.core.modules.socket.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author qr
 * @date 2022/3/31 09:34
 */
@ApiModel("发送socket的消息")
@Getter
@Setter
public class SocketMsg {

    @Length(max = 300, message = "最长消息为300个字符")
    @NotBlank(message = "发送消息不能为空")
    @ApiModelProperty("消息")
    private String msg;
}
