package com.sm.open.core.service.service.pf.biz.clinic.impl;

import com.sm.open.core.dal.pf.biz.clinic.PfClinicPartsDao;
import com.sm.open.core.model.dto.pf.biz.clinic.parts.PfClinicPartsDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasAlgorithm;
import com.sm.open.core.model.entity.BasEvaAsse;
import com.sm.open.core.model.entity.BasMedAsse;
import com.sm.open.core.service.service.pf.biz.clinic.PfClinicPartsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("pfClinicPartsService")
public class PfClinicPartsServiceImpl implements PfClinicPartsService {

    @Resource
    private PfClinicPartsDao pfClinicPartsDao;

    @Override
    public Long countParts(PfClinicPartsDto dto) {
        return pfClinicPartsDao.countParts(dto);
    }

    @Override
    public List<BasMedAsse> listParts(PfClinicPartsDto dto) {
        return pfClinicPartsDao.listParts(dto);
    }

    @Override
    public boolean addPart(BasMedAsse dto) {
        return pfClinicPartsDao.addPart(dto) == 1 ? true : false;
    }

    @Override
    public boolean editPart(BasMedAsse dto) {
        return pfClinicPartsDao.editPart(dto) == 1 ? true : false;
    }

    @Override
    public boolean delPart(PfBachChangeStatusDto dto) {
        return pfClinicPartsDao.delPart(dto) >= 1 ? true : false;
    }

    @Override
    public Long countSheet(PfClinicPartsDto dto) {
        return pfClinicPartsDao.countSheet(dto);
    }

    @Override
    public List<BasEvaAsse> listSheet(PfClinicPartsDto dto) {
        return pfClinicPartsDao.listSheet(dto);
    }

    @Override
    public boolean addSheet(BasEvaAsse dto) {
        return pfClinicPartsDao.addSheet(dto) == 1 ? true : false;
    }

    @Override
    public boolean editSheet(BasEvaAsse dto) {
        return pfClinicPartsDao.editSheet(dto) == 1 ? true : false;
    }

    @Override
    public boolean delSheet(PfBachChangeStatusDto dto) {
        return pfClinicPartsDao.delSheet(dto) >= 1 ? true : false;
    }

    @Override
    public Long countAlgorithm(PfClinicPartsDto dto) {
        return pfClinicPartsDao.countAlgorithm(dto);
    }

    @Override
    public List<BasAlgorithm> listAlgorithm(PfClinicPartsDto dto) {
        return pfClinicPartsDao.listAlgorithm(dto);
    }

    @Override
    public boolean addAlgorithm(BasAlgorithm dto) {
        return pfClinicPartsDao.addAlgorithm(dto) == 1 ? true : false;
    }

    @Override
    public boolean editAlgorithm(BasAlgorithm dto) {
        return pfClinicPartsDao.editAlgorithm(dto) == 1 ? true : false;
    }

    @Override
    public boolean delAlgorithm(PfBachChangeStatusDto dto) {
        return pfClinicPartsDao.delAlgorithm(dto) >= 1 ? true : false;
    }

    @Override
    public List<BasMedAsse> listAllPart() {
        return pfClinicPartsDao.listAllPart();
    }

    @Override
    public List<BasEvaAsse> listAllSheet() {
        return pfClinicPartsDao.listAllSheet();
    }

    @Override
    public List<BasAlgorithm> listAllAlgorithm() {
        return pfClinicPartsDao.listAllAlgorithm();
    }
}
