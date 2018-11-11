package com.sm.open.core.service.service.pf.biz.clinic;

import com.sm.open.core.facade.model.result.pf.biz.clinic.PfCaseHistoryTagResult;
import com.sm.open.core.model.dto.pf.biz.clinic.PfClinicTemplateDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.clinic.BasEvaTagVo;
import com.sm.open.core.model.vo.pf.biz.clinic.BasMedicalTagVo;
import com.sm.open.core.model.vo.pf.biz.clinic.PfAssessTagVo;
import com.sm.open.core.model.vo.pf.biz.clinic.PfCaseHistoryTagVo;

import java.util.List;

/**
 * @ClassName: PfClinicTemplateService
 * @Description: 临床模板定义
 * @Author yangtongbin
 * @Date 2018/10/8
 */
public interface PfClinicTemplateService {

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
    boolean editClassify(BasDemoCa dto);

    /**
     * 删除分类信息
     *
     * @param dto
     * @return
     */
    boolean delClassify(PfBachChangeStatusDto dto);

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
    boolean addTemplate(BasDemo dto);

    /**
     * 编辑模板
     *
     * @param dto
     * @return
     */
    boolean editTemplate(BasDemo dto);

    /**
     * 删除模板
     *
     * @param dto
     * @return
     */
    boolean delTemplate(PfBachChangeStatusDto dto);

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
    boolean delCaseHistoryTag(PfBachChangeStatusDto dto);

    /**
     * 保存病例标签信息
     *
     * @param dto
     * @return
     */
    Long saveCaseHistoryTag(BasMedicalTag dto);

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
    boolean delSheetTag(PfBachChangeStatusDto dto);

    /**
     * 保存标签信息
     *
     * @param dto
     * @return
     */
    Long saveSheetTag(BasEvaTag dto);

    /**
     * 获取所有病例模板
     *
     * @return
     */
    List<BasDemo> listAllBasDemo();

    /**
     * 评价维度分类tree
     *
     * @return
     */
    List<PfCommonZtreeVo> listDimensionTree(Long idDemo);

    /**
     * 删除评估维度
     *
     * @param dto
     * @return
     */
    boolean delDimensionTag(PfBachChangeStatusDto dto);

    /**
     * 保存评估维度
     *
     * @param dto
     * @return
     */
    Long saveDimensionTag(BasDemoAsses dto);

    /**
     * 查询评估维度信息
     *
     * @param idDimemsion
     * @return
     */
    BasDemoAsses selectDimensionTagInfo(Long idDimemsion);

    /**
     * all病例标签
     *
     * @param idDemo
     * @return
     */
    List<PfCaseHistoryTagVo> listAllCaseHistoryTag(Long idDemo);

    /**
     * all评估表标签
     *
     * @param idDemo
     * @return
     */
    List<PfAssessTagVo> listAllAssessTag(Long idDemo);
}
