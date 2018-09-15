package com.sm.open.core.service.facade.pf.system.param;

import com.sm.open.care.core.annotation.Loggable;
import com.sm.open.care.core.enums.Level;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.system.param.Param;
import com.sm.open.core.facade.model.param.pf.system.param.PfParamListParam;
import com.sm.open.core.facade.model.param.pf.system.param.SysParaPram;
import com.sm.open.core.facade.model.result.pf.system.param.SysParamResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.system.param.PfParamFacade;
import com.sm.open.core.model.dto.pf.system.param.ParamDto;
import com.sm.open.core.model.entity.SysParam;
import com.sm.open.core.service.service.pf.system.param.PfParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfParamFacade")
public class PfParamFacadeImpl implements PfParamFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfParamFacadeImpl.class);

    @Resource
    private PfParamService pfParamService;

    @Override
    public PfPageResult<SysParamResult> listParams(Param param) {
        try {
            PfPageParam.initPageDto(param);
            ParamDto paramDto = BeanUtil.convert(param, ParamDto.class);

            return PfResultFactory.initPagePfResultWithSuccess(pfParamService.count(paramDto),
                    BeanUtil.convertList(pfParamService.listParams(paramDto), SysParamResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfParamFacadeImpl-listParams-error】获取参数列表失败，param={}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfParamConstant.SELECT_PAGE_PARAM_ERROR, PfParamConstant.SELECT_PAGE_PARAM_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addParam(SysParaPram param) {
        try {
            if (pfParamService.isExistParam(param.getParamCode(), param.getSysType())) {
                throw new BizRuntimeException(PfParamConstant.ADD_PARAM_ISEXIST, PfParamConstant.ADD_PARAM_ISEXIST_MSG);
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfParamService.addParam(BeanUtil.convert(param, SysParam.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfParamFacadeImpl-addParam】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfParamFacadeImpl-addParam-error】新增参数失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfParamConstant.ADD_PARAM_ERROR, PfParamConstant.ADD_PARAM_ERROR_MSG));
        }
    }

    @Loggable(bizDec = "编辑参数", level = Level.info)
    @Override
    public CommonResult<Boolean> editParam(SysParaPram param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfParamService.editParam(BeanUtil.convert(param, SysParam.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfParamFacadeImpl-editParam】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfParamFacadeImpl-editParam-error】编辑参数失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfParamConstant.EDIT_PARAM_ERROR, PfParamConstant.EDIT_PARAM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> changeStatus(PfParamListParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfParamService.changeStatus(param.getList(), param.getStatus()));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfParamFacadeImpl-changeStatus】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfParamFacadeImpl-changeStatus-error】停用、启用参数失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfParamConstant.CHANGE_PARAM_STATUS_ERROR, PfParamConstant.CHANGE_PARAM_STATUS_ERROR_MSG));
        }
    }
}
