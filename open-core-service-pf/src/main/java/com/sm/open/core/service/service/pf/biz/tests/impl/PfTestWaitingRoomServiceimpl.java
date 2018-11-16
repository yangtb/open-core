package com.sm.open.core.service.service.pf.biz.tests.impl;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.core.dal.pf.biz.kb.PfCaseHistoryDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestPaperDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestPlanDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestWaitingRoomDao;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamTagDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestWatingRoomDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.casehistory.FaqMedicalrecVo;
import com.sm.open.core.model.vo.pf.biz.test.*;
import com.sm.open.core.model.vo.pf.biz.test.paper.PfTestPaperInfoVo;
import com.sm.open.core.service.service.pf.biz.tests.PfTestWaitingRoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
        PfTestPaperInfoVo pfTestPaperInfoVo = pfTestWaitingRoomDao.selectTestPaperInfo(dto.getIdTestplanDetail(), dto.getIdStudent());
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
            exmMedResult.setFgAsses(YesOrNoNum.YES.getCode());
            pfTestWaitingRoomDao.insertExmMedResult(exmMedResult);
            pfWaitingRoomStartVo.setIdTestexecResult(exmMedResult.getIdTestexecResult());
        }
        pfWaitingRoomStartVo.setIdTestexec(dto.getIdTestexec());
        return pfWaitingRoomStartVo;
    }

    @Override
    public boolean endExam(ExmTestexec dto) {
        return pfTestWaitingRoomDao.endExam(dto) == 1 ? true : false;
    }

    @Override
    public PfWaitingRoomPatVo selectPatInfo(PfTestExamTagDto dto) {
        PfWaitingRoomPatVo pfWaitingRoomPatVo = pfTestWaitingRoomDao.selectPatInfo(dto);
        PfWaitingRoomPatVo photo = pfTestWaitingRoomDao.selectPhotoInfo(dto.getIdMedicalrec());
        pfWaitingRoomPatVo.setExt(photo != null ? photo.getExt() : null);
        return pfWaitingRoomPatVo;
    }

    @Override
    public List<FaqMedCaseInquesList> listTestCons(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.listTestCons(dto);
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
    public List<FaqMedCaseBodyList> listTestCheck(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.listTestCheck(dto);
    }

    @Override
    public Long saveCheckQa(ExmMedResultBody dto) {
        Long idTestexecResultBody = pfTestWaitingRoomDao.isExistCheckQa(dto);
        if (idTestexecResultBody == null) {
            pfTestWaitingRoomDao.saveCheckQa(dto);
        } else {
            pfTestWaitingRoomDao.delCheckQa(idTestexecResultBody, dto.getFgValid());
            dto.setIdTestexecResultBody(idTestexecResultBody);
        }
        return dto.getIdTestexecResultBody();
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
    public List<FaqMedCaseInspectList> listTestExam(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.listTestExam(dto);
    }

    @Override
    public Long saveExamQa(ExmMedResultInspect dto) {
        Long idTestexecResultInspect = pfTestWaitingRoomDao.isExistExamQa(dto);
        if (idTestexecResultInspect == null) {
            pfTestWaitingRoomDao.saveExamQa(dto);
        } else {
            pfTestWaitingRoomDao.delExamQa(idTestexecResultInspect, dto.getFgValid());
            dto.setIdTestexecResultInspect(idTestexecResultInspect);
        }
        return dto.getIdTestexecResultInspect();
    }

    @Override
    public boolean updateExamStatus(PfBachChangeStatusDto dto) {
        return pfTestWaitingRoomDao.updateExamStatus(dto) >= 1 ? true : false;
    }

    @Override
    public List<PfWaitingRoomExamVo> listExamQa(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.listExamQa(dto);
    }

    @Override
    public Long saveReferral(ExmMedResultReferral dto) {
        if (dto.getIdTestexecResultReferral() == null) {
            pfTestWaitingRoomDao.saveReferral(dto);
        } else {
            pfTestWaitingRoomDao.outReferral(dto);
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
            pfTestWaitingRoomDao.addSummary(dto);
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
            }else if (dieReason.getSdEvaEffciency().equals("3")) {
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
    public PfWaitingRoomDiagnosisVo selectDiagnosis(Long idTestexecResult) {
        PfWaitingRoomDiagnosisVo diagnosisVo = new PfWaitingRoomDiagnosisVo();
        ExmMedResultDiagnosis diagnosis = pfTestWaitingRoomDao.selectDiagnosis(idTestexecResult);
        ExmMedResultSummary summary = pfTestWaitingRoomDao.selectSummary(idTestexecResult);
        if (diagnosis != null) {
            diagnosisVo.setIdTestexecResultDiagnosis(diagnosis.getIdTestexecResultDiagnosis());
            diagnosisVo.setIdTestexecResult(idTestexecResult);
            diagnosisVo.setIdDie(diagnosis.getIdDie());
            diagnosisVo.setIdDieText(diagnosis.getIdDieText());
        }
        if (summary != null) {
            diagnosisVo.setIdTestexecResultSumary(summary.getIdTestexecResultSumary());
            diagnosisVo.setDieSumary(summary.getDieSumary());
        }
        return diagnosisVo;
    }

    @Override
    public List<PfWaitingRoomDieReasonVo> listReadyDieReason(Long idTestexecResult) {
        return pfTestWaitingRoomDao.listReadyDieReason(idTestexecResult);
    }

    @Override
    public List<PfWaitingRoomDieReasonVo> listDieReason(Long idTestexecResultDiagnosis) {
        return pfTestWaitingRoomDao.listDieReason(idTestexecResultDiagnosis);
    }

}
