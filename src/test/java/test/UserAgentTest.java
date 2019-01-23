package test;

import eu.bitwalker.useragentutils.UserAgent;

public class UserAgentTest {
    public static void main(String[] args) {
        UserAgent userAgent = UserAgent.parseUserAgentString("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
        System.out.println(userAgent.getOperatingSystem().getName());
        System.out.println(userAgent.getOperatingSystem().getGroup());
        System.out.println(userAgent.getBrowser().getName());
        System.out.println(userAgent.getBrowserVersion().toString());
    }
}
