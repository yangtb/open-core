package com.sm.open.core.facade.pf.biz.tests;

import com.sm.open.core.facade.model.param.pf.biz.tests.paper.PfAddCaseParam;
import com.sm.open.core.facade.model.param.pf.biz.tests.plan.ExmTestplanMedicalrecParam;
import com.sm.open.core.facade.model.param.pf.biz.tests.plan.ExmTestplanParam;
import com.sm.open.core.facade.model.param.pf.biz.tests.plan.PfTestPlanParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCatalogueTreeParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfTestPlanFacade
 * @Description: 模拟计划facade
 * @Author yangtongbin
 * @Date 2018/11/2
 */
public interface PfTestPlanFacade {

    /**
     * 计划列表
     *
     * @param param
     * @return
     */
    PfPageResult listPlans(PfTestPlanParam param);

    /**
     * 新增计划
     *
     * @param param
     * @return
     */
    CommonResult<Long> savePlan(ExmTestplanParam param);

    /**
     * 删除计划
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delPlan(PfBachChangeStatusParam param);

    /**
     * 病例tree
     *
     * @param param
     * @return
     */
    CommonResult<List<PfCommonZtreeResult>> listCaseTree(PfCatalogueTreeParam param);

    /**
     * 试题清单列表
     *
     * @param param
     * @return
     */
    PfPageResult listPlanItem(PfTestPlanParam param);

    /**
     * 添加试题清单
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addPlanItem(PfAddCaseParam param);

    /**
     * 删除试题清单
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delPlanItem(PfBachChangeStatusParam param);

    /**
     * 更新排序
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updatePlanItemSort(ExmTestplanMedicalrecParam param);

    /**
     * 班级-学生tree
     *
     * @param param
     * @return
     */
    CommonResult<List<PfCommonZtreeResult>> listStudentTree(PfCatalogueTreeParam param);

    /**
     * 计划学生列表
     *
     * @param param
     * @return
     */
    PfPageResult listPlanStudent(PfTestPlanParam param);

    /**
     * 添加计划学生
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addPlanStudent(PfAddCaseParam param);

    /**
     * 删除计划学生
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delPlanStudent(PfBachChangeStatusParam param);
}
