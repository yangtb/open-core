package com.sm.open.core.dal.pf.biz.inquisition;

import com.sm.open.core.model.dto.pf.biz.inquisition.PfInquisitionQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonSearchDto;
import com.sm.open.core.model.entity.BasInques;
import com.sm.open.core.model.entity.BasInquesAnswer;
import com.sm.open.core.model.entity.BasInquesCa;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.inquisition.BasInquesSearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfInquisitionDao {

    /**
     * 问诊题库分类树
     *
     * @return
     */
    List<PfCommonZtreeVo> listQuestionClassifyTree();

    /**
     * 新增问诊题库分类信息
     *
     * @param dto
     * @return
     */
    Long addQuestionClassify(BasInquesCa dto);

    /**
     * 编辑问诊题库分类信息
     *
     * @param dto
     * @return
     */
    Integer editQuestionClassify(BasInquesCa dto);

    /**
     * 删除问诊题库分类信息
     *
     * @param dto
     * @return
     */
    Integer delQuestionClassify(PfBachChangeStatusDto dto);

    /**
     * 问题总数
     *
     * @param dto
     * @return
     */
    Long countQuestion(PfInquisitionQuestionDto dto);

    /**
     * 问题列表
     *
     * @param dto
     * @return
     */
    List<BasInques> listQuestion(PfInquisitionQuestionDto dto);

    /**
     * 新增问题
     *
     * @param dto
     * @return
     */
    Integer addQuestion(BasInques dto);

    /**
     * 编辑问题
     *
     * @param dto
     * @return
     */
    Integer editQuestion(BasInques dto);

    /**
     * 删除问题
     *
     * @param dto
     * @return
     */
    Integer delQuestion(PfBachChangeStatusDto dto);

    /**
     * 问题答案列表
     *
     * @param dto
     * @return
     */
    List<BasInquesAnswer> listAnswer(PfInquisitionQuestionDto dto);

    /**
     * 删除答案信息
     *
     * @param dto
     * @return
     */
    Integer delAnswer(PfBachChangeStatusDto dto);

    /**
     * 保存答案信息
     *
     * @param dto
     * @return
     */
    Integer saveAnswer(BasInquesAnswer dto);

    /**
     * 编辑答案信息
     *
     * @param dto
     * @return
     */
    Integer editAnswer(BasInquesAnswer dto);

    /**
     * 问诊问题列表搜索总数
     *
     * @param dto
     * @return
     */
    Long countSearchQuestion(PfCommonSearchDto dto);

    /**
     * 问诊问题列表搜索
     *
     * @param dto
     * @return
     */
    List<BasInquesSearchVo> searchQuestion(PfCommonSearchDto dto);
}
