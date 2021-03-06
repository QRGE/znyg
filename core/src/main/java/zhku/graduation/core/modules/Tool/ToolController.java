package zhku.graduation.core.modules.Tool;

import cn.hutool.core.lang.Validator;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zhku.graduation.basic.vo.Result;
import zhku.graduation.core.util.MailTool;
import zhku.graduation.core.util.RedisUtil;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author qr
 * @date 2022/3/8 15:48
 */
@RestController
@RequestMapping("/tool")
public class ToolController {

    @Lazy
    @Resource
    private RedisUtil redisUtil;

    private final static Integer captchaExpireTime = 600;

    @GetMapping("/mail/send")
    public Result<?> get(@RequestParam String email){
        if (!Validator.isEmail(email)) {
            return Result.error("邮箱格式错误");
        }
        String captcha = MailTool.sendCaptcha(email);
        redisUtil.set(email, captcha, captchaExpireTime);
        return Result.OK("成功发送验证码");
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }
}
