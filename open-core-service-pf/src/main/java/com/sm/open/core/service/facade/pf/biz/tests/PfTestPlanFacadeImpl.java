package com.sm.open.core.service.facade.pf.biz.tests;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.tests.plan.ExmTestplanParam;
import com.sm.open.core.facade.model.param.pf.biz.tests.plan.PfTestPlanParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.tests.plan.ExmTestplanResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.tests.PfTestPlanFacade;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestPlanDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.ExmTestplan;
import com.sm.open.core.service.service.pf.biz.tests.PfTestPlanService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfTestPlanFacade")
public class PfTestPlanFacadeImpl implements PfTestPlanFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfTestPlanFacadeImpl.class);

    @Resource
    private PfTestPlanService pfTestPlanService;

    @Override
    public PfPageResult listPlans(PfTestPlanParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfTestPlanDto dto = BeanUtil.convert(param, PfTestPlanDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfTestPlanService.countPlans(dto),
                    BeanUtil.convertList(pfTestPlanService.listPlans(dto), ExmTestplanResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfTestPlanFacadeImpl-listPlans-error】获取测试计划列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.PAGE_PLAN_LIST_ERROR, PfTestPaperConstant.PAGE_PLAN_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> savePlan(ExmTestplanParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getNaTestplan()), "naTestplan");
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPlanService.savePlan(BeanUtil.convert(param, ExmTestplan.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPlanFacadeImpl-savePlan】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPlanFacadeImpl-savePlan】新增计划失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_PLAN_ERROR, PfTestPaperConstant.SAVE_PLAN_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delPlan(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPlanService.delPlan(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPlanFacadeImpl-delPlan】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPlanFacadeImpl-delPlan】删除测试计划失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.DEL_PLAN_ERROR, PfTestPaperConstant.DEL_PLAN_ERROR_MSG));
        }
    }
}
