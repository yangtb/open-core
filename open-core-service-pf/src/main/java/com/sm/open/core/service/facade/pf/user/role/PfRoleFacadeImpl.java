package com.sm.open.core.service.facade.pf.user.role;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.user.role.PfRoleListParam;
import com.sm.open.core.facade.model.param.pf.user.role.PfRoleMenuParam;
import com.sm.open.core.facade.model.param.pf.user.role.PfRoleParam;
import com.sm.open.core.facade.model.param.pf.user.role.SysRoleParam;
import com.sm.open.core.facade.model.result.pf.user.role.PfRoleResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.user.role.PfRoleFacade;
import com.sm.open.core.model.dto.pf.user.role.PfRoleDto;
import com.sm.open.core.model.entity.SysRole;
import com.sm.open.core.model.entity.SysRoleMenu;
import com.sm.open.core.service.facade.pf.user.login.PfUserConstant;
import com.sm.open.core.service.service.pf.user.role.PfRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component("pfRoleFacade")
public class PfRoleFacadeImpl implements PfRoleFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfRoleFacadeImpl.class);

    @Resource
    private PfRoleService pfRoleService;

    @Override
    public PfPageResult<PfRoleResult> listRoles(PfRoleParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfRoleDto pfRoleDto = BeanUtil.convert(param, PfRoleDto.class);

            return PfResultFactory.initPagePfResultWithSuccess(pfRoleService.countRoles(pfRoleDto),
                    BeanUtil.convertList(pfRoleService.listRoles(pfRoleDto), PfRoleResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfRoleFacadeImpl-listRoles-error】获取系统角色列表失败，param={}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfUserConstant.SELECT_PAGE_USER_LIST_ERROR, PfUserConstant.SELECT_PAGE_USER_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<List<PfRoleResult>> list() {
        try {
            return ResultFactory.initCommonResultWithSuccess(BeanUtil.convertList(pfRoleService.list(), PfRoleResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfRoleFacadeImpl-list】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfRoleFacadeImpl-list-error】获取所有角色失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfRoleConstant.LIST_ALL_ROLE_FAILED, PfRoleConstant.LIST_ALL_ROLE_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<List<PfRoleResult>> listUserRole(Long userId) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfRoleService.listUserRole(userId), PfRoleResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfRoleFacadeImpl-listUserRole】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfRoleFacadeImpl-listUserRole-error】获取用户所有角色失败, userId=" + userId, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfRoleConstant.LIST_USER_ROLE_FAILED, PfRoleConstant.LIST_USER_ROLE_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> addRole(SysRoleParam param) {
        try {
            if (pfRoleService.isExistRole(param.getName())) {
                throw new BizRuntimeException(PfRoleConstant.ADD_ROLE_ISEXIST, PfRoleConstant.ADD_ROLE_ISEXIST_MSG);
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfRoleService.addRole(BeanUtil.convert(param, SysRole.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfRoleFacadeImpl-addRole】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfRoleFacadeImpl-addRole-error】新增系统角色失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfRoleConstant.ADD_ROLE_FAILED, PfRoleConstant.ADD_ROLE_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editRole(SysRoleParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfRoleService.editRole(BeanUtil.convert(param, SysRole.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfRoleFacadeImpl-editRole】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfRoleFacadeImpl-editRole-error】编辑系统角色失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfRoleConstant.EDIT_ROLE_FAILED, PfRoleConstant.EDIT_ROLE_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delRole(List<Long> roles) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfRoleService.delRole(roles));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfRoleFacadeImpl-delRole】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfRoleFacadeImpl-delRole-error】删除角色失败, roles=" + roles.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfRoleConstant.DEL_ROLE_FAILED, PfRoleConstant.DEL_ROLE_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> cancelRole(PfRoleListParam pfRoleListParam) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfRoleService.cancelRole(BeanUtil.convertList(pfRoleListParam.getRoles(), SysRole.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfRoleFacadeImpl-cancelRole】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfRoleFacadeImpl-cancelRole-error】作废/恢复角色失败, param={}", pfRoleListParam.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfRoleConstant.CANCEL_ROLE_FAILED, PfRoleConstant.CANCEL_ROLE_FAILED_MSG));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Boolean> saveRoleMenu(PfRoleMenuParam param) {
        try {
            pfRoleService.delRoleMenu(param.getRoleMenus().get(0).getRoleId());
            pfRoleService.saveRoleMenu(BeanUtil.convertList(param.getRoleMenus(), SysRoleMenu.class));
            return ResultFactory.initCommonResultWithSuccess(true);
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfRoleFacadeImpl-saveRoleMenu】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfRoleFacadeImpl-saveRoleMenu-error】保存角色菜单失败, param={}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfRoleConstant.SAVE_ROLE_MENU_FAILED, PfRoleConstant.SAVE_ROLE_MENU_FAILED_MSG));
        }
    }


}
