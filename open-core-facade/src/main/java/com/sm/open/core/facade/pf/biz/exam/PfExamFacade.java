package com.sm.open.core.facade.pf.biz.exam;

import com.sm.open.core.facade.model.param.pf.biz.exam.BasInspectCaParam;
import com.sm.open.core.facade.model.param.pf.biz.exam.BasInspectItemParam;
import com.sm.open.core.facade.model.param.pf.biz.exam.BasItemResultParam;
import com.sm.open.core.facade.model.param.pf.biz.exam.PfExamQuestionParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesAnswerParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesCaParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.PfInquisitionQuestionParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.exam.BasInspectItemResult;
import com.sm.open.core.facade.model.result.pf.biz.exam.BasItemResultResult;
import com.sm.open.core.facade.model.result.pf.biz.inquisition.BasInquesAnswerResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfExamFacade
 * @Description: 检验题库服务
 * @Author yangtongbin
 * @Date 2018/10/7
 */
public interface PfExamFacade {

    /**
     * 题库分类tree
     *
     * @return
     */
    CommonResult<List<PfCommonZtreeResult>> listQuestionClassifyTree();

    /**
     * 新增题库信息
     *
     * @param param
     * @return
     */
    CommonResult<Long> addQuestionClassify(BasInspectCaParam param);

    /**
     * 编辑题库信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editQuestionClassify(BasInspectCaParam param);

    /**
     * 删除题库信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delQuestionClassify(PfBachChangeStatusParam param);

    /**
     * 问题列表
     *
     * @param param
     * @return
     */
    PfPageResult listQuestion(PfExamQuestionParam param);

    /**
     * 新增问题
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addQuestion(BasInspectItemParam param);

    /**
     * 编辑问题
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editQuestion(BasInspectItemParam param);

    /**
     * 删除问题
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delQuestion(PfBachChangeStatusParam param);

    /**
     * 问题答案列表
     *
     * @param param
     * @return
     */
    PfPageResult<BasItemResultResult> listAnswer(PfExamQuestionParam param);

    /**
     * 删除答案信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delAnswer(PfBachChangeStatusParam param);

    /**
     * 保存答案信息
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveAnswer(BasItemResultParam param);

}
