package com.sm.open.core.facade.pf.user.menu;

import com.sm.open.core.facade.model.param.pf.system.auth.SysFunctionParam;
import com.sm.open.core.facade.model.param.pf.system.menu.PfMenuListParam;
import com.sm.open.core.facade.model.param.pf.user.menu.MenuParam;
import com.sm.open.core.facade.model.result.pf.system.auth.SysFunctionResult;
import com.sm.open.core.facade.model.result.pf.user.menu.PfMenuResult;
import com.sm.open.core.facade.model.result.pf.user.menu.PfMenuZtreeResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfMenuFacade
 * @Description: 菜单接口
 * @Author yangtongbin
 * @Date 2017/9/14 09:40
 */
public interface PfMenuFacade {

    /**
     * 获取菜单
     *
     * @return
     */
    PfPageResult<SysFunctionResult> listMenus(MenuParam param);

    /**
     * 新增菜单
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addMenu(SysFunctionParam param);

    /**
     * 修改菜单状态
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> changeStatusMenu(PfMenuListParam param);

    /**
     * 编辑菜单
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updateMenu(SysFunctionParam param);

    /**
     * 获取菜单[tree]
     *
     * @return
     */
    CommonResult<List<PfMenuZtreeResult>> listMenuTree();

    /**
     * 获取角色拥有菜单
     *
     * @param roleId 角色id
     * @return
     */
    CommonResult<List<PfMenuZtreeResult>> listMenuRoleTree(Long roleId);

    /**
     * 获取用户菜单
     *
     * @param isSuper         是否是超级管理员
     * @param isAnonymousUser 匿名用户
     * @param userId          用户id
     * @return
     */
    CommonResult<List<PfMenuResult>> listMyMenus(boolean isSuper,
                                                 boolean isAnonymousUser,
                                                 Long userId);

}
