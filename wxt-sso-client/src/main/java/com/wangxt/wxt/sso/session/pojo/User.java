package com.wangxt.wxt.sso.session.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Integer userId;
    private String username;
    private String password;
    private LocalDateTime loginTime;
}
