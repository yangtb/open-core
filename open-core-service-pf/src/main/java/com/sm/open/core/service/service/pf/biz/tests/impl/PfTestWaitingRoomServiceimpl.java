package com.sm.open.core.service.service.pf.biz.tests.impl;

import com.alibaba.fastjson.JSON;
import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.dal.pf.biz.disease.PfDiseaseDao;
import com.sm.open.core.dal.pf.biz.kb.PfCaseHistoryDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestPaperDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestPlanDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestWaitingRoomDao;
import com.sm.open.core.dal.pf.common.upload.PfUploadDao;
import com.sm.open.core.model.dto.pf.biz.tests.*;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.enums.TestPlanStatusEnum;
import com.sm.open.core.model.vo.pf.PfOrgChartVo;
import com.sm.open.core.model.vo.pf.biz.casehistory.FaqMedicalrecVo;
import com.sm.open.core.model.vo.pf.biz.disease.PfDiseaseZtreeVo;
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
import java.util.*;

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

    @Resource
    private PfDiseaseDao pfDiseaseDao;

    @Resource
    private PfUploadDao pfUploadDao;

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
        List<PfWaitingRoomConsVo> list = pfTestWaitingRoomDao.listConsQa(dto);
        List<Long> ids;
        for (PfWaitingRoomConsVo item : list) {
            ids = new ArrayList<>();
            if (StringUtils.isNotBlank(item.getIdMedia())) {
                List<String> idsStr = Arrays.asList(item.getIdMedia().split(","));
                for (String str : idsStr) {
                    ids.add(Long.valueOf(str));
                }
                item.setMediaList(pfUploadDao.selectBaseMediaByIds(ids));
            }
        }
        return list;
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
                List<String> idDieTexts = pfTestWaitingRoomDao.selectManyNzDie(idDies);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateCheckStatus(PfBachChangeStatusDto dto) {
        if ("1".equals(dto.getExtType())) {
            pfTestWaitingRoomDao.updateCheckStatusByIdTestexecResult(dto.getExtId(), dto.getOperationType());
        }
        return pfTestWaitingRoomDao.updateCheckStatus(dto) >= 1 ? true : false;
    }

    @Override
    public List<PfWaitingRoomCheckVo> listCheckQa(PfTestExamTagDto dto) {
        List<PfWaitingRoomCheckVo> list = pfTestWaitingRoomDao.listCheckQa(dto);
        List<Long> ids;
        for (PfWaitingRoomCheckVo item : list) {
            ids = new ArrayList<>();
            if (StringUtils.isNotBlank(item.getIdMedia())) {
                List<String> idsStr = Arrays.asList(item.getIdMedia().split(","));
                for (String str : idsStr) {
                    ids.add(Long.valueOf(str));
                }
                item.setMediaList(pfUploadDao.selectBaseMediaByIds(ids));
            }
        }
        return list;
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
                List<String> idDieTexts = pfTestWaitingRoomDao.selectManyNzDie(idDies);
                item.setIdDieText(StringUtils.join(idDieTexts.toArray(), ","));
            }
        }
        return lists;
    }

    @Override
    public BigDecimal examAmountTotal(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.examAmountTotal(dto);
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
    public BigDecimal saveBatchExamQa(List<ExmMedResultInspect> list) {
        if (CollectionUtils.isEmpty(list)) {
            return BigDecimal.ZERO;
        }

        for (ExmMedResultInspect exmMedResultInspect : list) {
            if (pfTestWaitingRoomDao.isExistExamQa(exmMedResultInspect) == null) {
                pfTestWaitingRoomDao.saveExamQa(exmMedResultInspect);
            }
        }
        // 统计金额
        return BigDecimal.ZERO;
    }

    @Override
    public boolean editExamQa(ExmMedResultInspect dto) {
        return pfTestWaitingRoomDao.editExamQa(dto) == 1 ? true : false;
    }

    @Override
    public boolean updateExamStatus(PfBachChangeStatusDto dto) {
        if ("1".equals(dto.getExtType())) {
            pfTestWaitingRoomDao.updateExamStatusByIdTestexecResult(dto.getExtId(), dto.getOperationType());
        }
        return pfTestWaitingRoomDao.updateExamStatus(dto) >= 1 ? true : false;
    }

    @Override
    public List<PfWaitingRoomExamVo> listExamQa(PfTestExamTagDto dto) {
        List<PfWaitingRoomExamVo> list = pfTestWaitingRoomDao.listExamQa(dto);
        List<Long> ids;
        for (PfWaitingRoomExamVo item : list) {
            ids = new ArrayList<>();
            if (StringUtils.isNotBlank(item.getIdMedia())) {
                List<String> idsStr = Arrays.asList(item.getIdMedia().split(","));
                for (String str : idsStr) {
                    ids.add(Long.valueOf(str));
                }
                item.setMediaList(pfUploadDao.selectBaseMediaByIds(ids));
            }
        }
        return list;
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
            diagnosis.setFgDieClass(dto.getFgDieClass());
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
    public boolean updateReferral(ExmMedResultReferral dto) {
        return pfTestWaitingRoomDao.updateReferral(dto) == 1 ? true : false;
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
            Long idTestexecResultDiagnosis = pfTestWaitingRoomDao.getDiagnosisId(dto);
            if (idTestexecResultDiagnosis == null) {
                pfTestWaitingRoomDao.addDiagnosis(dto);
            } else {
                dto.setIdTestexecResultDiagnosis(idTestexecResultDiagnosis);
                pfTestWaitingRoomDao.editDiagnosis(dto);
            }
        } else {
            pfTestWaitingRoomDao.editDiagnosis(dto);
        }
        return dto.getIdTestexecResultDiagnosis();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveIdentifyDiagnosis(ExmMedResultIdentify dto) {
        // 鉴别诊断
        if (dto.getIdTestexecResultIdentify() == null) {
            pfTestWaitingRoomDao.insertExmMedResultIdentify(dto);
        } else {
            pfTestWaitingRoomDao.updateExmMedResultIdentify(dto);
        }
        // 删除确诊理由
        pfTestWaitingRoomDao.delExmMedResultIdentifyReason(dto.getIdTestexecResultIdentify());
        // 确诊理由
        List<ExmMedResultIdentifyReason> reasonList;
        if (StringUtils.isBlank(dto.getReasonList())) {
            return dto.getIdTestexecResultIdentify();
        } else {
            reasonList = JSON.parseArray(dto.getReasonList(), ExmMedResultIdentifyReason.class);
        }
        if (!CollectionUtils.isEmpty(reasonList)) {
            for (ExmMedResultIdentifyReason item : reasonList) {
                item.setIdTestexecResultIdentify(dto.getIdTestexecResultIdentify());
                if (item.getSdEvaEffciency().equals("1")) {
                    item.setIdInques(item.getExtId());
                } else if (item.getSdEvaEffciency().equals("2")) {
                    item.setIdBody(item.getExtId());
                } else if (item.getSdEvaEffciency().equals("3")) {
                    item.setIdInspectItem(item.getExtId());
                }
                pfTestWaitingRoomDao.insertExmMedResultIdentifyReason(item);
            }
        }
        return dto.getIdTestexecResultIdentify();
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
    public PfWaitingRoomDiagnosisVo selectDiagnosis(ExmMedResultDiagnosis dto) {
        PfWaitingRoomDiagnosisVo diagnosisVo = pfTestWaitingRoomDao.selectDiagnosisNew(dto);
        /*
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
        }*/
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
        List<PfEvaExecVo> list = pfTestWaitingRoomDao.listEva(dto);
        for (PfEvaExecVo item : list) {
            if (StringUtils.isNotBlank(item.getNzName()) && "1".equals(item.getFgSystemAlgorithm())
                    && StringUtils.isNotBlank(item.getDesDimemsion())) {
                item.setNzName(item.getNzName() + "(" + item.getDesDimemsion() + ")");
            }
        }
        return list;
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

    @Override
    public List<PfDiseaseZtreeVo> listDiseaseCatalogueTree(PfCatalogueTreeDto dto) {
        if (dto.getMainCatalogue() == 1) {
            return pfDiseaseDao.listDiseaseCatalogueTree(dto);
        } else {
            List<PfDiseaseZtreeVo> list = pfDiseaseDao.listDieCatalogueTree(dto);
            List<PfDiseaseZtreeVo> listDie = pfDiseaseDao.listDiseaseTreeByCondition(dto, list);
            if (CollectionUtils.isEmpty(list)) {
                return listDie;
            } else {
                list.addAll(listDie);
            }
            return list;
        }
    }

    @Override
    public String selectReferralChartData(PfTestEvaDto dto) {
        PfOrgChartVo pfOrgChartVo = new PfOrgChartVo();
        if (dto.getChartType() == 1 || dto.getChartType() == 2) {
            // 一级目录固定
            pfOrgChartVo.setName("拟诊");
            pfOrgChartVo.setType(1);
            // 二级目录

            List<String> idReferrals = new ArrayList<>();
            if (dto.getChartType() == 1) {
                List<String> nzDies = pfTestWaitingRoomDao.getNzDie(dto.getIdTestexecResult());
                if (CollectionUtils.isEmpty(nzDies)) {
                    return JSON.toJSONString(pfOrgChartVo);
                }
                for (String item : nzDies) {
                    if (StringUtils.isBlank(item)) {
                        continue;
                    }
                    List<String> list = Arrays.asList(StringUtils.split(item, ","));
                    for (String idDie : list) {
                        idReferrals.add(idDie);
                    }
                }
            } else if (dto.getChartType() == 2) {
                idReferrals = pfTestWaitingRoomDao.getReferralId(dto.getIdTestexecResult());
            }

            //idReferrals = idReferrals.stream().distinct().collect(Collectors.toList());
            if (CollectionUtils.isEmpty(idReferrals)) {
                return JSON.toJSONString(pfOrgChartVo);
            }
            List<Map<String, String>> basDies = pfTestWaitingRoomDao.selectManyNzDieMap(idReferrals);

            List<PfOrgChartVo> twoChartList = new ArrayList<>();
            PfOrgChartVo twoChart;
            PfOrgChartVo thirdChart;
            PfOrgChartVo fourChart;
            for (Map<String, String> map : basDies) {
                twoChart = new PfOrgChartVo();
                twoChart.setName(map.containsKey("idDieText") ? map.get("idDieText") : "");
                twoChart.setFgExclude(map.containsKey("fgExclude") ? map.get("fgExclude") : "0");
                twoChart.setType(2);
                // 四级目录
                fourChart = new PfOrgChartVo();
                fourChart.setName("鉴别诊断");
                fourChart.setType(4);
                List<PfOrgChartVo> fourChartList = new ArrayList<>();
                fourChartList.add(fourChart);
                // 三级目录
                thirdChart = new PfOrgChartVo();
                thirdChart.setName("诊断分析");
                thirdChart.setType(3);
                thirdChart.setChildren(fourChartList);
                List<PfOrgChartVo> thirdChartList = new ArrayList<>();
                thirdChartList.add(thirdChart);
                // 二级目录
                twoChart.setChildren(thirdChartList);
                twoChartList.add(twoChart);
            }
            pfOrgChartVo.setChildren(twoChartList);
        } else if (dto.getChartType() == 3) {
            // 一级目录固定
            pfOrgChartVo.setName("初步诊断");
            pfOrgChartVo.setType(1);
            // 二级目录
            List<ExmMedResultDiagnosis> list = pfTestWaitingRoomDao.listDiagnosis(dto.getIdTestexecResult());

            List<PfOrgChartVo> children = new ArrayList<>();
            PfOrgChartVo chartVo;
            for (ExmMedResultDiagnosis item : list) {
                chartVo = new PfOrgChartVo();
                chartVo.setId(String.valueOf(item.getIdDie()));
                chartVo.setName(item.getIdDieText());
                chartVo.setType(2);
                children.add(chartVo);
            }
            pfOrgChartVo.setChildren(children);
        }
        return JSON.toJSONString(pfOrgChartVo);
    }

    @Override
    public Long countDiagnosticChart(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.countDiagnosticChart(dto);
    }

    @Override
    public List<PfWaitingRoomChartDetailVo> listDiagnosticChart(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.listDiagnosticChart(dto);
    }

    @Override
    public PfWaitingRoomFinishVo selectFinishExamInfo(Long idTestplanDetail) {
        return pfTestWaitingRoomDao.selectFinishExamInfo(idTestplanDetail);
    }

    @Override
    public Long selectAssessPatIdMedCase(Long idTestplanDetail) {
        return pfTestWaitingRoomDao.selectAssessPatIdMedCase(idTestplanDetail);
    }

    @Override
    public String selectEvaGuideContent(Long idTestplanDetail) {
        return pfTestWaitingRoomDao.selectEvaGuideContent(idTestplanDetail);
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
