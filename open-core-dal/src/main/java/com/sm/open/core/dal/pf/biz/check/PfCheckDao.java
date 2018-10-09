package com.sm.open.core.dal.pf.biz.check;

import com.sm.open.core.model.dto.pf.biz.check.PfCheckQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasBody;
import com.sm.open.core.model.entity.BasBodyCa;
import com.sm.open.core.model.entity.BasBodyResult;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfCheckDao {

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
    Integer editQuestionClassify(BasBodyCa dto);

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
    Integer addQuestion(BasBody dto);

    /**
     * 编辑问题
     *
     * @param dto
     * @return
     */
    Integer editQuestion(BasBody dto);

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
    List<BasBodyResult> listAnswer(PfCheckQuestionDto dto);

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
    Integer saveAnswer(BasBodyResult dto);

    /**
     * 编辑答案信息
     *
     * @param dto
     * @return
     */
    Integer editAnswer(BasBodyResult dto);
}