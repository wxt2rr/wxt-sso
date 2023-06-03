package com.wangxt.wxt.sso.session.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static String getValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                String name = c.getName();
                if (cookieName.equalsIgnoreCase(name)) {
                    return c.getValue();
                }
            }
        }

        return null;
    }

    public static void setCookie(HttpServletResponse response, String loginFlag, String sessionId, String domain, int expire) {
        Cookie cookie = new Cookie(loginFlag, sessionId);
        cookie.setPath("/");
        cookie.setDomain(domain);
        cookie.setMaxAge(expire);
        response.addCookie(cookie);
    }
}
