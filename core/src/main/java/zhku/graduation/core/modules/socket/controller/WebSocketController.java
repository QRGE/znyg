package zhku.graduation.core.modules.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.socket.WebSocket;
import zhku.graduation.core.modules.socket.entity.SocketMsg;

import javax.validation.Valid;

/**
 * 用于发送socket消息的接口
 * @author qr
 * @date 2022/2/14 12:01
 */
@RestController
@RequestMapping("/socket/")
public class WebSocketController {

    @Autowired
    private WebSocket webSocket;

    @PostMapping("/sendAll")
    public Result<?> sendAll(@Valid @RequestBody SocketMsg msg) {
        webSocket.sendAllMessage(msg.getMsg());
        return Result.OK(msg);
    }

    @GetMapping("/sendOne/{username}")
    public Result<?> sendOneWebSocket(@PathVariable("username") String username,
                                      @Valid @RequestBody SocketMsg msg) {
        webSocket.sendOneMessage(username,msg.getMsg());
        return Result.OK(msg.getMsg());
    }
}
