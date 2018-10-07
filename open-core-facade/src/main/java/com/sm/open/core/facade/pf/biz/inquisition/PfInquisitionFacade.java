package com.sm.open.core.facade.pf.biz.inquisition;

import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesAnswerParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesCaParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.PfInquisitionQuestionParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.inquisition.BasInquesAnswerResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfInquisitionFacade
 * @Description: 问诊服务
 * @Author yangtongbin
 * @Date 2018/10/3
 */
public interface PfInquisitionFacade {

    /**
     * 题库分类tree
     *
     * @return
     */
    CommonResult<List<PfCommonZtreeResult>> listQuestionClassifyTree();

    /**
     * 新增问诊题库信息
     *
     * @param param
     * @return
     */
    CommonResult<Long> addQuestionClassify(BasInquesCaParam param);

    /**
     * 编辑问诊题库信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editQuestionClassify(BasInquesCaParam param);

    /**
     * 删除问诊题库信息
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
    PfPageResult listQuestion(PfInquisitionQuestionParam param);

    /**
     * 新增问题
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addQuestion(BasInquesParam param);

    /**
     * 编辑问题
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editQuestion(BasInquesParam param);

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
    PfPageResult<BasInquesAnswerResult> listAnswer(PfInquisitionQuestionParam param);

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
    CommonResult<Boolean> saveAnswer(BasInquesAnswerParam param);

}
