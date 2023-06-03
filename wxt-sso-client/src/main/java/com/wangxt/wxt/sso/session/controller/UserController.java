package com.wangxt.wxt.sso.session.controller;

import com.wangxt.wxt.sso.session.pojo.UserInfoResult;
import com.wangxt.wxt.sso.session.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("info")
    @ResponseBody
    public UserInfoResult info(HttpServletRequest request) {
        return userService.info(request);
    }
}
