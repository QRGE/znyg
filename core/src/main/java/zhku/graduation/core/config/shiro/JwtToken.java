package zhku.graduation.core.config.shiro;

/**
 * @author qr
 * @date 2022/1/11 22:53
 */

import org.apache.shiro.authc.AuthenticationToken;

// 类似于 UsernamePasswordToken
public class JwtToken implements AuthenticationToken {

    private final String token;

    public JwtToken(String token) {
        this.token = token;
    }

    // 相当于用户名
    @Override
    public Object getPrincipal() {
        return token;
    }

    // 相当于密码
    // 设置用户名和密码都是返回的都是 jwt
    @Override
    public Object getCredentials() {
        return token;
    }

}

