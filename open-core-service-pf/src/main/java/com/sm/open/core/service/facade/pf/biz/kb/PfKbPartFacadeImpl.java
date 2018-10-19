package com.sm.open.core.service.facade.pf.biz.kb;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.kb.part.FaqMedCaseParam;
import com.sm.open.core.facade.model.param.pf.biz.kb.part.PfMedCaseParam;
import com.sm.open.core.facade.model.param.pf.biz.kb.part.PfPartCommonParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseInquesListResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.kb.PfKbPartFacade;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfMedCaseDto;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfPartCommonDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.FaqMedCase;
import com.sm.open.core.service.service.pf.biz.kb.PfKbPartService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfKbPartFacade")
public class PfKbPartFacadeImpl implements PfKbPartFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfKbPartFacadeImpl.class);

    @Resource
    private PfKbPartService pfKbPartService;

    @Override
    public PfPageResult listKbPart(PfMedCaseParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfMedCaseDto dto = BeanUtil.convert(param, PfMedCaseDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfKbPartService.countKbPart(dto),
                    BeanUtil.convertList(pfKbPartService.listKbPart(dto), FaqMedCaseResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listKbPart-error】获取病例组件用例列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbPartConstant.PAGE_KB_PART_LIST_ERROR, PfKbPartConstant.PAGE_KB_PART_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> addKbPart(FaqMedCaseParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.addKbPart(BeanUtil.convert(param, FaqMedCase.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-addKbPart】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-addKbPart】新增病例组件用例失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.ADD_KB_PART_ERROR, PfKbPartConstant.ADD_KB_PART_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editKbPart(FaqMedCaseParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.editKbPart(BeanUtil.convert(param, FaqMedCase.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-editKbPart】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-editKbPart】编辑病例组件用例失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.EDIT_KB_PART_ERROR, PfKbPartConstant.EDIT_KB_PART_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delKbPart(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.delKbPart(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-delKbPart】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-delKbPart】删除病例组件用例失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.DEL_KB_PART_ERROR, PfKbPartConstant.DEL_KB_PART_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listFaqMedCaseInques(PfPartCommonParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfPartCommonDto dto = BeanUtil.convert(param, PfPartCommonDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfKbPartService.countFaqMedCaseInques(dto),
                    BeanUtil.convertList(pfKbPartService.listFaqMedCaseInques(dto), FaqMedCaseInquesListResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listFaqMedCaseInques-error】获取问诊问题明细列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbPartConstant.LIST_FAQ_MED_CASE_INQUES_ERROR, PfKbPartConstant.LIST_FAQ_MED_CASE_INQUES_ERROR_MSG);
        }
    }
}
