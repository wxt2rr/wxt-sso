package com.wangxt.wxt.sso.session.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserInfoResult {
    private Integer code;
    private String sid;
    private String username;
    private LocalDateTime loginTime;
}
