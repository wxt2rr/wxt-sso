package com.wangxt.wxt.sso.session.controller;

import com.wangxt.wxt.sso.session.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("login")
public class CookieController {

    @Autowired
    private LoginService loginService;

    /**
     * 测试 cookie使用
     * @param request
     * @param response
     */
    @RequestMapping("cookie")
    public void cookie(HttpServletRequest request, HttpServletResponse response) {
        loginService.cookie(request, response);
    }
}
