package com.sm.open.core.model.dto.pf.user;

import com.sm.open.core.model.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfUserDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -8538601158882376369L;

    private String type;
    private String conditionValue;
    private Long idOrg;

    /**
     * 获取用户id
     */
    private Long userId;

    /**
     * 用户角色级别
     */
    private int level;

    /**
     * 超级管理员 1 是 0否
     */
    private String fgSuper;
}
