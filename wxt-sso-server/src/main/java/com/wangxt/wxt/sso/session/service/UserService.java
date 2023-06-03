package com.wangxt.wxt.sso.session.service;

import com.wangxt.wxt.sso.session.consts.ConstKey;
import com.wangxt.wxt.sso.session.dao.UserDao;
import com.wangxt.wxt.sso.session.pojo.User;
import com.wangxt.wxt.sso.session.pojo.UserInfoResult;
import com.wangxt.wxt.sso.session.util.CacheUtil;
import com.wangxt.wxt.sso.session.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserInfoResult info(HttpServletRequest request) {
        String loginFlag = CookieUtil.getValue(request, ConstKey.LOGIN_FLAG);

        final String key = String.format("%s_%s", ConstKey.LOGIN_FLAG, loginFlag);
        String userId = CacheUtil.get(key);

        User user = userDao.queryUser(Integer.parseInt(userId));
        return UserInfoResult.builder().sid(loginFlag).username(user.getUsername()).loginTime(user.getLoginTime()).code(200).build();
    }
}
