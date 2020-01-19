package com.sm.open.core.service.service.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.*;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.disease.PfDiseaseZtreeVo;
import com.sm.open.core.model.vo.pf.biz.test.*;
import com.sm.open.core.model.vo.pf.biz.test.eva.PfDiagnosticAnalysisDetailVo;
import com.sm.open.core.model.vo.pf.biz.test.eva.PfDiagnosticAnalysisVo;
import com.sm.open.core.model.vo.pf.biz.test.eva.PfEvaExecVo;
import com.sm.open.core.model.vo.pf.biz.test.eva.PfExecLogVo;
import com.sm.open.core.model.vo.pf.biz.test.paper.PfTestPaperInfoVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: PfTestWaitingRoomService
 * @Description: 模拟就诊service
 * @Author yangtongbin
 * @Date 2018/11/4
 */
public interface PfTestWaitingRoomService {

    /**
     * 候诊室列表count
     *
     * @param dto
     * @return
     */
    Long countWaitingRoom(PfTestWatingRoomDto dto);

    /**
     * 候诊室列表
     *
     * @param dto
     * @return
     */
    List<PfTestWaitingRoomVo> listWaitingRoom(PfTestWatingRoomDto dto);

    /**
     * 接诊列表count
     *
     * @param dto
     * @return
     */
    Long countReceivePat(PfTestWatingRoomDto dto);

    /**
     * 接诊列表
     *
     * @param dto
     * @return
     */
    List<PfTestReceivePatVo> listReceivePat(PfTestWatingRoomDto dto);

    /**
     * 查询试卷信息
     *
     * @param dto
     * @return
     */
    PfTestPaperInfoVo selectTestPaperInto(PfTestExamDto dto);

    /**
     * 开始考试
     *
     * @param dto
     * @return
     */
    PfWaitingRoomStartVo startExam(ExmTestexec dto);

    /**
     * 交卷
     *
     * @param dto
     * @return
     */
    boolean endExam(ExmTestexec dto);

    /**
     * 查询患者信息
     *
     * @param dto
     * @return
     */
    PfWaitingRoomPatVo selectPatInfo(PfTestExamTagDto dto);

    /**
     * 问诊总数
     *
     * @param dto
     * @return
     */
    Long countTestCons(PfTestExamTagDto dto);

    /**
     * 查询问诊信息
     *
     * @param dto
     * @return
     */
    List<FaqMedCaseInquesList> listTestCons(PfTestExamTagDto dto);

    /**
     * 问诊 - 保存问答问题
     *
     * @param dto
     * @return
     */
    Long saveConsQa(ExmMedResultInques dto);

    /**
     * 问诊 - 编辑问答问题
     *
     * @param dto
     * @return
     */
    boolean editConsQa(PfExmMedResultDto dto);

    /**
     * 问诊 - 线索标志
     *
     * @param dto
     * @return
     */
    boolean updateConsStatus(PfBachChangeStatusDto dto);

    /**
     * 问诊 - qa列表
     *
     * @param dto
     * @return
     */
    List<PfWaitingRoomConsVo> listConsQa(PfTestExamTagDto dto);

    /**
     * 查询图片
     *
     * @param dto
     * @return
     */
    Long selectPic(PfTestExamTagDto dto);

    /**
     * 检查 - 列表总数
     *
     * @param dto
     * @return
     */
    Long countTestCheck(PfTestExamTagDto dto);

    /**
     * 检查 - 列表
     *
     * @param dto
     * @return
     */
    List<FaqMedCaseBodyList> listTestCheck(PfTestExamTagDto dto);

    /**
     * 检查 - 保存问答问题
     *
     * @param dto
     * @return
     */
    Long saveCheckQa(ExmMedResultBody dto);

    /**
     * 检查 - 编辑问答问题
     *
     * @param dto
     * @return
     */
    boolean editCheckQa(ExmMedResultBody dto);

    /**
     * 检查 - 线索标志
     *
     * @param dto
     * @return
     */
    boolean updateCheckStatus(PfBachChangeStatusDto dto);

    /**
     * 检查 - qa列表
     *
     * @param dto
     * @return
     */
    List<PfWaitingRoomCheckVo> listCheckQa(PfTestExamTagDto dto);

    /**
     * 检验 - 列表总数
     *
     * @param dto
     * @return
     */
    Long countTestExam(PfTestExamTagDto dto);

