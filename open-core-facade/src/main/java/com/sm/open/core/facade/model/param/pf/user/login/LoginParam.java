package com.sm.open.core.facade.model.param.pf.user.login;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 登陆dto
 */
@Setter
@Getter
@ToString
public class LoginParam implements Serializable {

    private static final long serialVersionUID = 4144204267241614789L;

    @JSONField(name = "user_name")
    private String      userName;       // 管理员用户名
    private String      password;       // 密码

}
