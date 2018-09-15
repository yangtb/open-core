package com.sm.open.core.service.service.pf.user.menu;

import com.sm.open.core.model.dto.pf.user.menu.MenuDto;
import com.sm.open.core.model.entity.SysFunction;
import com.sm.open.core.model.vo.pf.user.menu.PfMenuVo;
import com.sm.open.core.model.vo.pf.user.menu.PfMenuZtreeVo;

import java.util.List;

/**
 * @ClassName: PfMenuService
 * @Description: 菜单接口
 * @Author yangtongbin
 * @Date 2017/9/14 10:24
 */
public interface PfMenuService {

    /**
     * 获取菜单
     *
     * @return
     */
    List<SysFunction> listMenus(MenuDto dto);

    /**
     * 菜单总数
     */
    Long countMenus(MenuDto dto);

    /**
     * 新增菜单
     *
     * @param dto
     * @return
     */
    boolean addMenu(SysFunction dto);

    /**
     * 判断是否存在该菜单
     *
     * @param code 菜单code
     * @return
     */
    boolean isExistMenu(String code);

    /**
     * 修改菜单状态
     *
     * @param list
     * @param status
     * @return
     */
    boolean changeStatusMenu(List<Long> list, String status);

    /**
     * 编辑菜单
     *
     * @param dto
     * @return
     */
    boolean updateMenu(SysFunction dto);

    /**
     * 获取菜单[tree]
     *
     * @return
     */
    List<PfMenuZtreeVo> listMenuTree();

    /**
     * 获取角色拥有菜单
     *
     * @param roleId 角色ID
     * @return
     */
    List<PfMenuZtreeVo> listMenuRoleTree(Long roleId);

    /**
     * 获取用户菜单
     *
     * @param userId 用户id
     * @return
     */
    List<PfMenuVo> listMyMenus(boolean isSuper, Long userId);


}