    /**
     * 检验 - 列表
     *
     * @param dto
     * @return
     */
    List<FaqMedCaseInspectList> listTestExam(PfTestExamTagDto dto);

    /**
     * 检验金额
     *
     * @param dto
     * @return
     */
    BigDecimal examAmountTotal(PfTestExamTagDto dto);

    /**
     * 检验 - 保存问答问题
     *
     * @param dto
     * @return
     */
    Long saveExamQa(ExmMedResultInspect dto);

    /**
     * 检验 - 批量保存问答问题
     *
     * @param list
     * @return
     */
    BigDecimal saveBatchExamQa(List<ExmMedResultInspect> list);

    /**
     * 检验 - 编辑问答问题
     *
     * @param dto
     * @return
     */
    boolean editExamQa(ExmMedResultInspect dto);

    /**
     * 检验 - 线索标志
     *
     * @param dto
     * @return
     */
    boolean updateExamStatus(PfBachChangeStatusDto dto);

    /**
     * 检验 - qa列表
     *
     * @param dto
     * @return
     */
    List<PfWaitingRoomExamVo> listExamQa(PfTestExamTagDto dto);

    /**
     * 拟诊 - 保存
     *
     * @param dto
     * @return
     */
    Long saveReferral(ExmMedResultReferral dto);

    /**
     * 拟诊 - 修改
     *
     * @param dto
     * @return
     */
    boolean updateReferral(ExmMedResultReferral dto);

    /**
     * 已添加拟诊
     *
     * @param dto
     * @return
     */
    List<ExmMedResultReferral> listReferral(PfTestExamTagDto dto);

    /**
     * 医嘱 - 查询
     *
     * @param idTestexecResult 执行结果id
     * @return
     */
    ExmMedResultOrder selectOrders(Long idTestexecResult);

    /**
     * 医嘱 - 保存
     *
     * @param dto
     * @return
     */
    Long saveOrder(ExmMedResultOrder dto);

    /**
     * 医嘱 - 保存药品
     *
     * @param dto
     * @return
     */
    boolean saveDrugs(PfCommonListDto dto);

    /**
     * 医嘱 - 长期用药列表
     *
     * @param idTestexecResultOrder 执行结果id
     * @return
     */
    List<ExmMedResultOrderLogDrugs> listLongDrugs(Long idTestexecResultOrder);

    /**
     * 医嘱 - 短期用药列表
     *
     * @param idTestexecResultOrder 执行结果id
     * @return
     */
    List<ExmMedResultOrderShortDrugs> listShortDrugs(Long idTestexecResultOrder);

    /**
     * 删除用药
     *
     * @param type 类型
     * @param id   主键
     * @return
     */
    boolean delDrugs(String type, Long id);

    /**
     * 保存诊断
     *
     * @param dto
     * @return
     */
    Long saveDiagnosis(ExmMedResultDiagnosis dto);

    /**
     * 保存鉴别诊断
     *
     * @param dto
     * @return
     */
    Long saveIdentifyDiagnosis(ExmMedResultIdentify dto);

    /**
     * 删除诊断
     *
     * @param idTestexecResultDiagnosis 主键
     * @return
     */
    boolean delDiagnosis(Long idTestexecResultDiagnosis);

    /**
     * 保存诊断小结
     *
     * @param dto
     * @return
     */
    Long saveSummary(ExmMedResultSummary dto);

    /**
     * 保存确诊理由
     *
     * @param dto
     * @return
     */
    boolean saveDieReason(List<ExmMedResultDieReason> dto);

    /**
     * 删除确诊理由
     *
     * @param idDieReason 主键
     * @return
     */
    boolean delDieReason(Long idDieReason);

    /**
     * 查询诊断、确诊理由
     *
     * @param idTestexecResult 病例结果ID
     * @return
     */
    List<PfDiagnosisVo> selectAllDiagnosis(Long idTestexecResult);

    /**
     * 查询拟诊
     *
     * @param idTestexecResult 病例结果ID
     * @return
     */
    List<ExmMedResultReferral> selectAllReferral(Long idTestexecResult);

    /**
     * 查询诊断、诊断小结
     *
     * @param dto
     * @return
     */
    PfWaitingRoomDiagnosisVo selectDiagnosis(ExmMedResultDiagnosis dto);

    List<ExmMedResultDiagnosis> listDiagnosis(ExmMedResultDiagnosis dto);

