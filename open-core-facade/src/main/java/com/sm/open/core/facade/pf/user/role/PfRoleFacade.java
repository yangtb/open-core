package com.sm.open.core.facade.pf.user.role;

import com.sm.open.core.facade.model.param.pf.user.role.PfRoleListParam;
import com.sm.open.core.facade.model.param.pf.user.role.PfRoleMenuParam;
import com.sm.open.core.facade.model.param.pf.user.role.PfRoleParam;
import com.sm.open.core.facade.model.param.pf.user.role.SysRoleParam;
import com.sm.open.core.facade.model.result.pf.user.role.PfRoleResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfRoleFacade
 * @Description: 角色接口
 * @Author yangtongbin
 * @Date 2017/9/17 22:23
 */
public interface PfRoleFacade {

    /**
     * 角色列表
     *
     * @param param
     * @return
     */
    PfPageResult<PfRoleResult> listRoles(PfRoleParam param);

    /**
     * 查询角色列表
     *
     * @return
     */
    CommonResult<List<PfRoleResult>> list();

    /**
     * 查询用户角色
     *
     * @param userId 用户id
     * @return
     */
    CommonResult<List<PfRoleResult>> listUserRole(Long userId);

    /**
     * 新增角色
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addRole(SysRoleParam param);

    /**
     * 编辑菜单
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editRole(SysRoleParam param);

    /**
     * 删除角色
     *
     * @param roles 角色ID
     * @return
     */
    CommonResult<Boolean> delRole(List<Long> roles);

    /**
     * 作废/恢复角色
     *
     * @param roles
     * @return
     */
    CommonResult<Boolean> cancelRole(PfRoleListParam roles);

    /**
     * 保存角色菜单
     *
     * @param param
     */
    CommonResult<Boolean> saveRoleMenu(PfRoleMenuParam param);
}
