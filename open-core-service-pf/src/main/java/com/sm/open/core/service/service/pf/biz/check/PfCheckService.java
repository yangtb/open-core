package com.sm.open.core.service.service.pf.biz.check;

import com.sm.open.core.model.dto.pf.biz.check.PfCheckQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonSearchDto;
import com.sm.open.core.model.entity.BasBody;
import com.sm.open.core.model.entity.BasBodyCa;
import com.sm.open.core.model.entity.BasBodyResult;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.check.BasCheckSearchVo;
import com.sm.open.core.model.vo.pf.biz.exam.BasExamSearchVo;

import java.util.List;

/**
 * @ClassName: PfCheckService
 * @Description: 检查题库服务
 * @Author yangtongbin
 * @Date 2018/10/7
 */
public interface PfCheckService {

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
    Long addQuestionClassify(BasBodyCa dto);

    /**
     * 编辑题库分类信息
     *
     * @param dto
     * @return
     */
    boolean editQuestionClassify(BasBodyCa dto);

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
    Long countQuestion(PfCheckQuestionDto dto);

    /**
     * 问题列表
     *
     * @param dto
     * @return
     */
    List<BasBody> listQuestion(PfCheckQuestionDto dto);

    /**
     * 新增问题
     *
     * @param dto
     * @return
     */
    boolean addQuestion(BasBody dto);

    /**
     * 编辑问题
     *
     * @param dto
     * @return
     */
    boolean editQuestion(BasBody dto);

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
    List<BasBodyResult> listAnswer(PfCheckQuestionDto dto);

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
    Long saveAnswer(BasBodyResult dto);

    /**
     * 检查项目总数
     *
     * @param dto
     * @return
     */
    Long countSearchCheck(PfCommonSearchDto dto);

    /**
     * 检查项目列表
     *
     * @param dto
     * @return
     */
    List<BasCheckSearchVo> searchCheck(PfCommonSearchDto dto);
}
