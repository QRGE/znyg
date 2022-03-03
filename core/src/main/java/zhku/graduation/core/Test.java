package zhku.graduation.core;

import zhku.graduation.core.util.PasswordUtil;

/**
 * @author qr
 * @date 2022/3/3 13:29
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(PasswordUtil.encrypt("zhangqiren", "123456", "cxbWpiJM"));
    }
}
