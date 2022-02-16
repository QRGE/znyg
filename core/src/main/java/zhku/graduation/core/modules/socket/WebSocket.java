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

    private static final CopyOnWriteArraySet<WebSocket> WEB_SOCKETS = new CopyOnWriteArraySet<>();

    private static final Map<String,Session> SESSION_POOL = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value="username")String username) {
        this.session = session;
        WEB_SOCKETS.add(this);
        SESSION_POOL.put(username, session);
        log.info("新添加连接: {}, 当前连接总数: {}", username, WEB_SOCKETS.size());
    }

    @OnClose
    public void onClose() {
        WEB_SOCKETS.remove(this);
        log.info("连接断开，当前连接总数: {}", WEB_SOCKETS.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("收到客户端消息: {}", message);
    }

    /**
     * 广播消息
     * @param message 消息内容
     */
    public void sendAllMessage(String message) {
        for(WebSocket webSocket : WEB_SOCKETS) {
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 单点消息
     * @param username 用户名
     * @param message 消息
     */
    public void sendOneMessage(String username, String message) {
        log.info("单点消息, 发送用户: {}, 内容: {}", username, message);
        Session session = SESSION_POOL.get(username);
        if (session != null) {
            try {
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
