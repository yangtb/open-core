package com.sm.open.core.service.service.pf.biz.drug.impl;

import com.sm.open.core.dal.pf.biz.drug.PfDrugDao;
import com.sm.open.core.model.dto.pf.biz.drug.PfDrugInfoDto;
import com.sm.open.core.model.entity.BasDie;
import com.sm.open.core.model.entity.BasDrugs;
import com.sm.open.core.service.service.pf.biz.drug.PfDrugService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfDrugServiceImpl implements PfDrugService {

    @Resource
    private PfDrugDao pfDrugDao;

    @Override
    public Long countDrugInfo(PfDrugInfoDto dto) {
        return pfDrugDao.countDrugInfo(dto);
    }

    @Override
    public List<BasDrugs> listDrugInfo(PfDrugInfoDto dto) {
        return pfDrugDao.listDrugInfo(dto);
    }
}
