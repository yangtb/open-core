package com.sm.open.core.dal.pf.biz.kb;

import com.sm.open.core.model.dto.pf.biz.kb.casehistory.PfCaseHistoryDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.FaqMedicalrec;
import com.sm.open.core.model.entity.FaqMedicalrecCa;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.casehistory.FaqMedicalrecVo;
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


}