    /**
     * 查询诊断小结
     *
     * @param idTestexecResult 病例结果ID
     * @return
     */
    ExmMedResultSummary selectSummary(Long idTestexecResult);

    /**
     * 查询已做问诊、检查、检验
     *
     * @return
     */
    List<PfWaitingRoomDieReasonVo> listReadyDieReason(Long idTestexecResult, String keyword);

    /**
     * 查询确诊理由
     *
     * @param idTestexecResultDiagnosis
     * @return
     */
    List<PfWaitingRoomDieReasonVo> listDieReason(Long idTestexecResultDiagnosis);

    /**
     * 查询病例评估得分
     *
     * @param idTestexecResult
     * @param idMedicalrec
     * @return
     */
    List<PfEvaExecVo> selectScore(Long idTestexecResult, Long idMedicalrec);

    /**
     * 查询病例评估
     *
     * @param dto
     * @return
     */
    List<PfEvaExecVo> listEva(PfTestEvaDto dto);

    /**
     * 查询评估日志
     *
     * @param idTestexecResultDimension
     * @return
     */
    List<ExmEvaLog> listEvaLog(Long idTestexecResultDimension);

    /**
     * 病例评估
     *
     * @param idTestexecResult
     * @return
     */
    boolean medEva(Long idTestexecResult);

    /**
     * 修改得分
     *
     * @param dto
     * @return
     */
    boolean editEva(ExmEvaDimension dto);

    /**
     * 查询病例执行日志
     *
     * @param idTestexecResult
     * @return
     */
    List<PfExecLogVo> listExecLog(Long idTestexecResult);

    /**
     * 评估结果
     *
     * @param idTestexecResult
     * @return
     */
    ExmEvaResult selectEvaResult(Long idTestexecResult);

    /**
     * 保存拟诊原因
     *
     * @param list
     * @return
     */
    boolean saveReferralReason(List<ExmMedResultReferralReason> list);

    /**
     * 拟诊原因列表
     *
     * @param idTestexecResultReferral
     * @return
     */
    List<PfReferralReasonVo> listReferralReason(Long idTestexecResultReferral);

    /**
     * 删除计划详情
     *
     * @param dto
     * @return
     */
    boolean delPlanDetail(PfBachChangeStatusDto dto);

    /**
     * 保存执行序号
     *
     * @param dto
     * @return
     */
    boolean saveExecSerialNo(ExmTestexec dto);

    /**
     * 拟诊疾病列表
     *
     * @param idTestexecResult
     * @return
     */
    List<BasDie> listAllReferralDie(Long idTestexecResult, String keywords);

    /**
     * 确诊项 及 排除拟诊项
     *
     * @param dto
     * @return
     */
    List<PfDiagnosticAnalysisVo> listDiagnosticAnalysis(PfTestEvaDto dto);

    /**
     * 查询病例诊断分析详情
     *
     * @param dto
     * @return
     */
    List<PfDiagnosticAnalysisDetailVo> listDiagnosticAnalysisDetail(PfTestEvaDto dto);

    /**
     * 疾病目录树
     *
     * @param dto
     * @return
     */
    List<PfDiseaseZtreeVo> listDiseaseCatalogueTree(PfCatalogueTreeDto dto);

    /**
     * 查询思维导图
     *
     * @param dto
     * @return
     */
    String selectReferralChartData(PfTestEvaDto dto);

    /**
     * 诊断分析、鉴别诊断总数
     *
     * @param dto
     * @return
     */
    Long countDiagnosticChart(PfTestExamTagDto dto);

    /**
     * 获取诊断分析、鉴别诊断列表
     *
     * @param dto
     * @return
     */
    List<PfWaitingRoomChartDetailVo> listDiagnosticChart(PfTestExamTagDto dto);

    List<PfWaitingRoomDimensionVo> listEvaDimension(Long idTestexecResultDimension);

    String selectIdStr(PfTestExamTagDto dto);

    /**
     *考试完成后所需跳转信息
     *
     * @param idTestplanDetail
     * @return
     */
    PfWaitingRoomFinishVo selectFinishExamInfo(Long idTestplanDetail);

    /**
     * 患者页签idMedCase
     *
     * @param idTestplanDetail
     * @return
     */
    Long selectAssessPatIdMedCase(Long idTestplanDetail);

    /**
     * 查询病例评估指南
     *
     * @param idTestplanDetail
     * @return
     */
    String selectEvaGuideContent(Long idTestplanDetail);

}
