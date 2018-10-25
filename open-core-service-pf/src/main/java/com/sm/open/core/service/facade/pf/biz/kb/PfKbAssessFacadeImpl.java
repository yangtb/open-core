package com.sm.open.core.service.facade.pf.biz.kb;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.kb.assess.FaqEvaCaseParam;
import com.sm.open.core.facade.model.param.pf.biz.kb.assess.PfEvaCaseParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.kb.assess.FaqEvaCaseResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.kb.PfKbAssessFacade;
import com.sm.open.core.model.dto.pf.biz.kb.assess.PfEvaCaseDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.FaqEvaCase;
import com.sm.open.core.service.service.pf.biz.kb.PfKbAssessService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfKbAssessFacade")
public class PfKbAssessFacadeImpl implements PfKbAssessFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfKbAssessFacadeImpl.class);

    @Resource
    private PfKbAssessService pfKbAssessService;

    @Override
    public PfPageResult listKbAssess(PfEvaCaseParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfEvaCaseDto dto = BeanUtil.convert(param, PfEvaCaseDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfKbAssessService.countKbAssess(dto),
                    BeanUtil.convertList(pfKbAssessService.listKbAssess(dto), FaqEvaCaseResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listKbAssess-error】获取评估组件用例列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbAssessConstant.PAGE_KB_ASSESS_LIST_ERROR, PfKbAssessConstant.PAGE_KB_ASSESS_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> saveKbAssess(FaqEvaCaseParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.saveKbAssess(BeanUtil.convert(param, FaqEvaCase.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-saveKbAssess】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-saveKbAssess】保存评估组件用例失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.ADD_KB_ASSESS_ERROR, PfKbAssessConstant.ADD_KB_ASSESS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delKbAssess(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.delKbAssess(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-delKbAssess】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-delKbAssess】删除评估组件用例失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.DEL_KB_ASSESS_ERROR, PfKbAssessConstant.DEL_KB_ASSESS_ERROR_MSG));
        }
    }

}
