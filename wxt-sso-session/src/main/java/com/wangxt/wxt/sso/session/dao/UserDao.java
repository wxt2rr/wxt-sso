package com.wangxt.wxt.sso.session.dao;

import com.wangxt.wxt.sso.session.pojo.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDao {

    private static final Map<Integer, User> userTable = new HashMap<>(10);

    static {
        userTable.put(1, new User(1, "wangxt", "111111", null));
        userTable.put(2, new User(2, "admin", "111111", null));
        userTable.put(3, new User(3, "root", "111111", null));
    }

    public User queryUser(Integer userId) {
        return userTable.get(userId);
    }

    private void updateUsers(User user) {
        userTable.put(user.getUserId(), user);
    }
}
