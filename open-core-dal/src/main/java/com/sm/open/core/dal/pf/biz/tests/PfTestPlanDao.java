package com.sm.open.core.dal.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.PfTestPlanDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.ExmTestplan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfTestPlanDao {

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
    Integer addPlan(ExmTestplan dto);

    /**
     * 编辑计划
     *
     * @param dto
     * @return
     */
    Integer editPlan(ExmTestplan dto);

    /**
     * 删除计划
     *
     * @param dto
     * @return
     */
    Integer delPlan(PfBachChangeStatusDto dto);
}
