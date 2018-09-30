package com.sm.open.core.service.service.pf.biz.disease.impl;

import com.sm.open.core.dal.pf.biz.disease.PfDiseaseDao;
import com.sm.open.core.model.dto.pf.biz.disease.PfDiseaseInfoDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.dto.pf.common.PfChangeStatusDto;
import com.sm.open.core.model.entity.BasDie;
import com.sm.open.core.model.entity.BasDieClass;
import com.sm.open.core.model.vo.pf.biz.disease.PfDiseaseZtreeVo;
import com.sm.open.core.service.service.pf.biz.disease.PfDiseaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PfDiseaseServiceImpl implements PfDiseaseService {


    @Resource
    private PfDiseaseDao pfDiseaseDao;

    @Override
    public List<PfDiseaseZtreeVo> listDiseaseCatalogueTree(PfCatalogueTreeDto dto) {
        return pfDiseaseDao.listDiseaseCatalogueTree(dto);
    }

    @Override
    public BasDieClass selectDiseaseDetail(Long idDieClass) {
        return pfDiseaseDao.selectDiseaseDetail(idDieClass);
    }

    @Override
    public boolean isExistDiseaseCatalogue(String cd) {
        return pfDiseaseDao.isExistDiseaseCatalogue(cd) >= 1 ? true : false;
    }

    @Override
    public Long saveDiseaseCatalogue(BasDieClass dto) {
        if (dto.getIdDieclass() == null) {
            pfDiseaseDao.saveDiseaseCatalogue(dto);
        } else {
            pfDiseaseDao.editDiseaseCatalogue(dto);
        }
        return dto.getIdDieclass();
    }

    @Override
    public boolean delDiseaseCatalogue(PfChangeStatusDto dto) {
        String[] idStr = StringUtils.split(dto.getIdStr(), ",");
        List<Long> idDieclassList = new ArrayList<>(idStr.length);
        for (String str : idStr) {
            idDieclassList.add(Long.valueOf(str));
        }
        return pfDiseaseDao.delDiseaseCatalogue(idDieclassList, dto.getStatus(), dto.getOperator()) >= 1 ? true : false;
    }

    @Override
    public Long countDiseaseInfo(PfDiseaseInfoDto dto) {
        return pfDiseaseDao.countDiseaseInfo(dto);
    }

    @Override
    public List<BasDie> listDiseaseInfo(PfDiseaseInfoDto dto) {
        return pfDiseaseDao.listDiseaseInfo(dto);
    }

    @Override
    public boolean addDiseaseInfo(BasDie dto) {
        return pfDiseaseDao.addDiseaseInfo(dto) == 1 ? true : false;
    }

    @Override
    public boolean editDiseaseInfo(BasDie dto) {
        return pfDiseaseDao.editDiseaseInfo(dto) == 1 ? true : false;
    }

    @Override
    public boolean delDiseaseInfo(PfBachChangeStatusDto dto) {
        return pfDiseaseDao.delDiseaseInfo(dto) >= 1 ? true : false;
    }
}
