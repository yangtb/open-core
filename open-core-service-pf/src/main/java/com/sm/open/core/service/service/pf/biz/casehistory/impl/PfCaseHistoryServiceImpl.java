package com.sm.open.core.service.service.pf.biz.casehistory.impl;

import com.sm.open.core.dal.pf.biz.casehistory.PfCaseHistoryDao;
import com.sm.open.core.model.dto.pf.biz.casehistory.PfCaseHistoryDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.FaqMedicalrec;
import com.sm.open.core.model.entity.FaqMedicalrecCa;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.casehistory.FaqMedicalrecVo;
import com.sm.open.core.service.service.pf.biz.casehistory.PfCaseHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfCaseHistoryServiceImpl implements PfCaseHistoryService {


    @Resource
    private PfCaseHistoryDao pfCaseHistoryDao;

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
}
