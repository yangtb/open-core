package com.sm.open.core.dal.pf.biz.kb;

import com.sm.open.core.model.dto.pf.biz.kb.casehistory.PfCaseHistoryDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.FaqEvaTag;
import com.sm.open.core.model.entity.FaqMedTag;
import com.sm.open.core.model.entity.FaqMedicalrec;
import com.sm.open.core.model.entity.FaqMedicalrecCa;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.casehistory.FaqMedicalrecVo;
import com.sm.open.core.model.vo.pf.biz.clinic.PfAssessTagVo;
import com.sm.open.core.model.vo.pf.biz.clinic.PfCaseHistoryTagVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PfCaseHistoryDao
 * @Description: 病例库dao
 * @Author yangtongbin
 * @Date 2018/10/10
 */
@Repository
public interface PfCaseHistoryDao {

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
    Long addClassify(FaqMedicalrecCa dto);

    /**
     * 编辑分类信息
     *
     * @param dto
     * @return
     */
    Integer editClassify(FaqMedicalrecCa dto);

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
    Long countTemplate(PfCaseHistoryDto dto);

    /**
     * 模板列表
     *
     * @param dto
     * @return
     */
    List<FaqMedicalrecVo> listTemplate(PfCaseHistoryDto dto);

    /**
     * 新增模板
     *
     * @param dto
     * @return
     */
    Integer addTemplate(FaqMedicalrec dto);

    /**
     * 编辑模板
     *
     * @param dto
     * @return
     */
    Integer editTemplate(FaqMedicalrec dto);

    /**
     * 删除模板
     *
     * @param dto
     * @return
     */
    Integer delTemplate(PfBachChangeStatusDto dto);

    /**
     * 保存病例标签
     *
     * @param dto
     * @return
     */
    Long saveMedTag(FaqMedTag dto);


    /**
     * 保存评估标签
     *
     * @param dto
     * @return
     */
    Long saveEvaTag(FaqEvaTag dto);

    /**
     * all病例标签
     *
     * @param idDemo
     * @param idMedicalrec
     * @return
     */
    List<PfCaseHistoryTagVo> listAllCaseHistoryTag(@Param("idDemo") Long idDemo,
                                                   @Param("idMedicalrec") Long idMedicalrec);

    /**
     * all评估表标签
     *
     * @param idDemo
     * @param idMedicalrec
     * @return
     */
    List<PfAssessTagVo> listAllAssessTag(@Param("idDemo") Long idDemo,
                                         @Param("idMedicalrec") Long idMedicalrec);

    /**
     * 根据病例id查询病例信息
     *
     * @param idMedicalrec 病例id
     * @return
     */
    FaqMedicalrecVo selectCaseInfoById(@Param("idMedicalrec") Long idMedicalrec);

    /**
     * 查询病例标签信息
     *
     * @param dto
     * @return
     */
    FaqMedTag selectMedTag(FaqMedTag dto);

    /**
     * 查询评估标签信息
     *
     * @param dto
     * @return
     */
    FaqEvaTag selectEvaTag(FaqEvaTag dto);

}
