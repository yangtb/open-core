package com.sm.open.core.facade.pf.biz.tests;

import com.sm.open.core.facade.model.param.pf.biz.tests.plan.ExmTestplanParam;
import com.sm.open.core.facade.model.param.pf.biz.tests.plan.PfTestPlanParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

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

}
