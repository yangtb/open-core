package com.sm.open.core.model.dto.pf.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户信息
 */
@Setter
@Getter
@ToString
public class UserDto implements Serializable {

    private static final long serialVersionUID = 8385384321073501601L;

    private Long        userId;         // 管理员ID
    private String      userName;       // 管理员用户名
    private String      email;          // 电邮
    private String      phoneNo;        // 联系电话
    private String      roleType;       // 用户角色类型

}
