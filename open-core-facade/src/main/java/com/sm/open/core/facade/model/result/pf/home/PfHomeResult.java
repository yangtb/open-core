package com.sm.open.core.facade.model.result.pf.home;


import com.sm.open.core.facade.model.result.pf.system.org.SysOrgResult;
import com.sm.open.core.facade.model.result.pf.user.menu.PfMenuResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangtongbin
 * @className: PfHomeVo
 * @description: 首页信息
 * @date 2018/9/26
 */
@Setter
@Getter
@ToString
public class PfHomeResult implements Serializable {

    /**
     * 顶部菜单
     */
    private List<PfMenuResult> topMenus;
    /**
     * 左侧菜单
     */
    private List<PfMenuResult> leftMenus;
    /**
     * 当前登陆用户名
     */
    private String username;
    /**
     * 机构信息
     */
    private SysOrgResult sysOrg;
    /**
     * 是否匿名用户
     */
    private boolean isAnonymousUser;
    /**
     * 网站名称
     */
    private String websiteName;
    /**
     * 版权信息
     */
    private String websiteCopyright;

    /**
     * 过期提示标识 1提示
     */
    private String expireNotice;
}
