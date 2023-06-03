package com.wangxt.wxt.sso.session.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResult {
    private Integer code;
    private String sid;
}
