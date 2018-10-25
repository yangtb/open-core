package com.sm.open.core.service.service.pf.biz.kb.impl;

import com.sm.open.core.dal.pf.biz.kb.PfKbAssessDao;
import com.sm.open.core.model.dto.pf.biz.kb.assess.PfEvaCaseDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.FaqEvaCase;
import com.sm.open.core.model.entity.FaqMedCase;
import com.sm.open.core.service.service.pf.biz.kb.PfKbAssessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfKbAssessServiceImpl implements PfKbAssessService {

    @Resource
    private PfKbAssessDao pfKbAssessDao;

    @Override
    public Long countKbAssess(PfEvaCaseDto dto) {
        return pfKbAssessDao.countKbAssess(dto);
    }

    @Override
    public List<FaqEvaCase> listKbAssess(PfEvaCaseDto dto) {
        return pfKbAssessDao.listKbAssess(dto);
    }

    @Override
    public Long saveKbAssess(FaqEvaCase dto) {
        Integer num;
        if (dto.getIdEvaCase() == null) {
            num = pfKbAssessDao.addKbAssess(dto);
        } else {
            num = pfKbAssessDao.editKbAssess(dto);
        }
        return num == 1 ? dto.getIdEvaCase() : null;
    }

    @Override
    public boolean delKbAssess(PfBachChangeStatusDto dto) {
        return pfKbAssessDao.delKbAssess(dto) >= 1 ? true : false;
    }
}
