package com.wangxt.wxt.sso.session.controller;

import com.wangxt.wxt.sso.session.pojo.LoginResult;
import com.wangxt.wxt.sso.session.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("login")
    @ResponseBody
    public LoginResult login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        return loginService.login(request, response, username, password);
    }
}
