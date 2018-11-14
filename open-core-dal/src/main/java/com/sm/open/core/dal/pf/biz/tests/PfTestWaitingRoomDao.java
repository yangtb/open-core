package com.sm.open.core.dal.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamTagDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestWatingRoomDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.test.*;
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
     * @param idTestplanDetail 计划明细id
     * @param idStudent        学生id
     * @return
     */
    PfTestPaperInfoVo selectTestPaperInfo(@Param("idTestplanDetail") Long idTestplanDetail,
                                          @Param("idStudent") Long idStudent);

    /**
     * 开始考试
     *
     * @param dto
     * @return
     */
    Integer startExam(ExmTestexec dto);

    /**
     * 插入测试执行_病历结果
     *
     * @param dto
     * @return
     */
    Integer insertExmMedResult(ExmMedResult dto);

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
     * @param fgValid                 删除标识
     * @return
     */
    Integer delExamQa(@Param("idTestexecResultInspect") Long idTestexecResultInspect,
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
     * 查询诊断
     *
     * @param idTestexecResult 病历结果ID
     * @return
     */
    ExmMedResultDiagnosis selectDiagnosis(Long idTestexecResult);

    /**
     * 查询诊断小结
     *
     * @param idTestexecResult 病历结果ID
     * @return
     */
    ExmMedResultSummary selectSummary(Long idTestexecResult);

    /**
     * 查询已做问诊、检查、检验
     *
     * @return
     */
    List<PfWaitingRoomDieReasonVo> listReadyDieReason(Long idTestexecResult);

    /**
     * 查询确诊理由
     *
     * @param idTestexecResultDiagnosis
     * @return
     */
    List<PfWaitingRoomDieReasonVo> listDieReason(Long idTestexecResultDiagnosis);
}
