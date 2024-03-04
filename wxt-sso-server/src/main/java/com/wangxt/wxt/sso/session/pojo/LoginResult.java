package com.wangxt.wxt.sso.session.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
public class LoginResult {
    private Integer code;
    private String sid;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date memberExpire = new Date();
    private Date date = new Date();
}
