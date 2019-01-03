package com.sm.open.core.dal.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.*;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.test.*;
import com.sm.open.core.model.vo.pf.biz.test.eva.PfEvaExecVo;
import com.sm.open.core.model.vo.pf.biz.test.eva.PfExecLogVo;
import com.sm.open.core.model.vo.pf.biz.test.paper.PfTestPaperInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfTestWaitingRoomDao {

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
    PfTestPaperInfoVo selectTestPaperInfo(PfTestExamDto dto);

    /**
     * 开始考试
     *
     * @param dto
     * @return
     */
    Integer startExam(ExmTestexec dto);

    /**
     * 插入测试执行_病例结果
     *
     * @param dto
     * @return
     */
    Integer insertExmMedResult(ExmMedResult dto);

    /**
     * 修改评估标识
     *
     * @param idTestexecResult
     * @return
     */
    Integer updateExmMedResultFlag(@Param("idTestexecResult") Long idTestexecResult);

    /**
     * 交卷
     *
     * @param dto
     * @return
     */
    Integer endExam(ExmTestexec dto);

    /**
     * 查询患者信息
     *
     * @param dto
     * @return
     */
    PfWaitingRoomPatVo selectPatInfo(PfTestExamTagDto dto);

    /**
     * 根据病例id查询照片
     *
     * @param idMedicalrec 病例ID
     * @return
     */
    PfWaitingRoomPatVo selectPhotoInfo(Long idMedicalrec);

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
    Integer saveConsQa(ExmMedResultInques dto);

    /**
     * 问诊 - 是否存在
     *
     * @param dto
     * @return
     */
    Long isExistConsQa(ExmMedResultInques dto);

    /**
     * 问诊 - 删除
     *
     * @param idTestexecResultInques 主键
     * @param fgValid                删除标识
     * @return
     */
    Integer delConsQa(@Param("idTestexecResultInques") Long idTestexecResultInques,
                      @Param("fgValid") String fgValid);

    /**
     * 问诊 - 线索标志
     *
     * @param dto
     * @return
     */
    Integer updateConsStatus(PfBachChangeStatusDto dto);

    /**
     * 问诊 - qa列表
     *
     * @param dto
     * @return
     */
    List<PfWaitingRoomConsVo> listConsQa(PfTestExamTagDto dto);

    /**
     * 查询图片id
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
     * 检查 - 信息
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
    Integer saveCheckQa(ExmMedResultBody dto);

    /**
     * 检查 - 编辑问答问题
     *
     * @param dto
     * @return
     */
    Integer editCheckQa(ExmMedResultBody dto);

    /**
     * 检查 - 是否存在
     *
     * @param dto
     * @return
     */
    Long isExistCheckQa(ExmMedResultBody dto);

    /**
     * 检查 - 删除
     *
     * @param idTestexecResultBody 主键
     * @param fgValid              删除标识
     * @return
     */
    Integer delCheckQa(@Param("idTestexecResultBody") Long idTestexecResultBody,
                       @Param("idDie") Long idDie,
                       @Param("fgValid") String fgValid);

    /**
     * 检查 - 线索标志
     *
     * @param dto
     * @return
     */
    Integer updateCheckStatus(PfBachChangeStatusDto dto);

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
     * 检验 - 信息
     *
     * @param dto
     * @return
     */
    List<FaqMedCaseInspectList> listTestExam(PfTestExamTagDto dto);

    /**
     * 检验 - 保存问答问题
     *
     * @param dto
     * @return
     */
    Integer saveExamQa(ExmMedResultInspect dto);

    /**
     * 检验 - 编辑问答问题
     *
     * @param dto
     * @return
     */
    Integer editExamQa(ExmMedResultInspect dto);

    /**
     * 检验 - 是否存在
     *
     * @param dto
     * @return
     */
    Long isExistExamQa(ExmMedResultInspect dto);

    /**
     * 检验 - 删除
     *
     * @param idTestexecResultInspect 主键
     * @param idDie                   疾病id
     * @param fgValid                 删除标识
     * @return
     */
    Integer delExamQa(@Param("idTestexecResultInspect") Long idTestexecResultInspect,
                      @Param("idDie") Long idDie,
                      @Param("fgValid") String fgValid);

    /**
     * 检验 - 线索标志
     *
     * @param dto
     * @return
     */
    Integer updateExamStatus(PfBachChangeStatusDto dto);

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
    Integer saveReferral(ExmMedResultReferral dto);

    /**
     * 拟诊 - 排除
     *
     * @param dto
     * @return
     */
    Integer outReferral(ExmMedResultReferral dto);

    /**
     * 排除确诊
     *
     * @param idTestexecResultReferral
     * @return
     */
    Integer updateQzFlag(Long idTestexecResultReferral);

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
    ExmMedResultOrder selectOrders(@Param("idTestexecResult") Long idTestexecResult);

    /**
     * 医嘱 - 保存
     *
     * @param dto
     * @return
     */
    Integer saveOrder(ExmMedResultOrder dto);

    /**
     * 医嘱 - 编辑
     *
     * @param dto
     * @return
     */
    Integer editOrder(ExmMedResultOrder dto);

    /**
     * 医嘱 - 保存长期用药
     *
     * @param dto
     * @return
     */
    Integer saveLongDrugs(PfCommonListDto dto);

    /**
     * 医嘱 - 保存临时用药
     *
     * @param dto
     * @return
     */
    Integer saveShortDrugs(PfCommonListDto dto);

    /**
     * 医嘱 - 长期用药列表
     *
     * @param idTestexecResultOrder 执行结果id
     * @return
     */
    List<ExmMedResultOrderLogDrugs> listLongDrugs(@Param("idTestexecResultOrder") Long idTestexecResultOrder);

    /**
     * 医嘱 - 短期用药列表
     *
     * @param idTestexecResultOrder 执行结果id
     * @return
     */
    List<ExmMedResultOrderShortDrugs> listShortDrugs(@Param("idTestexecResultOrder") Long idTestexecResultOrder);

    /**
     * 删除长期用药
     *
     * @param id
     * @return
     */
    Integer delLongDrugs(@Param("id") Long id);

    /**
     * 删除临时用药
     *
     * @param id
     * @return
     */
    Integer delShortDrugs(@Param("id") Long id);

    /**
     * 保存诊断
     *
     * @param dto
     * @return
     */
    Long addDiagnosis(ExmMedResultDiagnosis dto);

    /**
     * 编辑诊断
     *
     * @param dto
     * @return
     */
    Integer editDiagnosis(ExmMedResultDiagnosis dto);

    /**
     * 删除诊断
     *
     * @param idTestexecResultDiagnosis 主键
     * @return
     */
    Integer delDiagnosis(@Param("idTestexecResultDiagnosis") Long idTestexecResultDiagnosis);

    /**
     * 保存诊断小结
     *
     * @param dto
     * @return
     */
    Integer addSummary(ExmMedResultSummary dto);

    /**
     * edit诊断小结
     *
     * @param dto
     * @return
     */
    Integer editSummary(ExmMedResultSummary dto);

    /**
     * 保存确诊理由
     *
     * @param list
     * @return
     */
    Integer saveDieReason(@Param("list") List<ExmMedResultDieReason> list);

    /**
     * 保存确诊理由
     *
     * @param idTestexecResultDiagnosis
     * @param idTestexecResultReferral
     * @return
     */
    Integer saveQzDieReason(@Param("idTestexecResultDiagnosis") Long idTestexecResultDiagnosis,
                            @Param("idTestexecResultReferral") Long idTestexecResultReferral);

    /**
     * 删除已有确诊原因
     *
     * @param idTestexecResultDiagnosis
     * @return
     */
    Integer delQzDieReason(@Param("idTestexecResultDiagnosis") Long idTestexecResultDiagnosis);

    /**
     * 查询确诊id
     *
     * @param idTestexecResultReferral
     * @return
     */
    Long selectQzId(@Param("idTestexecResultReferral") Long idTestexecResultReferral);

    /**
     * 删除确诊理由
     *
     * @param idDieReason 主键
     * @return
     */
    Integer delDieReason(@Param("idDieReason") Long idDieReason);

    /**
     * 删除确诊理由
     *
     * @param idTestexecResultDiagnosis 主键
     * @return
     */
    Integer delDieReasonByResultId(@Param("idTestexecResultDiagnosis") Long idTestexecResultDiagnosis);

    /**
     * 查询诊断list
     *
     * @param idTestexecResult 病例结果ID
     * @return
     */
    List<ExmMedResultDiagnosis> listDiagnosis(Long idTestexecResult);

    /**
     * 查询诊断
     *
     * @param idTestexecResult 病例结果ID
     * @return
     */
    ExmMedResultDiagnosis selectDiagnosis(Long idTestexecResult);

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
     * @param idTestexecResult
     * @param keyword
     * @return
     */
    List<PfWaitingRoomDieReasonVo> listReadyDieReason(@Param("idTestexecResult") Long idTestexecResult,
                                                      @Param("keyword") String keyword);

    /**
     * 查询确诊理由
     *
     * @param idTestexecResultDiagnosis
     * @return
     */
    List<PfWaitingRoomDieReasonVo> listDieReason(Long idTestexecResultDiagnosis);

    /**
     * 查询病例评估
     *
     * @param dto
     * @return
     */
    List<PfEvaExecVo> listEva(PfTestEvaDto dto);

    /**
     * 获取平均得分
     *
     * @param list
     * @return
     */
    List<PfEvaExecVo> getScore(@Param("list") List<Long> list);

    /**
     * 查询已执行病例结果id
     *
     * @param idMedicalrec
     * @return
     */
    List<Long> getExecResultId(@Param("idMedicalrec") Long idMedicalrec);

    /**
     * 查询评估日志
     *
     * @param idTestexecResult
     * @return
     */
    List<ExmEvaLog> listEvaLog(@Param("idTestexecResult") Long idTestexecResult);

    /**
     * 病例评估
     *
     * @param dto
     * @return
     */
    void medEva(PfEvaExecDto dto);

    /**
     * 修改得分
     *
     * @param dto
     * @return
     */
    Integer editEva(ExmEvaDimension dto);

    /**
     * 评估结果
     *
     * @param idTestexecResult
     * @return
     */
    ExmEvaResult selectEvaResult(Long idTestexecResult);

    /**
     * 查询病例执行日志 - 问诊
     *
     * @param idTestexecResult
     * @return
     */
    List<PfExecLogVo> listExecLogInques(Long idTestexecResult);

    /**
     * 查询病例执行日志 - 检查
     *
     * @param idTestexecResult
     * @return
     */
    List<PfExecLogVo> listExecLogBody(Long idTestexecResult);

    /**
     * 查询病例执行日志 - 检验
     *
     * @param idTestexecResult
     * @return
     */
    List<PfExecLogVo> listExecLogInspect(Long idTestexecResult);

    /**
     * 查询病例执行日志 - 诊断
     *
     * @param idTestexecResult
     * @return
     */
    List<PfExecLogVo> listExecLogDiagnosis(Long idTestexecResult);

    /**
     * 查询病例执行日志 - 诊断理由
     *
     * @param idTestexecResultDiagnosis
     * @return
     */
    List<PfExecLogVo> listExecLogDiagnosisReason(Long idTestexecResultDiagnosis);

    /**
     * 查询病例执行日志 - 医嘱
     *
     * @param idTestexecResult
     * @return
     */
    List<PfExecLogVo> listExecLogOrder(Long idTestexecResult);

    /**
     * 拟诊列表
     *
     * @param idTestexecResult
     * @return
     */
    List<ExmMedResultReferral> selectAllReferral(@Param("idTestexecResult") Long idTestexecResult);

    /**
     * 保存拟诊原因
     *
     * @param list
     * @return
     */
    Integer saveReferralReason(@Param("list") List<ExmMedResultReferralReason> list);

    /**
     * 拟诊原因列表
     *
     * @param idTestexecResultReferral
     * @return
     */
    List<PfReferralReasonVo> listReferralReason(@Param("idTestexecResultReferral") Long idTestexecResultReferral);

    /**
     * 删除计划详情
     *
     * @param dto
     * @return
     */
    Integer delPlanDetail(PfBachChangeStatusDto dto);

    /**
     * 删除计划详情状态有无变动
     *
     * @param dto
     * @return
     */
    Integer selectDelPlanDetailstatus(PfBachChangeStatusDto dto);

    /**
     * 保存执行序号
     *
     * @param dto
     * @return
     */
    Integer saveExecSerialNo(ExmTestexec dto);
}
