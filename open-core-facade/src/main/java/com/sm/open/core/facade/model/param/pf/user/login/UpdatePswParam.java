package com.sm.open.core.facade.model.param.pf.user.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString(exclude = {"oldPassword", "newPassword"})
public class UpdatePswParam implements Serializable {

    private static final long serialVersionUID = 6379879533868883447L;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 原密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;

}
