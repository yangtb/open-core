package com.sm.open.core.service.service.pf.biz.kb.impl;

import com.sm.open.core.dal.pf.biz.kb.PfCaseHistoryDao;
import com.sm.open.core.dal.pf.biz.kb.PfKbAssessDao;
import com.sm.open.core.dal.pf.biz.kb.PfKbPartDao;
import com.sm.open.core.model.dto.pf.biz.kb.casehistory.PfCaseHistoryDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.casehistory.FaqMedicalrecVo;
import com.sm.open.core.model.vo.pf.biz.clinic.PfAssessTagVo;
import com.sm.open.core.model.vo.pf.biz.clinic.PfCaseHistoryTagVo;
import com.sm.open.core.service.service.pf.biz.kb.PfCaseHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfCaseHistoryServiceImpl implements PfCaseHistoryService {


    @Resource
    private PfCaseHistoryDao pfCaseHistoryDao;

    @Resource
    private PfKbPartDao pfKbPartDao;

    @Resource
    private PfKbAssessDao pfKbAssessDao;

    @Override
    public List<PfCommonZtreeVo> listClassifyTree() {
        return pfCaseHistoryDao.listClassifyTree();
    }

    @Override
    public Long addClassify(FaqMedicalrecCa dto) {
        return pfCaseHistoryDao.addClassify(dto) == 1 ? dto.getIdMedicalrecCa() : null;
    }

    @Override
    public boolean editClassify(FaqMedicalrecCa dto) {
        return pfCaseHistoryDao.editClassify(dto) == 1 ? true : false;
    }

    @Override
    public boolean delClassify(PfBachChangeStatusDto dto) {
        return pfCaseHistoryDao.delClassify(dto) >= 1 ? true : false;
    }

    @Override
    public Long countTemplate(PfCaseHistoryDto dto) {
        return pfCaseHistoryDao.countTemplate(dto);
    }

    @Override
    public List<FaqMedicalrecVo> listTemplate(PfCaseHistoryDto dto) {
        return pfCaseHistoryDao.listTemplate(dto);
    }

    @Override
    public boolean addTemplate(FaqMedicalrec dto) {
        return pfCaseHistoryDao.addTemplate(dto) == 1 ? true : false;
    }

    @Override
    public boolean editTemplate(FaqMedicalrec dto) {
        return pfCaseHistoryDao.editTemplate(dto) == 1 ? true : false;
    }

    @Override
    public boolean delTemplate(PfBachChangeStatusDto dto) {
        return pfCaseHistoryDao.delTemplate(dto) >= 1 ? true : false;
    }

    @Override
    public Long saveMedTag(FaqMedTag dto) {
        return pfCaseHistoryDao.saveMedTag(dto) == 1 ? dto.getIdMedTag() : null;
    }

    @Override
    public Long saveEvaTag(FaqEvaTag dto) {
        return pfCaseHistoryDao.saveEvaTag(dto) == 1 ? dto.getIdEvaTag() : null;
    }

    @Override
    public List<PfCaseHistoryTagVo> listAllCaseHistoryTag(Long idDemo, Long idMedicalrec) {
        return pfCaseHistoryDao.listAllCaseHistoryTag(idDemo, idMedicalrec);
    }

    @Override
    public List<PfAssessTagVo> listAllAssessTag(Long idDemo, Long idMedicalrec) {
        return pfCaseHistoryDao.listAllAssessTag(idDemo, idMedicalrec);
    }

    @Override
    public FaqMedTag selectMedTag(FaqMedTag dto) {
        return pfCaseHistoryDao.selectMedTag(dto);
    }

    @Override
    public FaqEvaTag selectEvaTag(FaqEvaTag dto) {
        return pfCaseHistoryDao.selectEvaTag(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveAsMed(FaqMedTag dto) {
        // 病例维护 1-生成主表记录
        FaqMedCase faqMedCase = new FaqMedCase();
        faqMedCase.setIdMedCase(dto.getOldIdMedCase());
        faqMedCase.setCreator(dto.getCreator());
        faqMedCase.setName(dto.getCaseName());
        faqMedCase.setIdOrg(dto.getIdOrg());
        pfKbPartDao.copyKbPart(faqMedCase);
        // 2-复制子表
        if (dto.getCdMedAsse().equals("004")) {
            pfKbPartDao.copyKbCons(dto.getOldIdMedCase(), faqMedCase.getIdMedCase());
        } else if (dto.getCdMedAsse().equals("005")) {
            pfKbPartDao.copyKbCheck(dto.getOldIdMedCase(), faqMedCase.getIdMedCase());
            pfKbPartDao.copyCheckPic(dto.getOldIdMedCase(), faqMedCase.getIdMedCase());
        } else if (dto.getCdMedAsse().equals("006")) {
            pfKbPartDao.copyKbExam(dto.getOldIdMedCase(), faqMedCase.getIdMedCase());
        }
        // 3 保存病例标签
        dto.setIdMedCase(faqMedCase.getIdMedCase());
        pfCaseHistoryDao.saveMedTag(dto);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveAsEva(FaqEvaTag dto) {
        // 病例维护 1-生成主表记录
        FaqEvaCase faqEvaCase = new FaqEvaCase();
        faqEvaCase.setIdEvaCase(dto.getOldIdEvaCase());
        faqEvaCase.setCreator(dto.getCreator());
        faqEvaCase.setName(dto.getCaseName());
        faqEvaCase.setIdOrg(dto.getIdOrg());
        pfKbAssessDao.copyKbAssess(faqEvaCase);

        // 2-复制子表
        List<FaqEvaCaseItem> items = pfKbAssessDao.selectFaqEvaCaseItem(dto.getOldIdEvaCase());
        for (FaqEvaCaseItem faqEvaCaseItem : items) {
            Long oldIdEvaCaseItem = faqEvaCaseItem.getIdEvaCaseItem();
            faqEvaCaseItem.setIdEvaCase(faqEvaCase.getIdEvaCase());
            pfKbAssessDao.addItem(faqEvaCaseItem);

            Long newIdEvaCaseItem = faqEvaCaseItem.getIdEvaCaseItem();

            if (dto.getCdEvaAsse().equals("001")) {
                pfKbAssessDao.copyKbAssess001(oldIdEvaCaseItem, newIdEvaCaseItem);
            } else if (dto.getCdEvaAsse().equals("002")) {
                pfKbAssessDao.copyKbAssess002(oldIdEvaCaseItem, newIdEvaCaseItem);
            } else if (dto.getCdEvaAsse().equals("003")) {
                pfKbAssessDao.copyKbAssess003(oldIdEvaCaseItem, newIdEvaCaseItem);
            } else if (dto.getCdEvaAsse().equals("004")) {
                pfKbAssessDao.copyKbAssess004(oldIdEvaCaseItem, newIdEvaCaseItem);
            } else if (dto.getCdEvaAsse().equals("005")) {
                pfKbAssessDao.copyKbAssess005(oldIdEvaCaseItem, newIdEvaCaseItem);
            } else if (dto.getCdEvaAsse().equals("006")) {
                pfKbAssessDao.copyKbAssess006(oldIdEvaCaseItem, newIdEvaCaseItem);
            } else if (dto.getCdEvaAsse().equals("007")) {
                pfKbAssessDao.copyKbAssess007(oldIdEvaCaseItem, newIdEvaCaseItem);
            }
        }
        // 3 保存病例标签
        dto.setIdEvaCase(faqEvaCase.getIdEvaCase());
        pfCaseHistoryDao.saveEvaTag(dto);
        return true;
    }
}
