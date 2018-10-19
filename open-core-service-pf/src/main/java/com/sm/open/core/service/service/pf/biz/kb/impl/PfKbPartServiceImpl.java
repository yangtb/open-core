package com.sm.open.core.service.service.pf.biz.kb.impl;

import com.sm.open.core.dal.pf.biz.kb.PfKbPartDao;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfMedCaseDto;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfPartCommonDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.FaqMedCase;
import com.sm.open.core.model.entity.FaqMedCaseInquesList;
import com.sm.open.core.service.service.pf.biz.kb.PfKbPartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfKbPartServiceImpl implements PfKbPartService {

    @Resource
    private PfKbPartDao pfKbPartDao;

    @Override
    public Long countKbPart(PfMedCaseDto dto) {
        return pfKbPartDao.countKbPart(dto);
    }

    @Override
    public List<FaqMedCase> listKbPart(PfMedCaseDto dto) {
        return pfKbPartDao.listKbPart(dto);
    }

    @Override
    public Long addKbPart(FaqMedCase dto) {
        return pfKbPartDao.addKbPart(dto) == 1 ? dto.getIdMedCase() : null;
    }

    @Override
    public boolean editKbPart(FaqMedCase dto) {
        return pfKbPartDao.editKbPart(dto) == 1 ? true : false;
    }

    @Override
    public boolean delKbPart(PfBachChangeStatusDto dto) {
        return pfKbPartDao.delKbPart(dto) >= 1 ? true : false;
    }

    @Override
    public Long countFaqMedCaseInques(PfPartCommonDto dto) {
        return pfKbPartDao.countFaqMedCaseInques(dto);
    }

    @Override
    public List<FaqMedCaseInquesList> listFaqMedCaseInques(PfPartCommonDto dto) {
        return pfKbPartDao.listFaqMedCaseInques(dto);
    }
}
