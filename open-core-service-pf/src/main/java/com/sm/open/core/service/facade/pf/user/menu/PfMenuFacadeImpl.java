package com.sm.open.core.service.facade.pf.user.menu;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.system.auth.SysFunctionParam;
import com.sm.open.core.facade.model.param.pf.system.menu.PfMenuListParam;
import com.sm.open.core.facade.model.param.pf.user.menu.MenuParam;
import com.sm.open.core.facade.model.result.pf.system.auth.SysFunctionResult;
import com.sm.open.core.facade.model.result.pf.user.menu.PfMenuResult;
import com.sm.open.core.facade.model.result.pf.user.menu.PfMenuZtreeResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.user.menu.PfMenuFacade;
import com.sm.open.core.model.dto.pf.user.menu.MenuDto;
import com.sm.open.core.model.entity.SysFunction;
import com.sm.open.core.model.vo.pf.user.menu.PfMenuZtreeVo;
import com.sm.open.core.service.service.pf.user.menu.PfMenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfMenuFacade")
public class PfMenuFacadeImpl implements PfMenuFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfMenuFacadeImpl.class);

    @Resource
    private PfMenuService pfMenuService;

    @Override
    public PfPageResult<SysFunctionResult> listMenus(MenuParam param) {
        try {
            PfPageParam.initPageDto(param);
            MenuDto menuDto = BeanUtil.convert(param, MenuDto.class);

            return PfResultFactory.initPagePfResultWithSuccess(pfMenuService.countMenus(menuDto),
                    BeanUtil.convertList(pfMenuService.listMenus(menuDto), SysFunctionResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfMenuFacadeImpl-listMenus-error】获取系统菜单列表失败，param={}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfMenuConstant.SELECT_PAGE_MENU_ERROR, PfMenuConstant.SELECT_PAGE_MENU_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addMenu(SysFunctionParam param) {
        try {
            if (pfMenuService.isExistMenu(param.getCode())) {
                throw new BizRuntimeException(PfMenuConstant.ADD_MENU_ISEXIST, PfMenuConstant.ADD_MENU_ISEXIST_MSG);
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfMenuService.addMenu(BeanUtil.convert(param, SysFunction.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfMenuFacadeImpl-addMenu】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfMenuFacadeImpl-addMenu-error】新增系统菜单失败，param={}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfMenuConstant.ADD_MENU_FAILED, PfMenuConstant.ADD_MENU_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> changeStatusMenu(PfMenuListParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfMenuService.changeStatusMenu(param.getList(), param.getStatus()));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfMenuFacadeImpl-changeStatusMenu】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfMenuFacadeImpl-changeStatusMenu-error】修改系统菜单状态失败，param={}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfMenuConstant.CHANGE_MENU_STATUS_FAILED, PfMenuConstant.CHANGE_MENU_STATUS_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> updateMenu(SysFunctionParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfMenuService.updateMenu(BeanUtil.convert(param, SysFunction.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfMenuFacadeImpl-updateMenu】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfMenuFacadeImpl-updateMenu-error】修改系统菜单失败，param={}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfMenuConstant.UPDATE_MENU_FAILED, PfMenuConstant.UPDATE_MENU_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<List<PfMenuZtreeResult>> listMenuTree() {
        try {
            List<PfMenuZtreeVo> menuZtreeVos = pfMenuService.listMenuTree();
            for (PfMenuZtreeVo pfMenuZtreeVo : menuZtreeVos) {
                if (StringUtils.isNotBlank(pfMenuZtreeVo.getPId())) {
                    continue;
                }
                pfMenuZtreeVo.setPId(pfMenuZtreeVo.getPosition());
            }
            // 左侧菜单
            PfMenuZtreeVo leftMenu = new PfMenuZtreeVo();
            leftMenu.setMenuId(-1L);
            leftMenu.setId("left");
            leftMenu.setName("左侧");
            leftMenu.setOpen(true);
            menuZtreeVos.add(leftMenu);
            // 顶部菜单
            PfMenuZtreeVo topMenu = new PfMenuZtreeVo();
            topMenu.setMenuId(-2L);
            topMenu.setId("top");
            topMenu.setName("顶部");
            topMenu.setOpen(true);
            menuZtreeVos.add(topMenu);

            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(menuZtreeVos, PfMenuZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfMenuFacadeImpl-listMenuTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfMenuFacadeImpl-listMenuTree-error】获取系统菜单【tree】失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfMenuConstant.LIST_MENU_TREE_FAILED, PfMenuConstant.LIST_MENU_TREE_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<List<PfMenuZtreeResult>> listMenuRoleTree(Long roleId) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfMenuService.listMenuRoleTree(roleId), PfMenuZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfMenuFacadeImpl-listMenuRoleTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfMenuFacadeImpl-listMenuRoleTree-error】获取角色拥有菜单失败，roleId={}", roleId, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfMenuConstant.LIST_MENU_ROLE_TREE_FAILED, PfMenuConstant.LIST_MENU_ROLE_TREE_FAILED_MSG));
        }
    }

    @Override
    public CommonResult<List<PfMenuResult>> listMyMenus(boolean isSuper, boolean isAnonymousUser, Long userId) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    PfMenuBeanUtils.convertMyMenuList(pfMenuService.listMyMenus(isSuper, isAnonymousUser, userId)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfMenuFacadeImpl-listMyMenus】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfMenuFacadeImpl-listMyMenus-error】获取用户菜单失败，isSuper=" + isSuper + ", userId=" + userId, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfMenuConstant.LIST_MY_MENUS_FAILED, PfMenuConstant.LIST_MY_MENUS_FAILED_MSG));
        }
    }


}
