package com.sm.open.core.model.dto.pf.user.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class UpdatePswDto implements Serializable {

    private static final long serialVersionUID = 6379879533868883447L;

    private Long        userId;          // 用户id
    private String      userName;        // 用户名
    private String      oldPassword;     // 原密码
    private String      newPassword;     // 新密码

}
