package com.sm.open.core.service.service.pf.biz.exam;

import com.sm.open.core.facade.model.result.pf.biz.exam.BasInspectItemResult;
import com.sm.open.core.model.dto.pf.biz.exam.PfExamQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonSearchDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.exam.BasExamSearchVo;

import java.util.List;

/**
 * @ClassName: PfExamService
 * @Description: 检验题库服务
 * @Author yangtongbin
 * @Date 2018/10/7
 */
public interface PfExamService {

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
    boolean editQuestionClassify(BasInspectCa dto);

    /**
     * 删除题库分类信息
     *
     * @param dto
     * @return
     */
    boolean delQuestionClassify(PfBachChangeStatusDto dto);

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
    boolean addQuestion(BasInspectItem dto);

    /**
     * 编辑问题
     *
     * @param dto
     * @return
     */
    boolean editQuestion(BasInspectItem dto);

    /**
     * 删除问题
     *
     * @param dto
     * @return
     */
    boolean delQuestion(PfBachChangeStatusDto dto);

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
    boolean delAnswer(PfBachChangeStatusDto dto);

    /**
     * 保存答案信息
     *
     * @param dto
     * @return
     */
    Long saveAnswer(BasItemResult dto);

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
}
