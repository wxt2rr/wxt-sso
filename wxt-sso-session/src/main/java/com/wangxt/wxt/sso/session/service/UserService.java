package com.wangxt.wxt.sso.session.service;

import com.wangxt.wxt.sso.session.pojo.UserInfoResult;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    public UserInfoResult info(HttpServletRequest request) {
        UserInfoResult wang = UserInfoResult.builder().sid(request.getSession().getId()).username("wang").code(200).build();
        return wang;
    }
}
