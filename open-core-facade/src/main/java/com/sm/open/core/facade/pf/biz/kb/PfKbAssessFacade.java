package com.sm.open.core.facade.pf.biz.kb;

import com.sm.open.core.facade.model.param.pf.biz.kb.assess.*;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.kb.assess.*;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfKbAssessFacade
 * @Description: 评估组件facade服务
 * @Author yangtongbin
 * @Date 2018/10/22
 */
public interface PfKbAssessFacade {

    /**
     * 评估组件用例列表
     *
     * @param param
     * @return
     */
    PfPageResult listKbAssess(PfEvaCaseParam param);

    /**
     * 新增评估组件用例
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveKbAssess(FaqEvaCaseParam param);

    /**
     * 删除评估组件用例
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delKbAssess(PfBachChangeStatusParam param);

    /**
     * 评估项_拟诊_列表
     *
     * @param param
     * @return
     */
    PfPageResult listKbReferral(PfAssessCommonParam param);

    /**
     * 拟诊 - 等效答案列表
     *
     * @param param
     * @return
     */
    CommonResult<List<FaqEvaCaseItemReferralResult>> listReferral(PfAssessCommonParam param);

    /**
     * 拟诊 - 删除等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delReferral(PfBachChangeStatusParam param);

    /**
     * 拟诊 - 保存等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveReferral(PfAssessReferralParam param);

    /**
     * 确诊 - 评估项 - 列表
     *
     * @param param
     * @return
     */
    PfPageResult listKbDiagnosis(PfAssessCommonParam param);

    /**
     * 确诊 - 等效答案列表
     *
     * @param param
     * @return
     */
    CommonResult<List<FaqEvaCaseItemDiagnosisResult>> listDiagnosisAnswer(PfAssessCommonParam param);

    /**
     * 确诊 - 删除等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delDiagnosis(PfBachChangeStatusParam param);

    /**
     * 确诊 - 保存等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveDiagnosis(PfAssessDiagnosisParam param);

    /**
     * 确诊理由 - 评估项 - 列表
     *
     * @param param
     * @return
     */
    PfPageResult listKbReason(PfAssessCommonParam param);

    /**
     * 确诊理由 - 等效答案列表
     *
     * @param param
     * @return
     */
    CommonResult<List<FaqEvaCaseItemReasonResult>> listReasonAnswer(PfAssessCommonParam param);

    /**
     * 确诊理由 - 删除等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delReason(PfBachChangeStatusParam param);

    /**
     * 确诊理由 - 保存等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveReason(PfAssessReasonParam param);


    /**
     * 鉴定检查 - 评估项 - 列表
     *
     * @param param
     * @return
     */
    PfPageResult listKbCover(PfAssessCommonParam param);

    /**
     * 鉴定检查 - 等效答案列表
     *
     * @param param
     * @return
     */
    CommonResult<List<FaqEvaCaseItemCoverResult>> listCoverAnswer(PfAssessCommonParam param);

    /**
     * 鉴定检查 - 删除等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delCover(PfBachChangeStatusParam param);

    /**
     * 鉴定检查 - 保存等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveCover(PfAssessCoverParam param);


    /**
     * 覆盖必须检查 - 评估项 - 列表
     *
     * @param param
     * @return
     */
    PfPageResult listKbMust(PfAssessCommonParam param);

    /**
     * 覆盖必须检查 - 等效答案列表
     *
     * @param param
     * @return
     */
    CommonResult<List<FaqEvaCaseItemMustResult>> listMustAnswer(PfAssessCommonParam param);

    /**
     * 覆盖必须检查 - 删除等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delMust(PfBachChangeStatusParam param);

    /**
     * 覆盖必须检查 - 保存等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveMust(PfAssessMustParam param);

    /**
     * 检查效率 - 评估项 - 列表
     *
     * @param param
     * @return
     */
    PfPageResult listKbEffciency(PfAssessCommonParam param);

    /**
     * 检查效率 - 删除等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delEffciency(PfBachChangeStatusParam param);

    /**
     * 检查效率 - 保存等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveEffciency(PfAssessEffciencyParam param);

    /**
     * 临时医嘱用药 - 评估项 - 列表
     *
     * @param param
     * @return
     */
    PfPageResult listKbOrder(PfAssessCommonParam param);

    /**
     * 临时医嘱用药 - 等效答案列表
     *
     * @param param
     * @return
     */
    CommonResult<List<FaqEvaCaseItemOrderResult>> listOrderAnswer(PfAssessCommonParam param);

    /**
     * 临时医嘱用药 - 删除等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delOrder(PfBachChangeStatusParam param);

    /**
     * 临时医嘱用药 - 保存等效答案
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveOrder(PfAssessOrderParam param);

    /**
     * 删除
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delCommonAssess(PfBachChangeStatusParam param);
}
