package zhku.graduation.core.util;

import cn.hutool.extra.mail.MailException;
import com.sun.mail.util.MailSSLSocketFactory;
import lombok.SneakyThrows;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 用法
 * @author qr
 * @date 2022/3/7 09:42
 */
public class MailTool {

    private final static String token = "uegxcojaylnmebeh";

    private final static String host = "smtp.qq.com";

    /**
     * 发送验证码
     * @param toEmail 待发送的邮箱
     */
    public static String sendCaptcha(String toEmail) {
        try {
            String captcha = PasswordUtil.getSalt();
            send("验证码", captcha + "，验证码10分钟内有效", toEmail);
            return captcha;
        }catch (Exception e) {
            throw new MailException("发送验证码失败！请输入正确的邮箱");
        }
    }

    @SneakyThrows
    public static void send(String title, String content, String toEmail) {
        Properties prop = new Properties();
        //设置QQ邮件服务器
        prop.setProperty("mail.host", host);
        //邮件发送协议
        prop.setProperty("mail.transport.protocol", "smtp");
        //需要验证用户名密码
        prop.setProperty("mail.smtp.auth", "true");
        //关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        // 使用JavaMail发送邮件的5个步骤
        // 1、创建定义整个应用程序所需的环境信息的Session对象
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication("1826255833@qq.com", token);
            }
        });
        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(false);
        // 2、通过session得到transport对象
        Transport ts = session.getTransport();
        // 3、使用邮箱的用户名和授权码连上邮件服务器
        ts.connect("smtp.qq.com", "1826255833@qq.com", token);
        // 4、创建邮件
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("1826255833@qq.com"));
        // 指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        // 邮件标题/注意
        message.setSubject(title);
        // 邮件文本内容
        message.setContent(content, "text/html;charset=UTF-8");
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        //5，关闭连接
        ts.close();
    }
}
