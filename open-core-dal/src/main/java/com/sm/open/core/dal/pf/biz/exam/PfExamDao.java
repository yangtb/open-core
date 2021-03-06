package com.sm.open.core.dal.pf.biz.exam;

import com.sm.open.core.model.dto.pf.biz.exam.PfExamQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonSearchDto;
import com.sm.open.core.model.entity.BasInspectCa;
import com.sm.open.core.model.entity.BasInspectItem;
import com.sm.open.core.model.entity.BasItemResult;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.exam.BasExamSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfExamDao {

    /**
     * 题库分类树
     *
     * @return
     */
    List<PfCommonZtreeVo> listQuestionClassifyTree();

    /**
     * 新增题库分类信息
     *
     * @param dto
     * @return
     */
    Long addQuestionClassify(BasInspectCa dto);

    /**
     * 编辑题库分类信息
     *
     * @param dto
     * @return
     */
    Integer editQuestionClassify(BasInspectCa dto);

    /**
     * 删除题库分类信息
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
    Long countQuestion(PfExamQuestionDto dto);

    /**
     * 问题列表
     *
     * @param dto
     * @return
     */
    List<BasInspectItem> listQuestion(PfExamQuestionDto dto);

    /**
     * 新增问题
     *
     * @param dto
     * @return
     */
    Integer addQuestion(BasInspectItem dto);

    /**
     * 编辑问题
     *
     * @param dto
     * @return
     */
    Integer editQuestion(BasInspectItem dto);

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
    List<BasItemResult> listAnswer(PfExamQuestionDto dto);

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
    Integer saveAnswer(BasItemResult dto);

    /**
     * 编辑答案信息
     *
     * @param dto
     * @return
     */
    Integer editAnswer(BasItemResult dto);

    /**
     * 有无默认答案
     *
     * @param idInspectItem 项目ID
     * @return
     */
    boolean isExistDefaultAnswer(@Param("idInspectItem") Long idInspectItem);

    /**
     * 设定默认答案
     *
     * @param idInspectItem 项目ID
     * @return
     */
    Integer setDefaultAnswer(@Param("idInspectItem") Long idInspectItem);

    /**
     * 更新默认答案
     *
     * @param idInspectItem 项目ID
     * @return
     */
    Integer updateDefaultAnswer(@Param("idInspectItem") Long idInspectItem);

    /**
     * 检验项目总数
     *
     * @param dto
     * @return
     */
    Long countSearchExam(PfCommonSearchDto dto);

    /**
     * 检验项目列表
     *
     * @param dto
     * @return
     */
    List<BasExamSearchVo> searchExam(PfCommonSearchDto dto);

    /**
     * 根据id查询检验项目
     *
     * @param idInspectItem
     * @return
     */
    BasInspectItem selectInspectItemById(Long idInspectItem);

    /**
     * 根据id查询检验结果
     *
     * @param idResult
     * @return
     */
    BasItemResult selectItemResultById(Long idResult);
}
