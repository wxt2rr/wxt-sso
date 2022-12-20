package com.wangxt.wxt.sso.session.service;

import com.wangxt.wxt.sso.session.consts.ConstKey;
import com.wangxt.wxt.sso.session.dao.UserDao;
import com.wangxt.wxt.sso.session.pojo.LoginResult;
import com.wangxt.wxt.sso.session.pojo.User;
import com.wangxt.wxt.sso.session.util.CacheUtil;
import com.wangxt.wxt.sso.session.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    /**
     * 客户端  ---用户名/密码--->  服务器
     * |
     * 校验通过
     * |
     * 写入cookie
     */
    public LoginResult login(HttpServletRequest request, HttpServletResponse response, String name, String password) {
        // 首先校验用户名、密码是否匹配
        User user = getUserInfo(name);
        if (user == null) {
            return LoginResult.builder().code(-100).build();
        }

        boolean checkPassword = checkPassword(user.getPassword(), password);
        if (!checkPassword) {
            return LoginResult.builder().code(-101).build();
        }

        // 使用uuid生成唯一的sessionId
        String sessionId = UUID.randomUUID().toString();
        final String key = String.format("%s_%s", ConstKey.LOGIN_FLAG, sessionId);
        CacheUtil.put(key, user.getUserId().toString());
        CookieUtil.setCookie(response, ConstKey.LOGIN_FLAG, sessionId, "wangxt.com", 60 * 60 * 24 * 7);

        // 系统默认 SessionId
        //String sid = request.getSession().getId();

        // 更新登录时间
        user.setLoginTime(LocalDateTime.now());
        updateUser(user);
        return LoginResult.builder().code(200).sid(sessionId).build();
    }

    private void updateUser(User user) {
        userDao.updateUser(user);
    }

    private User getUserInfo(String name) {
        return userDao.queryUser(name);
    }

    private boolean checkPassword(String name, String password) {
        return name.equalsIgnoreCase(password);
    }

    public void cookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + "," + cookie.getValue());
            }
        }

        /**
         * 先设置 www.wangxt.com和sso.wangxt.com两个域的cookie，然后使用sso.wangxt.com访问，结论：sso.wangxt.com只能获取到WXTID2
         *//*
         *//*Cookie cookie = new Cookie("WXTID", "www.wangxt.com");
        cookie.setDomain("www.wangxt.com");
        cookie.setPath("*");
        response.addCookie(cookie);

        cookie = new Cookie("WXTID2", "sso.wangxt.com");
        cookie.setDomain("sso.wangxt.com");
        cookie.setPath("*");
        response.addCookie(cookie);*//*

         *//**
         * 然后只设置顶级域名wangxt.com，使用sso.wangxt.com访问，结论：sso.chuang.com可以获取到其顶级域名下的cookie
         */
        /*Cookie cookie = new Cookie("WXTID", "www.wangxt.com");
        cookie.setDomain("wangxt.com");
        cookie.setPath("*");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);*/

        String id = request.getSession().getId();
        System.out.println(id);
    }
}
