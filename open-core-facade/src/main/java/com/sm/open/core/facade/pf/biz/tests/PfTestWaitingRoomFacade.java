package com.sm.open.core.facade.pf.biz.tests;

import com.sm.open.core.facade.model.param.pf.biz.tests.room.*;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCatalogueTreeParam;
import com.sm.open.core.facade.model.param.pf.common.PfCommonListParam;
import com.sm.open.core.facade.model.result.pf.biz.disease.PfDiseaseZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseBodyResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.*;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.eva.*;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.paper.PfTestPaperResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: PfTestWaitingRoomFacade
 * @Description: 模拟就诊facade
 * @Author yangtongbin
 * @Date 2018/11/4
 */
public interface PfTestWaitingRoomFacade {

    /**
     * 候诊室列表
     *
     * @param param
     * @return
     */
    PfPageResult listWaitingRoom(PfTestWatingRoomParam param);

    /**
     * 接诊列表
     *
     * @param param
     * @return
     */
    PfPageResult listReceivePat(PfTestWatingRoomParam param);

    /**
     * 获取试卷信息
     *
     * @param param
     * @return
     */
    CommonResult<PfTestPaperResult> selectTestPaperInfo(PfTestExamParam param);

    /**
     * 获取试卷信息
     *
     * @param param
     * @return
     */
    CommonResult<PfTestPaperResult> selectTestPaper(PfTestExamParam param);

    /**
     * 开始考试
     *
     * @param param
     * @return
     */
    CommonResult<PfWaitingRoomStartResult> startExam(ExmTestexecParam param);

    /**
     * 交卷
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> endExam(ExmTestexecParam param);

    /**
     * 查询患者信息
     *
     * @param param
     * @return
     */
    CommonResult<PfWaitingRoomPatResult> selectPatInfo(PfTestExamTagParam param);

    /**
     * 查询问诊列表
     *
     * @param param
     * @return
     */
    PfPageResult listTestCons(PfTestExamTagParam param);

    /**
     * 问诊 - 保存问答问题
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveConsQa(ExmMedResultInquesParam param);

    /**
     * 问诊 - 编辑问答问题
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editConsQa(PfExmMedResultParam param);

    /**
     * 问诊 - 线索标志
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updateConsStatus(PfBachChangeStatusParam param);

    /**
     * 问诊 - qa列表
     *
     * @param param
     * @return
     */
    CommonResult<List<PfWaitingRoomConsResult>> listConsQa(PfTestExamTagParam param);

    /**
     * 查询检查图片
     *
     * @param param
     * @return
     */
    CommonResult<FaqMedCaseBodyResult> selectPic(PfTestExamTagParam param);

    /**
     * 检查 - 列表
     *
     * @param param
     * @return
     */
    PfPageResult listTestCheck(PfTestExamTagParam param);

    /**
     * 检查 - 保存问答问题
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveCheckQa(ExmMedResultBodyParam param);

    /**
     * 检查 - 编辑问答问题
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editCheckQa(ExmMedResultBodyParam param);

    /**
     * 检查 - 线索标志
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updateCheckStatus(PfBachChangeStatusParam param);

    /**
     * 检查 - qa列表
     *
     * @param param
     * @return
     */
    CommonResult<List<PfWaitingRoomCheckResult>> listCheckQa(PfTestExamTagParam param);


    /**
     * 检验 - 列表
     *
     * @param param
     * @return
     */
    PfPageResult listTestExam(PfTestExamTagParam param);


    /**
     * 检验 - 保存
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveExamQa(ExmMedResultInspectParam param);

    /**
     * 检验 - 批量保存
     *
     * @param dto
     * @return
     */
    CommonResult<BigDecimal> saveBatchExamQa(PfTestExamTagParam dto);

    /**
     * 检验 - 编辑
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editExamQa(ExmMedResultInspectParam param);

    /**
     * 检验 - 线索标志
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updateExamStatus(PfBachChangeStatusParam param);

    /**
     * 检验 - qa列表
     *
     * @param param
     * @return
     */
    CommonResult<List<PfWaitingRoomExamResult>> listExamQa(PfTestExamTagParam param);

    /**
     * 拟诊 - 保存
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveReferral(ExmMedResultReferralParam param);

    /**
     * 已添加拟诊
     *
     * @param param
     * @return
     */
    CommonResult<List<ExmMedResultReferralResult>> listReferral(PfTestExamTagParam param);

    /**
     * 医嘱 - 查询
     *
     * @param idTestexecResult 执行结果id
     * @return
     */
    CommonResult<ExmMedResultOrderResult> selectOrders(Long idTestexecResult);

    /**
     * 医嘱 - 保存
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveOrder(ExmMedResultOrderParam param);

    /**
     * 医嘱 - 保存药品
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> saveDrugs(PfCommonListParam param);

    /**
     * 医嘱 - 长期用药列表
     *
     * @param idTestexecResultOrder 执行结果id
     * @return
     */
    PfPageResult listLongDrugs(Long idTestexecResultOrder);

    /**
     * 医嘱 - 短期用药列表
     *
     * @param idTestexecResultOrder 执行结果id
     * @return
     */
    PfPageResult listShortDrugs(Long idTestexecResultOrder);

    /**
     * 删除用药
     *
     * @param type 类型
     * @param id   主键
     * @return
     */
    CommonResult<Boolean> delDrugs(String type, Long id);

