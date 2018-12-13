package com.sm.open.core.dal.pf.biz.clinic;

import com.sm.open.core.model.dto.pf.biz.clinic.PfClinicTemplateDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.clinic.BasEvaTagVo;
import com.sm.open.core.model.vo.pf.biz.clinic.BasMedicalTagVo;
import com.sm.open.core.model.vo.pf.biz.clinic.PfAssessTagVo;
import com.sm.open.core.model.vo.pf.biz.clinic.PfCaseHistoryTagVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PfClinicTemplateDao
 * @Description: 临床模板定义dao
 * @Author yangtongbin
 * @Date 2018/10/8
 */
@Repository
public interface PfClinicTemplateDao {

    /**
     * 分类树
     *
     * @return
     */
    List<PfCommonZtreeVo> listClassifyTree();

    /**
     * 新增分类信息
     *
     * @param dto
     * @return
     */
    Long addClassify(BasDemoCa dto);

    /**
     * 编辑分类信息
     *
     * @param dto
     * @return
     */
    Integer editClassify(BasDemoCa dto);

    /**
     * 删除分类信息
     *
     * @param dto
     * @return
     */
    Integer delClassify(PfBachChangeStatusDto dto);

    /**
     * 模板总数
     *
     * @param dto
     * @return
     */
    Long countTemplate(PfClinicTemplateDto dto);

    /**
     * 模板列表
     *
     * @param dto
     * @return
     */
    List<BasDemo> listTemplate(PfClinicTemplateDto dto);

    /**
     * 新增模板
     *
     * @param dto
     * @return
     */
    Integer addTemplate(BasDemo dto);

    /**
     * 编辑模板
     *
     * @param dto
     * @return
     */
    Integer editTemplate(BasDemo dto);

    /**
     * 删除模板
     *
     * @param dto
     * @return
     */
    Integer delTemplate(PfBachChangeStatusDto dto);

    /**
     * 病例标签列表
     *
     * @param dto
     * @return
     */
    List<BasMedicalTagVo> listCaseHistoryTag(PfClinicTemplateDto dto);

    /**
     * 删除病例标签信息
     *
     * @param dto
     * @return
     */
    Integer delCaseHistoryTag(PfBachChangeStatusDto dto);

    /**
     * 保存病例标签信息
     *
     * @param dto
     * @return
     */
    Long saveCaseHistoryTag(BasMedicalTag dto);

    /**
     * 编辑病例标签信息
     *
     * @param dto
     * @return
     */
    Long editCaseHistoryTag(BasMedicalTag dto);

    /**
     * 标签列表
     *
     * @param dto
     * @return
     */
    List<BasEvaTagVo> listSheetTag(PfClinicTemplateDto dto);

    /**
     * 删除标签信息
     *
     * @param dto
     * @return
     */
    Integer delSheetTag(PfBachChangeStatusDto dto);

    /**
     * 保存标签信息
     *
     * @param dto
     * @return
     */
    Integer saveSheetTag(BasEvaTag dto);

    /**
     * 编辑标签信息
     *
     * @param dto
     * @return
     */
    Integer editSheetTag(BasEvaTag dto);

    /**
     * 获取所有病例模板
     *
     * @return
     */
    List<BasDemo> listAllBasDemo();

    /**
     * 评价维度分类tree
     *
     * @param idDemo
     * @return
     */
    List<PfCommonZtreeVo> listDimensionTree(@Param("idDemo") Long idDemo);

    /**
     * 删除评估维度
     *
     * @param dto
     * @return
     */
    Integer delDimensionTag(PfBachChangeStatusDto dto);

    /**
     * 保存评估维度
     *
     * @param dto
     * @return
     */
    Long saveDimensionTag(BasDemoAsses dto);

    /**
     * 编辑评估维度
     *
     * @param dto
     * @return
     */
    Long editDimensionTag(BasDemoAsses dto);

    /**
     * 查询评估维度信息
     *
     * @param idDimemsion
     * @return
     */
    BasDemoAsses selectDimensionTagInfo(@Param("idDimemsion") Long idDimemsion);

    /**
     * all病例标签
     *
     * @param idDemo
     * @return
     */
    List<PfCaseHistoryTagVo> listAllCaseHistoryTag(@Param("idDemo") Long idDemo);

    /**
     * all评估表标签
     *
     * @param idDemo
     * @return
     */
    List<PfAssessTagVo> listAllAssessTag(@Param("idDemo") Long idDemo);

    /**
     * 病例流程配置
     * @param dto
     * @return
     */
    Integer saveSerialNo(BasMedicalTag dto);
}
