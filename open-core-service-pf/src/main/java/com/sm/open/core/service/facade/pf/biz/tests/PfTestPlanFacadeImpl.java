package com.sm.open.core.service.facade.pf.biz.tests;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.tests.paper.PfAddCaseParam;
import com.sm.open.core.facade.model.param.pf.biz.tests.plan.ExmTestplanMedicalrecParam;
import com.sm.open.core.facade.model.param.pf.biz.tests.plan.ExmTestplanParam;
import com.sm.open.core.facade.model.param.pf.biz.tests.plan.PfTestPlanParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCatalogueTreeParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.plan.ExmTestplanMedicalrecResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.plan.ExmTestplanResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.tests.PfTestPlanFacade;
import com.sm.open.core.model.dto.pf.biz.tests.PfAddCaseDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestPlanDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.entity.ExmTestplan;
import com.sm.open.core.model.entity.ExmTestplanMedicalrec;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.service.service.pf.biz.kb.PfCaseHistoryService;
import com.sm.open.core.service.service.pf.biz.tests.PfTestPlanService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfTestPlanFacade")
public class PfTestPlanFacadeImpl implements PfTestPlanFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfTestPlanFacadeImpl.class);

    @Resource
    private PfTestPlanService pfTestPlanService;

    @Resource
    private PfCaseHistoryService pfCaseHistoryService;

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

    @Override
    public CommonResult<List<PfCommonZtreeResult>> listCaseTree(PfCatalogueTreeParam param) {
        try {
            List<PfCommonZtreeVo> catalogueList = pfCaseHistoryService.listClassifyTree();
            catalogueList.forEach(pfCommonZtreeVo -> pfCommonZtreeVo.setNocheck(true));
            List<PfCommonZtreeVo> planCaseList = pfTestPlanService.listCaseTree(BeanUtil.convert(param, PfCatalogueTreeDto.class));
            catalogueList.addAll(planCaseList);

            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(catalogueList, PfCommonZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPlanFacadeImpl-listCaseTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPlanFacadeImpl-listCaseTree】查询病例tree失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_CASE_CLASSIFY_TREE_ERROR,
                    PfTestPaperConstant.LIST_CASE_CLASSIFY_TREE_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listPlanItem(PfTestPlanParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfTestPlanDto dto = BeanUtil.convert(param, PfTestPlanDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfTestPlanService.listPlanItem(dto), ExmTestplanMedicalrecResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfTestPlanFacadeImpl-listPlanItem-error】获取试卷清单失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.PAGE_PAPER_ITEM_LIST_ERROR, PfTestPaperConstant.PAGE_PAPER_ITEM_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addPlanItem(PfAddCaseParam param) {
        try {
            Assert.isTrue(param.getIdTestplan() != null, "idTestplan");
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "list");
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPlanService.addPlanItem(BeanUtil.convert(param, PfAddCaseDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPlanFacadeImpl-addPlanItem】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPlanFacadeImpl-addPlanItem】新增试卷清单失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_PAPER_ITEM_ERROR, PfTestPaperConstant.SAVE_PAPER_ITEM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delPlanItem(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPlanService.delPlanItem(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPlanFacadeImpl-delPlanItem】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPlanFacadeImpl-delPlanItem】删除试卷清单失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.DEL_PAPER_ITEM_ERROR, PfTestPaperConstant.DEL_PAPER_ITEM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> updatePlanItemSort(ExmTestplanMedicalrecParam param) {
        try {
            Assert.isTrue(param.getIdTestplanMedicalrec() != null, "idTestplanMedicalrec");
            Assert.isTrue(param.getSort() != null, "sort");

            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPlanService.updatePlanItemSort(BeanUtil.convert(param, ExmTestplanMedicalrec.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPlanFacadeImpl-updatePaperItemSort】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPlanFacadeImpl-updatePaperItemSort】修改试卷清单排序失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.UPDATE_PAPER_ITEM_SORT_ERROR, PfTestPaperConstant.UPDATE_PAPER_ITEM_SORT_ERROR_MSG));
        }
    }
}