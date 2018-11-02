package com.sm.open.core.service.service.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.PfTestPlanDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.ExmTestplan;

import java.util.List;

/**
 * @ClassName: PfTestPlanService
 * @Description: 测试计划
 * @Author yangtongbin
 * @Date 2018/11/2
 */
public interface PfTestPlanService {

    /**
     * 计划列表总数
     *
     * @param dto
     * @return
     */
    Long countPlans(PfTestPlanDto dto);

    /**
     * 计划列表
     *
     * @param dto
     * @return
     */
    List<ExmTestplan> listPlans(PfTestPlanDto dto);

    /**
     * 新增计划
     *
     * @param dto
     * @return
     */
    Long savePlan(ExmTestplan dto);

    /**
     * 删除计划
     *
     * @param dto
     * @return
     */
    boolean delPlan(PfBachChangeStatusDto dto);

}
