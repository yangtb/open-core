package com.sm.open.core.service.service.pf.biz.disease.impl;

import com.sm.open.core.dal.pf.biz.disease.PfDiseaseDao;
import com.sm.open.core.model.dto.pf.biz.disease.PfDiseaseInfoDto;
import com.sm.open.core.model.entity.BasDie;
import com.sm.open.core.service.service.pf.biz.disease.PfDiseaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfDiseaseServiceImpl implements PfDiseaseService {


    @Resource
    private PfDiseaseDao pfDiseaseDao;

    @Override
    public Long countDiseaseInfo(PfDiseaseInfoDto dto) {
        return pfDiseaseDao.countDiseaseInfo(dto);
    }

    @Override
    public List<BasDie> listDiseaseInfo(PfDiseaseInfoDto dto) {
        return pfDiseaseDao.listDiseaseInfo(dto);
    }
}
