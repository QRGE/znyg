package zhku.graduation.core.modules.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.modules.socket.WebSocket;

/**
 * @author qr
 * @date 2022/2/14 12:01
 */
@RestController
@RequestMapping("/socket/")
public class WebSocketController {

    @Autowired
    private WebSocket webSocket;

    @GetMapping("/sendAll")
    public Result<?> sendAll() {
        String text = "你们好哇!";
        webSocket.sendAllMessage(text);
        return Result.OK(text);
    }

    @GetMapping("/sendOne/{username}")
    public Result<?> sendOneWebSocket(@PathVariable("username") String username) {
        String text = String.format("你好哇! %s", username);
        webSocket.sendOneMessage(username,text);
        return Result.OK(text);
    }
}
