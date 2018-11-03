package com.sm.open.core.service.service.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.PfAddCaseDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestPlanDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.entity.ExmTestplan;
import com.sm.open.core.model.entity.ExmTestplanMedicalrec;
import com.sm.open.core.model.entity.ExmTestplanStudent;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;

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

    /**
     * 病例树
     *
     * @return
     */
    List<PfCommonZtreeVo> listCaseTree(PfCatalogueTreeDto dto);

    /**
     * 试题清单列表
     *
     * @param dto
     * @return
     */
    List<ExmTestplanMedicalrec> listPlanItem(PfTestPlanDto dto);

    /**
     * 添加试题清单
     *
     * @param dto
     * @return
     */
    boolean addPlanItem(PfAddCaseDto dto);

    /**
     * 删除试题清单
     *
     * @param dto
     * @return
     */
    boolean delPlanItem(PfBachChangeStatusDto dto);

    /**
     * 修改试题清单排序
     *
     * @param dto
     * @return
     */
    boolean updatePlanItemSort(ExmTestplanMedicalrec dto);

    /**
     * 班级-学生tree
     *
     * @param dto
     * @return
     */
    List<PfCommonZtreeVo> listStudentTree(PfCatalogueTreeDto dto);

    /**
     * 计划学生列表
     *
     * @param dto
     * @return
     */
    List<ExmTestplanStudent> listPlanStudent(PfTestPlanDto dto);

    /**
     * 添加计划学生
     *
     * @param dto
     * @return
     */
    boolean addPlanStudent(PfAddCaseDto dto);

    /**
     * 删除计划学生
     *
     * @param dto
     * @return
     */
    boolean delPlanStudent(PfBachChangeStatusDto dto);
}