    /**
     * 保存诊断
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveDiagnosis(ExmMedResultDiagnosisParam param);

    /**
     * 保存鉴别诊断
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveIdentifyDiagnosis(ExmMedResultIdentifyParam param);

    /**
     * 删除诊断
     *
     * @param idTestexecResultDiagnosis 主键
     * @return
     */
    CommonResult<Boolean> delDiagnosis(Long idTestexecResultDiagnosis);

    /**
     * 保存诊断小结
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveSummary(ExmMedResultSummaryParam param);

    /**
     * 保存确诊理由
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> saveDieReason(List<ExmMedResultDieReasonParam> param);

    /**
     * 删除确诊理由
     *
     * @param idDieReason 主键
     * @return
     */
    CommonResult<Boolean> delDieReason(Long idDieReason);

    /**
     * 查询拟诊
     *
     * @param idTestexecResult 病例结果ID
     * @return
     */
    CommonResult<List<ExmMedResultReferralResult>> selectAllReferral(Long idTestexecResult);

    /**
     * 查询所有诊断
     *
     * @param idTestexecResult 病例结果ID
     * @return
     */
    CommonResult<List<PfDiagnosisResult>> selectAllDiagnosis(Long idTestexecResult);

    /**
     * 查询诊断、诊断小结
     *
     * @param param
     * @return
     */
    CommonResult<PfWaitingRoomDiagnosisResult> selectDiagnosis(ExmMedResultDiagnosisParam param);

    /**
     * 查询诊断小结
     *
     * @param idTestexecResult 病例结果ID
     * @return
     */
    CommonResult<ExmMedResultSummaryResult> selectSummary(Long idTestexecResult);

    /**
     * 查询已做问诊、检查、检验
     *
     * @return
     */
    CommonResult<List<PfWaitingRoomDieReasonResult>> listReadyDieReason(Long idTestexecResult, String keyword);

    /**
     * 查询确诊理由
     *
     * @param idTestexecResultDiagnosis
     * @return
     */
    PfPageResult listDieReason(Long idTestexecResultDiagnosis);

    /**
     * 查询病例评估得分
     *
     * @param idTestexecResult
     * @return
     */
    CommonResult<List<PfEvaExecResult>> selectScore(Long idTestexecResult, Long idMedicalrec);

    /**
     * 查询病例评估
     *
     * @param param
     * @return
     */
    CommonResult<List<PfEvaExecResult>> listEva(PfTestEvaParam param);

    /**
     * 查询评估日志
     *
     * @param idTestexecResult
     * @return
     */
    CommonResult<List<ExmEvaLogResult>> listEvaLog(Long idTestexecResult);

    /**
     * 病例评估
     *
     * @param idTestexecResult
     * @return
     */
    CommonResult<Boolean> medEva(Long idTestexecResult);

    /**
     * 修改得分
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editEva(ExmEvaDimensionParam param);

    /**
     * 查询病例执行日志
     *
     * @param idTestexecResult
     * @return
     */
    CommonResult<List<PfExecLogResult>> listExecLog(Long idTestexecResult);

    /**
     * 评估结果
     *
     * @param idTestexecResult
     * @return
     */
    CommonResult<ExmEvaResultResult> selectEvaResult(Long idTestexecResult);

    /**
     * 保存拟诊原因
     *
     * @param params
     * @return
     */
    CommonResult<Boolean> saveReferralReason(List<ExmMedResultReferralReasonParam> params);

    /**
     * 拟诊原因列表
     *
     * @param idTestexecResultReferral
     * @return
     */
    PfPageResult listReferralReason(Long idTestexecResultReferral);

    /**
     * 删除计划详情
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delPlanDetail(PfBachChangeStatusParam param);

    /**
     * 保存执行序号
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> saveExecSerialNo(ExmTestexecParam param);

    /**
     * 拟诊疾病列表
     *
     * @param idTestexecResult
     * @return
     */
    PfPageResult listAllReferralDie(Long idTestexecResult, String keywords);

    /**
     * 确诊项 及 排除拟诊项
     *
     * @param param
     * @return
     */
    CommonResult<List<PfDiagnosticAnalysisResult>> listDiagnosticAnalysis(PfTestEvaParam param);

    /**
     * 查询病例诊断分析详情
     *
     * @param param
     * @return
     */
    CommonResult<List<PfDiagnosticAnalysisDetailResult>> listDiagnosticAnalysisDetail(PfTestEvaParam param);

    /**
     * 疾病目录树
     *
     * @param
     * @return
     */
    CommonResult<List<PfDiseaseZtreeResult>> listDiseaseCatalogueTree(PfCatalogueTreeParam param);

    /**
     * 思维导图
     *
     * @param param
     * @return
     */
    CommonResult<String> selectReferralChartData(PfTestEvaParam param);

    /**
     * 查询诊断分析、鉴别诊断列表
     *
     * @param param
     * @return
     */
    PfPageResult listDiagnosticChart(PfTestExamTagParam param);

    /**
     * 考试完成后所需跳转信息
     *
     * @param idTestplanDetail
     * @return
     */
    CommonResult<PfWaitingRoomFinishResult> selectFinishExamInfo(Long idTestplanDetail);

    /**
     * 患者页签idMedCase
     *
     * @param idTestplanDetail
     * @return
     */
    CommonResult<Long> selectAssessPatIdMedCase(Long idTestplanDetail);

    /**
     * 查询病例评估指南
     *
     * @param idTestplanDetail
     * @return
     */
    CommonResult<String> selectEvaGuideContent(Long idTestplanDetail);
}
