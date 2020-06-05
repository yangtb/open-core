package com.sm.open.core.facade.model.param.pf.home;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfHomeParam
 * @Description: 首页服务入参
 * @Author yangtongbin
 * @Date 2018/9/26
 */
@Setter
@Getter
@ToString
public class PfHomeParam implements Serializable {

    /**
     * 超级管理员
     */
    private boolean isSuper;
    /**
     * 匿名用户
     */
    private boolean isAnonymousUser;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 当前用户所在机构id
     */
    private Long idOrg;
}
