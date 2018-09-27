package com.sm.open.core.service.facade.pf.system.org;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.system.org.PfOrgParam;
import com.sm.open.core.facade.model.param.pf.system.org.SysOrgParam;
import com.sm.open.core.facade.model.result.pf.system.org.SysOrgResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.system.org.PfOrgFacade;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.system.org.PfOrgDto;
import com.sm.open.core.model.entity.SysOrg;
import com.sm.open.core.service.service.pf.system.org.PfOrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfOrgFacade")
public class PfOrgFacadeImpl implements PfOrgFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfOrgFacadeImpl.class);

    @Resource
    private PfOrgService pfOrgService;

    @Override
    public PfPageResult listOrgs(PfOrgParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfOrgDto pfOrgDto = BeanUtil.convert(param, PfOrgDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfOrgService.countOrgs(pfOrgDto),
                    BeanUtil.convertList(pfOrgService.listOrgs(pfOrgDto), SysOrgResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfOrgFacadeImpl-listOrgs-error】获取机构列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfOrgConstant.SELECT_PAGE_ORG_LIST_ERROR, PfOrgConstant.SELECT_PAGE_ORG_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<List<SysOrgResult>> listAllOrg() {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfOrgService.listAllOrg(), SysOrgResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfOrgFacadeImpl-listAllOrg】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfOrgFacadeImpl-listAllOrg-error】查询所有机构失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfOrgConstant.LIST_ALL_ORG_ERROR, PfOrgConstant.LIST_ALL_ORG_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> addOrg(SysOrgParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfOrgService.addOrg(BeanUtil.convert(param, SysOrg.class)) == null ? false : true);
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfOrgFacadeImpl-addOrg】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfOrgFacadeImpl-addOrg-error】新增机构失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfOrgConstant.ADD_ORG_ERROR, PfOrgConstant.ADD_ORG_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editOrg(SysOrgParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfOrgService.editOrg(BeanUtil.convert(param, SysOrg.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfOrgFacadeImpl-editOrg】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfOrgFacadeImpl-editOrg-error】编辑机构失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfOrgConstant.EDIT_ORG_ERROR, PfOrgConstant.EDIT_ORG_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delOrg(PfBachChangeStatusParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfOrgService.delOrg(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfOrgFacadeImpl-delOrg】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfOrgFacadeImpl-delOrg-error】删除机构失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfOrgConstant.DEL_ORG_ERROR, PfOrgConstant.DEL_ORG_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> authOrg(PfBachChangeStatusParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfOrgService.authOrg(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfOrgFacadeImpl-authOrg】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfOrgFacadeImpl-authOrg-error】机构认证失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfOrgConstant.AUTH_ORG_ERROR, PfOrgConstant.AUTH_ORG_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<SysOrgResult> selectOrgInfoById(Long idOrg) {
        try {
            Assert.isTrue(idOrg != null, "idOrg");
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfOrgService.selectOrgInfoById(idOrg), SysOrgResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfOrgFacadeImpl-selectOrgInfoById】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfOrgFacadeImpl-selectOrgInfoById-error】根据id查询机构信息失败, idOrg:" + idOrg, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfOrgConstant.SELECT_ORG_INFO_ERROR, PfOrgConstant.SELECT_ORG_INFO_ERROR_MSG));
        }
    }
}
