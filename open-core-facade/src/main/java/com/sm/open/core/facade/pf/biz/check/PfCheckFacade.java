package com.sm.open.core.facade.pf.biz.check;

import com.sm.open.core.facade.model.param.pf.biz.check.BasBodyCaParam;
import com.sm.open.core.facade.model.param.pf.biz.check.BasBodyParam;
import com.sm.open.core.facade.model.param.pf.biz.check.BasBodyResultParam;
import com.sm.open.core.facade.model.param.pf.biz.check.PfCheckQuestionParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesAnswerParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesCaParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.PfInquisitionQuestionParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.check.BasBodyResultResult;
import com.sm.open.core.facade.model.result.pf.biz.inquisition.BasInquesAnswerResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfCheckFacade
 * @Description: 检查题库服务
 * @Author yangtongbin
 * @Date 2018/10/7
 */
public interface PfCheckFacade {

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
    CommonResult<Long> addQuestionClassify(BasBodyCaParam param);

    /**
     * 编辑题库信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editQuestionClassify(BasBodyCaParam param);

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
    PfPageResult listQuestion(PfCheckQuestionParam param);

    /**
     * 新增问题
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addQuestion(BasBodyParam param);

    /**
     * 编辑问题
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editQuestion(BasBodyParam param);

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
    PfPageResult<BasBodyResultResult> listAnswer(PfCheckQuestionParam param);

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
    CommonResult<Long> saveAnswer(BasBodyResultParam param);

}
