package com.sm.open.core.service.service.pf.biz.clinic.impl;

import com.sm.open.core.dal.pf.biz.clinic.PfClinicTemplateDao;
import com.sm.open.core.model.dto.pf.biz.check.PfCheckQuestionDto;
import com.sm.open.core.model.dto.pf.biz.clinic.PfClinicTemplateDto;
import com.sm.open.core.model.dto.pf.biz.exam.PfExamQuestionDto;
import com.sm.open.core.model.dto.pf.biz.inquisition.PfInquisitionQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.clinic.BasEvaTagVo;
import com.sm.open.core.model.vo.pf.biz.clinic.BasMedicalTagVo;
import com.sm.open.core.model.vo.pf.biz.clinic.PfAssessTagVo;
import com.sm.open.core.model.vo.pf.biz.clinic.PfCaseHistoryTagVo;
import com.sm.open.core.service.service.pf.biz.clinic.PfClinicTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfClinicTemplateServiceImpl implements PfClinicTemplateService {

    @Resource
    private PfClinicTemplateDao pfClinicTemplateDao;

    @Override
    public List<PfCommonZtreeVo> listClassifyTree() {
        return pfClinicTemplateDao.listClassifyTree();
    }

    @Override
    public Long addClassify(BasDemoCa dto) {
        return pfClinicTemplateDao.addClassify(dto) == 1 ? dto.getIdDemoCa() : null;
    }

    @Override
    public boolean editClassify(BasDemoCa dto) {
        return pfClinicTemplateDao.editClassify(dto) == 1 ? true : false;
    }

    @Override
    public boolean delClassify(PfBachChangeStatusDto dto) {
        return pfClinicTemplateDao.delClassify(dto) >= 1 ? true : false;
    }

    @Override
    public Long countTemplate(PfClinicTemplateDto dto) {
        return pfClinicTemplateDao.countTemplate(dto);
    }

    @Override
    public List<BasDemo> listTemplate(PfClinicTemplateDto dto) {
        return pfClinicTemplateDao.listTemplate(dto);
    }

    @Override
    public boolean addTemplate(BasDemo dto) {
        return pfClinicTemplateDao.addTemplate(dto) == 1 ? true : false;
    }

    @Override
    public boolean editTemplate(BasDemo dto) {
        return pfClinicTemplateDao.editTemplate(dto) == 1 ? true : false;
    }

    @Override
    public boolean delTemplate(PfBachChangeStatusDto dto) {
        return pfClinicTemplateDao.delTemplate(dto) >= 1 ? true : false;
    }

    @Override
    public List<BasMedicalTagVo> listCaseHistoryTag(PfClinicTemplateDto dto) {
        return pfClinicTemplateDao.listCaseHistoryTag(dto);
    }

    @Override
    public boolean delCaseHistoryTag(PfBachChangeStatusDto dto) {
        return pfClinicTemplateDao.delCaseHistoryTag(dto) >= 1 ? true : false;
    }

    @Override
    public Long saveCaseHistoryTag(BasMedicalTag dto) {
        Long num;
        if (dto.getIdTag() != null) {
            num = pfClinicTemplateDao.editCaseHistoryTag(dto);
        } else {
            num = pfClinicTemplateDao.saveCaseHistoryTag(dto);
        }
        return num == 1 ? dto.getIdTag() : null;
    }

    @Override
    public List<BasEvaTagVo> listSheetTag(PfClinicTemplateDto dto) {
        return pfClinicTemplateDao.listSheetTag(dto);
    }

    @Override
    public boolean delSheetTag(PfBachChangeStatusDto dto) {
        return pfClinicTemplateDao.delSheetTag(dto) >= 1 ? true : false;
    }

    @Override
    public Long saveSheetTag(BasEvaTag dto) {
        Integer num;
        if (dto.getIdTag() != null) {
            num = pfClinicTemplateDao.editSheetTag(dto);
        } else {
            num = pfClinicTemplateDao.saveSheetTag(dto);
        }
        return num == 1 ? dto.getIdTag() : null;
    }

    @Override
    public List<BasDemo> listAllBasDemo() {
        return pfClinicTemplateDao.listAllBasDemo();
    }

    @Override
    public List<PfCommonZtreeVo> listDimensionTree(Long idDemo) {
        return pfClinicTemplateDao.listDimensionTree(idDemo);
    }

    @Override
    public boolean delDimensionTag(PfBachChangeStatusDto dto) {
        return pfClinicTemplateDao.delDimensionTag(dto) >= 1 ? true : false;
    }

    @Override
    public Long saveDimensionTag(BasDemoAsses dto) {
        Long num;
        if (dto.getIdDimemsion() != null) {
            num = pfClinicTemplateDao.editDimensionTag(dto);
        } else {
            num = pfClinicTemplateDao.saveDimensionTag(dto);
        }
        return num == 1 ? dto.getIdDimemsion() : null;
    }

    @Override
    public BasDemoAsses selectDimensionTagInfo(Long idDimemsion) {
        return pfClinicTemplateDao.selectDimensionTagInfo(idDimemsion);
    }

    @Override
    public List<PfCaseHistoryTagVo> listAllCaseHistoryTag(Long idDemo) {
        return pfClinicTemplateDao.listAllCaseHistoryTag(idDemo);
    }

    @Override
    public List<PfAssessTagVo> listAllAssessTag(Long idDemo) {
        return pfClinicTemplateDao.listAllAssessTag(idDemo);
    }

    @Override
    public boolean saveSerialNo(BasMedicalTag dto) {
        return pfClinicTemplateDao.saveSerialNo(dto) >= 1 ? true : false;
    }

    @Override
    public Long selectCaseIdMedCase(Long idMedicalrec, String cdMedAsse) {
        return pfClinicTemplateDao.selectCaseIdMedCase(idMedicalrec, cdMedAsse);
    }

    @Override
    public Long countInquisitionQuestion(PfInquisitionQuestionDto dto) {
        return pfClinicTemplateDao.countInquisitionQuestion(dto);
    }

    @Override
    public List<BasInques> listInquisitionQuestion(PfInquisitionQuestionDto dto) {
        return pfClinicTemplateDao.listInquisitionQuestion(dto);
    }

    @Override
    public Long countCheckQuestion(PfCheckQuestionDto dto) {
        return pfClinicTemplateDao.countCheckQuestion(dto);
    }

    @Override
    public List<BasBody> listCheckQuestion(PfCheckQuestionDto dto) {
        return pfClinicTemplateDao.listCheckQuestion(dto);
    }

    @Override
    public Long countExamQuestion(PfExamQuestionDto dto) {
        return pfClinicTemplateDao.countExamQuestion(dto);
    }

    @Override
    public List<BasInspectItem> listExamQuestion(PfExamQuestionDto dto) {
        return pfClinicTemplateDao.listExamQuestion(dto);
    }


}
