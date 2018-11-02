package com.sm.open.core.dal.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.PfTestPlanDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.entity.ExmTestplan;
import com.sm.open.core.model.entity.ExmTestplanMedicalrec;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import org.apache.ibatis.annotations.Param;
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
     * 获取以保存清单
     *
     * @param idTestplan
     * @return
     */
    List<ExmTestplanMedicalrec> listAllPlanItem(@Param("idTestplan") Long idTestplan);

    /**
     * 清空试卷
     *
     * @param idTestplan
     * @return
     */
    Integer delAllPlanItem(@Param("idTestplan") Long idTestplan);

    /**
     * 添加试题清单
     *
     * @param dto
     * @return
     */
    boolean addPlanItem(ExmTestplanMedicalrec dto);

    /**
     * 删除试题清单
     *
     * @param dto
     * @return
     */
    Integer delPlanItem(PfBachChangeStatusDto dto);

    /**
     * 修改试题清单排序
     *
     * @param dto
     * @return
     */
    Integer updatePlanItemSort(ExmTestplanMedicalrec dto);
}
