package com.wangxt.wxt.sso.session.service;

import com.wangxt.wxt.sso.session.pojo.LoginResult;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    public LoginResult login(HttpServletRequest request, HttpServletResponse response, String name, String password) {
        boolean checkPassword = checkPassword(name, password);
        if (!checkPassword) {
            return LoginResult.builder().code(-101).build();
        }

        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            String name1 = c.getName();
            System.out.println(name1+","+c.getValue());
        }


        // 系统默认 SessionId
        String sid = request.getSession().getId();
        return LoginResult.builder().code(200).sid(sid).build();
    }

    private boolean checkPassword(String name, String password) {
        Map<String, String> userList = new HashMap<String, String>();
        userList.put("wangxt", "123456");
        userList.put("admin", "123456");

        return userList.getOrDefault(name, "").equalsIgnoreCase(password);
    }

    private void writeLoginState(HttpServletResponse response) {

    }

    public void cookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                System.out.println(cookie.getName() + "," + cookie.getValue());
            }
        }

        /**
         * 先设置 www.wangxt.com和sso.wangxt.com两个域的cookie，然后使用sso.wangxt.com访问，结论：sso.wangxt.com只能获取到WXTID2
         */
        /*Cookie cookie = new Cookie("WXTID", "www.wangxt.com");
        cookie.setDomain("www.wangxt.com");
        cookie.setPath("*");
        response.addCookie(cookie);

        cookie = new Cookie("WXTID2", "sso.wangxt.com");
        cookie.setDomain("sso.wangxt.com");
        cookie.setPath("*");
        response.addCookie(cookie);*/

        /**
         * 然后只设置顶级域名wangxt.com，使用sso.wangxt.com访问，结论：sso.chuang.com可以获取到其顶级域名下的cookie
         */
        Cookie cookie = new Cookie("WXTID", "www.wangxt.com");
        cookie.setDomain("wangxt.com");
        cookie.setPath("*");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
