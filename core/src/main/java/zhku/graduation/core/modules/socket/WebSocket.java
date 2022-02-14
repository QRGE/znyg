package zhku.graduation.core.modules.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author qr
 * @date 2022/2/14 11:56
 */
@Component
@ServerEndpoint("/socket/{username}")
@Slf4j
public class WebSocket {

    private Session session;

    private static final CopyOnWriteArraySet<WebSocket> webSockets =new CopyOnWriteArraySet<>();

    private static final Map<String,Session> sessionPool = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value="username")String username) {
        this.session = session;
        webSockets.add(this);
        sessionPool.put(username, session);
        System.out.println(username+"【websocket消息】有新的连接，总数为:" + webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        System.out.println("连接断开，总数为:" + webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("收到客户端消息:" + message);
    }

    // 广播消息
    public void sendAllMessage(String message) {
        log.info("广播消息: {}", message);
        for(WebSocket webSocket : webSockets) {
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 单点消息
    public void sendOneMessage(String username, String message) {
        log.info("单点消息, 发送用户: {}, 内容: {}", username, message);
        Session session = sessionPool.get(username);
        if (session != null) {
            try {
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
