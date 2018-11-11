package com.sm.open.core.dal.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.PfTestPlanDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.entity.ExmTestplan;
import com.sm.open.core.model.entity.ExmTestplanDetail;
import com.sm.open.core.model.entity.ExmTestplanMedicalrec;
import com.sm.open.core.model.entity.ExmTestplanStudent;
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
     * 默认试题
     *
     * @param idTestplan  测试计划ID
     * @param idTestpaper 试题id
     * @return
     */
    boolean defaultPlamItem(@Param("idTestplan") Long idTestplan,
                            @Param("idTestpaper") Long idTestpaper);

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
     * @param dto
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
     * 清空计划学生
     *
     * @param idTestplan
     * @return
     */
    Integer delAllPlanStudent(@Param("idTestplan") Long idTestplan);

    /**
     * 添加计划学生
     *
     * @param list       学生id列表
     * @param idTestplan 测试计划id
     * @return
     */
    Integer addPlanStudent(@Param("list") List<Long> list,
                           @Param("idTestplan") Long idTestplan);

    /**
     * 删除计划学生
     *
     * @param dto
     * @return
     */
    Integer delPlanStudent(PfBachChangeStatusDto dto);

    /**
     * 计划明细列表
     *
     * @param dto
     * @return
     */
    List<ExmTestplanDetail> listPlanDetail(PfTestPlanDto dto);

    /**
     * 获取计划明细
     *
     * @param idTestplan 计划id
     * @return
     */
    List<ExmTestplanDetail> selectPlanDetail(@Param("idTestplan") Long idTestplan);

    /**
     * 插入计划明细
     *
     * @param dto
     * @return
     */
    Integer addPlanDetail(ExmTestplanDetail dto);

    /**
     * 是否存在计划明细判断
     *
     * @param dto
     * @return
     */
    boolean isExistPlanDetail(ExmTestplanDetail dto);

    /**
     * 根据id查询测试计划
     *
     * @param idTestplan 测试计划id
     * @return
     */
    ExmTestplan selectPlanById(@Param("idTestplan") Long idTestplan);
}
