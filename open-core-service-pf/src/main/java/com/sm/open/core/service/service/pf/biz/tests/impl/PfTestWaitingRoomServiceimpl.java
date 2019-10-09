package com.sm.open.core.service.service.pf.biz.tests.impl;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.dal.pf.biz.kb.PfCaseHistoryDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestPaperDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestPlanDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestWaitingRoomDao;
import com.sm.open.core.model.dto.pf.biz.tests.*;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.enums.TestPlanStatusEnum;
import com.sm.open.core.model.vo.pf.biz.casehistory.FaqMedicalrecVo;
import com.sm.open.core.model.vo.pf.biz.test.*;
import com.sm.open.core.model.vo.pf.biz.test.eva.*;
import com.sm.open.core.model.vo.pf.biz.test.paper.PfTestPaperInfoVo;
import com.sm.open.core.service.service.pf.biz.tests.PfTestWaitingRoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PfTestWaitingRoomServiceimpl implements PfTestWaitingRoomService {

    @Resource
    private PfTestWaitingRoomDao pfTestWaitingRoomDao;

    @Resource
    private PfCaseHistoryDao pfCaseHistoryDao;

    @Resource
    private PfTestPaperDao pfTestPaperDao;

    @Resource
    private PfTestPlanDao pfTestPlanDao;

    @Override
    public Long countWaitingRoom(PfTestWatingRoomDto dto) {
        return pfTestWaitingRoomDao.countWaitingRoom(dto);
    }

    @Override
    public List<PfTestWaitingRoomVo> listWaitingRoom(PfTestWatingRoomDto dto) {
        return pfTestWaitingRoomDao.listWaitingRoom(dto);
    }

    @Override
    public Long countReceivePat(PfTestWatingRoomDto dto) {
        return pfTestWaitingRoomDao.countReceivePat(dto);
    }

    @Override
    public List<PfTestReceivePatVo> listReceivePat(PfTestWatingRoomDto dto) {
        return pfTestWaitingRoomDao.listReceivePat(dto);
    }

    @Override
    public PfTestPaperInfoVo selectTestPaperInto(PfTestExamDto dto) {
        PfTestPaperInfoVo pfTestPaperInfoVo = pfTestWaitingRoomDao.selectTestPaperInfo(dto);
        if (pfTestPaperInfoVo == null) {
            pfTestPaperInfoVo = new PfTestPaperInfoVo();
        }
        // 试卷信息
        ExmTestpaper exmTestpaper = new ExmTestpaper();
        if (dto.getIdTestpaper() == -1) {
            exmTestpaper.setNaTestpaper("自定义");
            ExmTestplan exmTestplan = pfTestPlanDao.selectPlanById(dto.getIdTestplan());
            if (exmTestplan != null) {
                pfTestPaperInfoVo.setPaperCreator(exmTestplan.getCreator());
                pfTestPaperInfoVo.setOrgName(exmTestplan.getOrgName());
            }
        } else {
            exmTestpaper = pfTestPaperDao.selectTestPaperById(dto.getIdTestpaper());
            pfTestPaperInfoVo.setOrgName(exmTestpaper.getOrgName());
            pfTestPaperInfoVo.setPaperCreator(exmTestpaper.getCreator());
        }

        // 病例信息
        FaqMedicalrecVo faqMedicalrecVo = pfCaseHistoryDao.selectCaseInfoById(dto.getIdMedicalrec());
        String testPaperName = exmTestpaper != null ? exmTestpaper.getNaTestpaper() : "";
        if (faqMedicalrecVo != null) {
            testPaperName += StringUtils.isNotBlank(faqMedicalrecVo.getName()) ? "-" + faqMedicalrecVo.getName() : "";
        }
        pfTestPaperInfoVo.setTestPaperName(testPaperName);
        return pfTestPaperInfoVo;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public PfWaitingRoomStartVo startExam(ExmTestexec dto) {
        PfWaitingRoomStartVo pfWaitingRoomStartVo = new PfWaitingRoomStartVo();
        Integer num = pfTestWaitingRoomDao.startExam(dto);
        if (num == 1) {
            ExmMedResult exmMedResult = new ExmMedResult();
            exmMedResult.setIdTestexec(dto.getIdTestexec());
            // 序号，暂时写死
            exmMedResult.setSerial(1);
            exmMedResult.setFgFinalResult(YesOrNoNum.YES.getCode());
            exmMedResult.setFgAsses(YesOrNoNum.NO.getCode());
            pfTestWaitingRoomDao.insertExmMedResult(exmMedResult);
            pfWaitingRoomStartVo.setIdTestexecResult(exmMedResult.getIdTestexecResult());

            // 修改计划明细状态
            pfTestPlanDao.updatePlanStatus(dto.getIdTestplanDetail(), TestPlanStatusEnum.EXEC.getCode());
        }
        pfWaitingRoomStartVo.setIdTestexec(dto.getIdTestexec());
        return pfWaitingRoomStartVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean endExam(ExmTestexec dto) {
        if (pfTestWaitingRoomDao.endExam(dto) == 1) {
            // 修改计划明细状态
            pfTestPlanDao.updatePlanStatus(dto.getIdTestplanDetail(), TestPlanStatusEnum.FINISH.getCode());
        }
        return true;
    }

    @Override
    public PfWaitingRoomPatVo selectPatInfo(PfTestExamTagDto dto) {
        PfWaitingRoomPatVo pfWaitingRoomPatVo = pfTestWaitingRoomDao.selectPatInfo(dto);
        PfWaitingRoomPatVo photo = pfTestWaitingRoomDao.selectPhotoInfo(dto.getIdMedicalrec());
        pfWaitingRoomPatVo.setExt(photo != null ? photo.getExt() : null);
        return pfWaitingRoomPatVo;
    }

    @Override
    public Long countTestCons(PfTestExamTagDto dto) {
        if (dto.getInquesPreFlag() == 0) {
            return pfTestWaitingRoomDao.countTestCons(dto);
        } else {
            return pfTestWaitingRoomDao.countTestConsPre(dto);
        }
    }

    @Override
    public List<FaqMedCaseInquesList> listTestCons(PfTestExamTagDto dto) {
        if (dto.getInquesPreFlag() == 0) {
            return pfTestWaitingRoomDao.listTestCons(dto);
        } else {
            return pfTestWaitingRoomDao.listTestConsPre(dto);
        }
    }

    @Override
    public Long saveConsQa(ExmMedResultInques dto) {
        Long idTestexecResultInques = pfTestWaitingRoomDao.isExistConsQa(dto);
        if (idTestexecResultInques == null) {
            pfTestWaitingRoomDao.saveConsQa(dto);
        } else {
            pfTestWaitingRoomDao.delConsQa(idTestexecResultInques, dto.getFgValid());
            dto.setIdTestexecResultInques(idTestexecResultInques);
        }
        return dto.getIdTestexecResultInques();
    }

    @Override
    public boolean editConsQa(PfExmMedResultDto dto) {
        if (dto.getType() == 1) {
            ExmMedResultInques exmMedResultInques = new ExmMedResultInques();
            exmMedResultInques.setIdTestexecResultInques(dto.getId());
            exmMedResultInques.setDesReason(dto.getDesReason());
            exmMedResultInques.setDesReply(dto.getDesReply());
            return pfTestWaitingRoomDao.editConsQa(exmMedResultInques) == 1 ? true : false;
        } else if (dto.getType() == 2) {
            ExmMedResultBody exmMedResultBody = new ExmMedResultBody();
            exmMedResultBody.setIdTestexecResultBody(dto.getId());
            exmMedResultBody.setDesReply(dto.getDesReply());
            return pfTestWaitingRoomDao.editCheckQa(exmMedResultBody) == 1 ? true : false;
        } else if (dto.getType() == 3) {
            ExmMedResultInspect exmMedResultInspect = new ExmMedResultInspect();
            exmMedResultInspect.setIdTestexecResultInspect(dto.getId());
            exmMedResultInspect.setDesReply(dto.getDesReply());
            return pfTestWaitingRoomDao.editExamQa(exmMedResultInspect) == 1 ? true : false;
        }
        return true;
    }

    @Override
    public boolean updateConsStatus(PfBachChangeStatusDto dto) {
        return pfTestWaitingRoomDao.updateConsStatus(dto) >= 1 ? true : false;
    }

    @Override
    public List<PfWaitingRoomConsVo> listConsQa(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.listConsQa(dto);
    }

    @Override
    public Long selectPic(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.selectPic(dto);
    }

    @Override
    public Long countTestCheck(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.countTestCheck(dto);
    }

    @Override
    public List<FaqMedCaseBodyList> listTestCheck(PfTestExamTagDto dto) {
        List<FaqMedCaseBodyList> lists = pfTestWaitingRoomDao.listTestCheck(dto);
        for (FaqMedCaseBodyList item : lists) {
            // 查询疾病
            if (StringUtils.isNotBlank(item.getIdDie())) {
                List<String> idDies = Arrays.asList(item.getIdDie().split(","));
                List<String> idDieTexts = pfTestWaitingRoomDao.selectManyDie(idDies);
                item.setIdDieText(StringUtils.join(idDieTexts.toArray(), ","));
            }
        }
        return lists;
    }

    @Override
    public Long saveCheckQa(ExmMedResultBody dto) {
        Long idTestexecResultBody = pfTestWaitingRoomDao.isExistCheckQa(dto);
        if (idTestexecResultBody == null) {
            pfTestWaitingRoomDao.saveCheckQa(dto);
        } else {
            pfTestWaitingRoomDao.delCheckQa(idTestexecResultBody, dto.getIdDie(), dto.getFgValid());
            dto.setIdTestexecResultBody(idTestexecResultBody);
        }
        return dto.getIdTestexecResultBody();
    }

    @Override
    public boolean editCheckQa(ExmMedResultBody dto) {
        return pfTestWaitingRoomDao.editCheckQa(dto) == 1 ? true : false;
    }

    @Override
    public boolean updateCheckStatus(PfBachChangeStatusDto dto) {
        return pfTestWaitingRoomDao.updateCheckStatus(dto) >= 1 ? true : false;
    }

    @Override
    public List<PfWaitingRoomCheckVo> listCheckQa(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.listCheckQa(dto);
    }

    @Override
    public Long countTestExam(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.countTestExam(dto);
    }

    @Override
    public List<FaqMedCaseInspectList> listTestExam(PfTestExamTagDto dto) {
        List<FaqMedCaseInspectList> lists = pfTestWaitingRoomDao.listTestExam(dto);
        for (FaqMedCaseInspectList item : lists) {
            // 查询疾病
            if (StringUtils.isNotBlank(item.getIdDie())) {
                List<String> idDies = Arrays.asList(item.getIdDie().split(","));
                List<String> idDieTexts = pfTestWaitingRoomDao.selectManyDie(idDies);
                item.setIdDieText(StringUtils.join(idDieTexts.toArray(), ","));
            }
        }
        return lists;
    }

    @Override
    public Long saveExamQa(ExmMedResultInspect dto) {
        Long idTestexecResultInspect = pfTestWaitingRoomDao.isExistExamQa(dto);
        if (idTestexecResultInspect == null) {
            pfTestWaitingRoomDao.saveExamQa(dto);
        } else {
            pfTestWaitingRoomDao.delExamQa(idTestexecResultInspect, dto.getIdDie(), dto.getFgValid());
            dto.setIdTestexecResultInspect(idTestexecResultInspect);
        }
        return dto.getIdTestexecResultInspect();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BigDecimal saveBatchExamQa(PfTestExamTagDto dto) {
        if (dto.isChecked()) {
            // 获取分类节点下的所有检验项目
            List<FaqMedCaseInspectList> list = pfTestWaitingRoomDao.listAllExamByIdInspect(dto);
            for (FaqMedCaseInspectList item : list) {
                // 勾选该分类节点下的所有检验项目
                ExmMedResultInspect exmMedResultInspect = new ExmMedResultInspect();
                exmMedResultInspect.setIdTestexecResult(dto.getIdTestexecResult());
                exmMedResultInspect.setIdMedCaseList(item.getIdMedCaseList());
                exmMedResultInspect.setIdInspectItem(item.getIdInspectItem());

                pfTestWaitingRoomDao.saveExamQa(exmMedResultInspect);
            }
            // 统计金额
            return pfTestWaitingRoomDao.sumCostMoney(dto);
        } else {
            List<Long> list = pfTestWaitingRoomDao.listAllIdInspect(dto);
            // 删除检验项目
            pfTestWaitingRoomDao.delExamQaByIdTestexecResult(dto.getIdTestexecResult(), list);
            // 统计金额
            return BigDecimal.ZERO;
        }
    }

    @Override
    public boolean editExamQa(ExmMedResultInspect dto) {
        return pfTestWaitingRoomDao.editExamQa(dto) == 1 ? true : false;
    }

    @Override
    public boolean updateExamStatus(PfBachChangeStatusDto dto) {
        return pfTestWaitingRoomDao.updateExamStatus(dto) >= 1 ? true : false;
    }

    @Override
    public List<PfWaitingRoomExamVo> listExamQa(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.listExamQa(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveReferral(ExmMedResultReferral dto) {
        if (dto.getIdTestexecResultReferral() == null) {
            pfTestWaitingRoomDao.saveReferral(dto);
            // 导入到确诊
            ExmMedResultDiagnosis diagnosis = new ExmMedResultDiagnosis();
            diagnosis.setIdTestexecResult(dto.getIdTestexecResult());
            diagnosis.setIdDie(dto.getIdDie());
            diagnosis.setIdTestexecResultReferral(dto.getIdTestexecResultReferral());
            pfTestWaitingRoomDao.addDiagnosis(diagnosis);
        } else {
            // 排除拟诊
            pfTestWaitingRoomDao.outReferral(dto);
            // 修改确诊标识
            pfTestWaitingRoomDao.updateQzFlag(dto.getIdTestexecResultReferral());
        }
        return dto.getIdTestexecResultReferral();
    }

    @Override
    public List<ExmMedResultReferral> listReferral(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.listReferral(dto);
    }

    @Override
    public ExmMedResultOrder selectOrders(Long idTestexecResult) {
        return pfTestWaitingRoomDao.selectOrders(idTestexecResult);
    }

    @Override
    public Long saveOrder(ExmMedResultOrder dto) {
        if (dto.getIdTestexecResultOrder() == null) {
            pfTestWaitingRoomDao.saveOrder(dto);
        } else {
            pfTestWaitingRoomDao.editOrder(dto);
        }
        return dto.getIdTestexecResultOrder();
    }

    @Override
    public boolean saveDrugs(PfCommonListDto dto) {
        if (dto.getExtType().equals("long")) {
            pfTestWaitingRoomDao.saveLongDrugs(dto);
        } else {
            pfTestWaitingRoomDao.saveShortDrugs(dto);
        }
        return true;
    }

    @Override
    public List<ExmMedResultOrderLogDrugs> listLongDrugs(Long idTestexecResultOrder) {
        return pfTestWaitingRoomDao.listLongDrugs(idTestexecResultOrder);
    }

    @Override
    public List<ExmMedResultOrderShortDrugs> listShortDrugs(Long idTestexecResultOrder) {
        return pfTestWaitingRoomDao.listShortDrugs(idTestexecResultOrder);
    }

    @Override
    public boolean delDrugs(String type, Long id) {
        if (type.equals("long")) {
            pfTestWaitingRoomDao.delLongDrugs(id);
        } else {
            pfTestWaitingRoomDao.delShortDrugs(id);
        }
        return true;
    }

    @Override
    public Long saveDiagnosis(ExmMedResultDiagnosis dto) {
        if (dto.getIdTestexecResultDiagnosis() == null) {
            pfTestWaitingRoomDao.addDiagnosis(dto);
        } else {
            pfTestWaitingRoomDao.editDiagnosis(dto);
        }
        return dto.getIdTestexecResultDiagnosis();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delDiagnosis(Long idTestexecResultDiagnosis) {
        pfTestWaitingRoomDao.delDiagnosis(idTestexecResultDiagnosis);
        pfTestWaitingRoomDao.delDieReasonByResultId(idTestexecResultDiagnosis);
        return true;
    }

    @Override
    public Long saveSummary(ExmMedResultSummary dto) {
        if (dto.getIdTestexecResultSumary() == null) {
            // 根据
            Long idTestexecResultSumary = pfTestWaitingRoomDao.getIdByIdTestexecResult(dto.getIdTestexecResult());
            if (idTestexecResultSumary == null) {
                pfTestWaitingRoomDao.addSummary(dto);
            } else {
                dto.setIdTestexecResultSumary(idTestexecResultSumary);
                pfTestWaitingRoomDao.editSummary(dto);
            }
        } else {
            pfTestWaitingRoomDao.editSummary(dto);
        }
        return dto.getIdTestexecResultSumary();
    }

    @Override
    public boolean saveDieReason(List<ExmMedResultDieReason> list) {
        for (ExmMedResultDieReason dieReason : list) {
            if (dieReason.getSdEvaEffciency().equals("1")) {
                dieReason.setIdInques(dieReason.getExtId());
            } else if (dieReason.getSdEvaEffciency().equals("2")) {
                dieReason.setIdBody(dieReason.getExtId());
            } else if (dieReason.getSdEvaEffciency().equals("3")) {
                dieReason.setIdInspectItem(dieReason.getExtId());
            }
        }
        return pfTestWaitingRoomDao.saveDieReason(list) >= 1 ? true : false;
    }

    @Override
    public boolean delDieReason(Long idDieReason) {
        return pfTestWaitingRoomDao.delDieReason(idDieReason) == 1 ? true : false;
    }

    @Override
    public List<PfDiagnosisVo> selectAllDiagnosis(Long idTestexecResult) {
        List<ExmMedResultDiagnosis> diagnoses = pfTestWaitingRoomDao.listDiagnosis(idTestexecResult);
        List<PfDiagnosisVo> diagnosisVoList = BeanUtil.convertList(diagnoses, PfDiagnosisVo.class);
        for (PfDiagnosisVo pfDiagnosisVo : diagnosisVoList) {
            List<PfWaitingRoomDieReasonVo> dieReasonVos = pfTestWaitingRoomDao.listDieReason(pfDiagnosisVo.getIdTestexecResultDiagnosis());
            pfDiagnosisVo.setIdeReasonList(dieReasonVos);
        }
        return diagnosisVoList;
    }

    @Override
    public List<ExmMedResultReferral> selectAllReferral(Long idTestexecResult) {
        return pfTestWaitingRoomDao.selectAllReferral(idTestexecResult);
    }

    @Override
    public PfWaitingRoomDiagnosisVo selectDiagnosis(Long idTestexecResult) {
        PfWaitingRoomDiagnosisVo diagnosisVo = new PfWaitingRoomDiagnosisVo();
        //ExmMedResultDiagnosis diagnosis = pfTestWaitingRoomDao.selectDiagnosis(idTestexecResult);
        ExmMedResultSummary summary = pfTestWaitingRoomDao.selectSummary(idTestexecResult);
        /*if (diagnosis != null) {
            diagnosisVo.setIdTestexecResultDiagnosis(diagnosis.getIdTestexecResultDiagnosis());
            diagnosisVo.setIdTestexecResult(idTestexecResult);
            diagnosisVo.setIdDie(diagnosis.getIdDie());
            diagnosisVo.setIdDieText(diagnosis.getIdDieText());
        }*/
        if (summary != null) {
            diagnosisVo.setIdTestexecResultSumary(summary.getIdTestexecResultSumary());
            diagnosisVo.setDieSumary(summary.getDieSumary());
        }
        return diagnosisVo;
    }

    @Override
    public ExmMedResultSummary selectSummary(Long idTestexecResult) {
        return pfTestWaitingRoomDao.selectSummary(idTestexecResult);
    }

    @Override
    public List<PfWaitingRoomDieReasonVo> listReadyDieReason(Long idTestexecResult, String keyword) {
        return pfTestWaitingRoomDao.listReadyDieReason(idTestexecResult, keyword);
    }

    @Override
    public List<PfWaitingRoomDieReasonVo> listDieReason(Long idTestexecResultDiagnosis) {
        return pfTestWaitingRoomDao.listDieReason(idTestexecResultDiagnosis);
    }

    @Override
    public List<PfEvaExecVo> selectScore(Long idTestexecResult, Long idMedicalrec) {
        // 查询已执行病例结果id
        List<Long> execResultIds = pfTestWaitingRoomDao.getExecResultId(idMedicalrec);
        if (CollectionUtils.isEmpty(execResultIds)) {
            return null;
        }
        return pfTestWaitingRoomDao.getScore(execResultIds);
    }

    @Override
    public List<PfEvaExecVo> listEva(PfTestEvaDto dto) {
        return pfTestWaitingRoomDao.listEva(dto);
    }

    @Override
    public List<ExmEvaLog> listEvaLog(Long idTestexecResult) {
        return pfTestWaitingRoomDao.listEvaLog(idTestexecResult);
    }

    @Override
    public boolean medEva(Long idTestexecResult) {
        PfEvaExecDto dto = new PfEvaExecDto();
        dto.setIdTestexecResult(idTestexecResult);
        pfTestWaitingRoomDao.medEva(dto);
        if (dto.getParCode() != 0) {
            throw new BizRuntimeException(String.valueOf(dto.getParCode()), dto.getParMsg());
        } else {
            pfTestWaitingRoomDao.updateExmMedResultFlag(idTestexecResult);
        }
        return true;
    }

    @Override
    public boolean editEva(ExmEvaDimension dto) {
        return pfTestWaitingRoomDao.editEva(dto) == 1 ? true : false;

    }

    @Override
    public List<PfExecLogVo> listExecLog(Long idTestexecResult) {
        List<PfExecLogVo> inquesLog = pfTestWaitingRoomDao.listExecLogInques(idTestexecResult);
        List<PfExecLogVo> bodyLog = pfTestWaitingRoomDao.listExecLogBody(idTestexecResult);
        inquesLog.addAll(bodyLog);
        List<PfExecLogVo> inspectLog = pfTestWaitingRoomDao.listExecLogInspect(idTestexecResult);
        inquesLog.addAll(inspectLog);
        List<PfExecLogVo> diagnosisLog = pfTestWaitingRoomDao.listExecLogDiagnosis(idTestexecResult);
        for (PfExecLogVo pfExecLogVo : diagnosisLog) {
            inquesLog.add(pfExecLogVo);
            List<PfExecLogVo> diagnosisReasonLog = pfTestWaitingRoomDao.listExecLogDiagnosisReason(pfExecLogVo.getExtId());
            inquesLog.addAll(diagnosisReasonLog);
        }
        ExmMedResultOrder selectOrders = pfTestWaitingRoomDao.selectOrders(idTestexecResult);
        if (selectOrders != null) {
            inquesLog.addAll(buildOrdersLog(selectOrders));
        }
        return inquesLog;
    }

    public List<PfExecLogVo> buildOrdersLog(ExmMedResultOrder selectOrders) {
        List<PfExecLogVo> ordersLog = new ArrayList<>();
        PfExecLogVo pfExecLogVo;

        if (StringUtils.isNotBlank(selectOrders.getSdNursRout())) {
            pfExecLogVo = new PfExecLogVo();
            pfExecLogVo.setStage("医嘱");
            pfExecLogVo.setLogDate(selectOrders.getGmtCreate());
            pfExecLogVo.setOperation("添加护理常规");
            ordersLog.add(pfExecLogVo);
        }
        if (StringUtils.isNotBlank(selectOrders.getCdNursLevel())) {
            pfExecLogVo = new PfExecLogVo();
            pfExecLogVo.setStage("医嘱");
            pfExecLogVo.setLogDate(selectOrders.getGmtCreate());
            pfExecLogVo.setOperation("添加护理级别");
            ordersLog.add(pfExecLogVo);
        }
        if (StringUtils.isNotBlank(selectOrders.getSdDiet())) {
            pfExecLogVo = new PfExecLogVo();
            pfExecLogVo.setStage("医嘱");
            pfExecLogVo.setLogDate(selectOrders.getGmtCreate());
            pfExecLogVo.setOperation("添加饮食分类");
            ordersLog.add(pfExecLogVo);
        }
        if (StringUtils.isNotBlank(selectOrders.getSdPosition())) {
            pfExecLogVo = new PfExecLogVo();
            pfExecLogVo.setStage("医嘱");
            pfExecLogVo.setLogDate(selectOrders.getGmtCreate());
            pfExecLogVo.setOperation("添加体位");
            ordersLog.add(pfExecLogVo);
        }
        if (StringUtils.isNotBlank(selectOrders.getDesCheck())) {
            pfExecLogVo = new PfExecLogVo();
            pfExecLogVo.setStage("医嘱");
            pfExecLogVo.setLogDate(selectOrders.getGmtCreate());
            pfExecLogVo.setOperation("添加(一次性)检查或处置");
            ordersLog.add(pfExecLogVo);
        }
        if (StringUtils.isNotBlank(selectOrders.getDesSpecial())) {
            pfExecLogVo = new PfExecLogVo();
            pfExecLogVo.setStage("医嘱");
            pfExecLogVo.setLogDate(selectOrders.getGmtCreate());
            pfExecLogVo.setOperation("添加特殊处理");
            ordersLog.add(pfExecLogVo);
        }
        return ordersLog;
    }

    @Override
    public ExmEvaResult selectEvaResult(Long idTestexecResult) {
        return pfTestWaitingRoomDao.selectEvaResult(idTestexecResult);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveReferralReason(List<ExmMedResultReferralReason> list) {
        boolean qzFlag = false;
        Long idTestexecResultReferral = null;
        Long idTestexecResultDiagnosis = null;
        ExmMedResultDieReason exmMedResultDieReason;
        List<ExmMedResultDieReason> dieReasons = new ArrayList<>(list.size());
        for (ExmMedResultReferralReason dieReason : list) {
            idTestexecResultReferral = dieReason.getIdTestexecResultReferral();
            if (dieReason.getFgExclude().equals(YesOrNoNum.NO.getCode())) {
                qzFlag = true;
            }
            if (dieReason.getSdEvaEffciency().equals("1")) {
                dieReason.setIdInques(dieReason.getExtId());
            } else if (dieReason.getSdEvaEffciency().equals("2")) {
                dieReason.setIdBody(dieReason.getExtId());
            } else if (dieReason.getSdEvaEffciency().equals("3")) {
                dieReason.setIdInspectItem(dieReason.getExtId());
            }
            exmMedResultDieReason = BeanUtil.convert(dieReason, ExmMedResultDieReason.class);
            dieReasons.add(exmMedResultDieReason);
            if (idTestexecResultDiagnosis == null) {
                idTestexecResultDiagnosis = pfTestWaitingRoomDao.selectQzId(idTestexecResultReferral);
            }
            exmMedResultDieReason.setIdTestexecResultDiagnosis(idTestexecResultDiagnosis);
        }
        if (qzFlag) {
            // 导入到确诊
            saveDieReason(dieReasons);
        }
        return pfTestWaitingRoomDao.saveReferralReason(list) >= 1 ? true : false;
    }

    @Override
    public List<PfReferralReasonVo> listReferralReason(Long idTestexecResultReferral) {
        return pfTestWaitingRoomDao.listReferralReason(idTestexecResultReferral);
    }

    @Override
    public boolean delPlanDetail(PfBachChangeStatusDto dto) {
        if (pfTestWaitingRoomDao.selectDelPlanDetailstatus(dto) >= 1) {
            throw new BizRuntimeException("planStatusChange", "删除失败！原因：候诊记录状态已改变。请刷新列表后重试！");
        }
        return pfTestWaitingRoomDao.delPlanDetail(dto) >= 1 ? true : false;
    }

    @Override
    public boolean saveExecSerialNo(ExmTestexec dto) {
        return pfTestWaitingRoomDao.saveExecSerialNo(dto) >= 1 ? true : false;
    }

    @Override
    public List<BasDie> listAllReferralDie(Long idTestexecResult, String keywords) {
        return pfTestWaitingRoomDao.listAllReferralDie(idTestexecResult, keywords);
    }

    @Override
    public List<PfDiagnosticAnalysisVo> listDiagnosticAnalysis(PfTestEvaDto dto) {
        List<PfDiagnosticAnalysisVo> qzList = pfTestWaitingRoomDao.listQzItem(dto);
        if (dto.getParCode() != 0) {
            throw new BizRuntimeException(String.valueOf(dto.getParCode()), dto.getParMsg());
        }
        for (PfDiagnosticAnalysisVo pfDiagnosticAnalysisVo : qzList) {
            pfDiagnosticAnalysisVo.setType(1);
        }
        List<PfDiagnosticAnalysisVo> pcnzList = pfTestWaitingRoomDao.listPcnzItem(dto);
        if (dto.getParCode() != 0) {
            throw new BizRuntimeException(String.valueOf(dto.getParCode()), dto.getParMsg());
        }
        for (PfDiagnosticAnalysisVo pfDiagnosticAnalysisVo : pcnzList) {
            pfDiagnosticAnalysisVo.setType(2);
        }
        qzList.addAll(pcnzList);
        return qzList;
    }

    @Override
    public List<PfDiagnosticAnalysisDetailVo> listDiagnosticAnalysisDetail(PfTestEvaDto dto) {
        List<PfDiagnosticAnalysisDetailVo> allList = new ArrayList<>();
        if (StringUtils.isBlank(dto.getIdDieStr())) {
            return Collections.EMPTY_LIST;
        }
        List<String> idList = Arrays.asList(dto.getIdDieStr().split("\\|"));

        if (dto.getType() == 1) {
            List<PfAnalysisVo> qzAnalysisVos = pfTestWaitingRoomDao.getDiagnosislReason(dto);
            for (String str : idList) {
                for (PfAnalysisVo pfAnalysisVo : qzAnalysisVos) {
                    if (str.equals(pfAnalysisVo.getIdDie())) {
                        allList.addAll(getQaDetal(pfAnalysisVo, dto.getIdMedicalrec()));
                    }
                }
            }

        } else {
            List<PfAnalysisVo> pcqzAnalysisVos = pfTestWaitingRoomDao.getUnReferralReason(dto);
            for (String str : idList) {
                for (PfAnalysisVo pcqzAnalysisVo : pcqzAnalysisVos) {
                    if (str.equals(pcqzAnalysisVo.getIdDie())) {
                        allList.addAll(getQaDetal(pcqzAnalysisVo, dto.getIdMedicalrec()));
                    }
                }
            }
        }
        return allList;
    }

    private List<PfDiagnosticAnalysisDetailVo> getQaDetal(PfAnalysisVo pfAnalysisVo, Long idMedicalrec) {
        Integer sdEvaEffciency = pfAnalysisVo.getSdEvaEffciency();
        String idReason = pfAnalysisVo.getIdReason();
        Integer flag = pfAnalysisVo.getFlag();
        if (StringUtils.isBlank(idReason)) {
            return Collections.EMPTY_LIST;
        }

        List<PfDiagnosticAnalysisDetailVo> allList = new ArrayList<>();
        List<String> idReasonList = Arrays.asList(idReason.split("\\|"));

        String cdMedAsse = null;
        if (sdEvaEffciency == 1) {
            cdMedAsse = "004";
        } else if (sdEvaEffciency == 2) {
            cdMedAsse = "005";
        } else if (sdEvaEffciency == 3) {
            cdMedAsse = "006";
        }
        Long idMedCase = pfTestWaitingRoomDao.getIdMedCase(idMedicalrec, cdMedAsse);

        if (idMedCase == null) {
            return Collections.EMPTY_LIST;
        }

        if (sdEvaEffciency == 1) {
            allList = pfTestWaitingRoomDao.getInques(idReasonList, idMedCase);
        } else if (sdEvaEffciency == 2) {
            allList = pfTestWaitingRoomDao.getBody(idReasonList, idMedCase);
        } else if (sdEvaEffciency == 3) {
            allList = pfTestWaitingRoomDao.getCheck(idReasonList, idMedCase);
        }
        for (PfDiagnosticAnalysisDetailVo pfDiagnosticAnalysisDetailVo : allList) {
            pfDiagnosticAnalysisDetailVo.setSdEvaEffciency(sdEvaEffciency);
            pfDiagnosticAnalysisDetailVo.setFlag(flag);
        }
        return allList;
    }

}
