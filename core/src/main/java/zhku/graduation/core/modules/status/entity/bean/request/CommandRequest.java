package zhku.graduation.core.modules.status.entity.bean.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qr
 * @date 2022/4/5 17:36
 */
@Getter
@Setter
public class CommandRequest {

    private Integer nodeId;

    private String commandObj;

    private Integer status;
}
