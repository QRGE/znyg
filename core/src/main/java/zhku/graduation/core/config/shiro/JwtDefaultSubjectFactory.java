package zhku.graduation.core.config.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @author qr
 * @date 2022/1/11 22:48
 */
public class JwtDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        // 关闭 shiro 的会话
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
