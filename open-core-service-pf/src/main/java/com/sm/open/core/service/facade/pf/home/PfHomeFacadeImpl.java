package com.sm.open.core.service.facade.pf.home;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.home.PfHomeParam;
import com.sm.open.core.facade.model.result.pf.home.PfHomeResult;
import com.sm.open.core.facade.model.result.pf.system.org.SysOrgResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.ResultFactory;
import com.sm.open.core.facade.pf.home.PfHomeFacade;
import com.sm.open.core.model.entity.SysOrg;
import com.sm.open.core.model.vo.pf.user.menu.PfMenuVo;
import com.sm.open.core.service.facade.pf.user.menu.PfMenuBeanUtils;
import com.sm.open.core.service.service.pf.system.org.PfOrgService;
import com.sm.open.core.service.service.pf.user.menu.PfMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("pfHomeFacade")
public class PfHomeFacadeImpl implements PfHomeFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfHomeFacadeImpl.class);

    @Resource
    private PfMenuService pfMenuService;

    @Resource
    private PfOrgService pfOrgService;

    @Override
    public CommonResult<PfHomeResult> selectHomeInfo(PfHomeParam param) {
        PfHomeResult result = new PfHomeResult();
        result.setAnonymousUser(param.isAnonymousUser());
        try {
            // 菜单
            List<PfMenuVo> menuVos = pfMenuService.listMyMenus(param.isSuper(), param.isAnonymousUser(), param.getUserId());
            Map<String, List<PfMenuVo>> menus = menuVos.stream().collect(Collectors.groupingBy(PfMenuVo::getPosition));
            result.setTopMenus(PfMenuBeanUtils.convertMyMenuList(menus.get("top")));
            result.setLeftMenus(PfMenuBeanUtils.convertMyMenuList(menus.get("left")));

            // 机构
            if (param.getIdOrg() != null) {
                SysOrg sysOrg = pfOrgService.selectOrgInfoById(param.getIdOrg());
                result.setSysOrg(BeanUtil.convert(sysOrg, SysOrgResult.class));
            }
            return ResultFactory.initCommonResultWithSuccess(result);
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfHomeFacadeImpl-selectHomeInfo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfHomeFacadeImpl-selectHomeInfo-error】获取用户首页信息失败，param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfHomeConstant.SELECT_HOME_INFO_ERROR, PfHomeConstant.SELECT_HOME_INFO_ERROR_MSG));
        }
    }


}
