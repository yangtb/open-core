package com.sm.open.core.service.service.pf.biz.drug.impl;

import com.sm.open.care.core.utils.PinyinUtil;
import com.sm.open.core.dal.pf.biz.drug.PfDrugDao;
import com.sm.open.core.model.dto.pf.biz.drug.PfDrugInfoDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.dto.pf.common.PfChangeStatusDto;
import com.sm.open.core.model.entity.BasDrugs;
import com.sm.open.core.model.entity.BasDrugsClass;
import com.sm.open.core.model.vo.pf.biz.drug.PfDrugZtreeVo;
import com.sm.open.core.service.service.pf.biz.drug.PfDrugService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PfDrugServiceImpl implements PfDrugService {

    @Resource
    private PfDrugDao pfDrugDao;

    @Override
    public List<PfDrugZtreeVo> listDrugCatalogueTree(PfCatalogueTreeDto dto) {
        return pfDrugDao.listDrugCatalogueTree(dto);
    }

    @Override
    public BasDrugsClass selectDrugDetail(Long idDrugsclass) {
        return pfDrugDao.selectDrugDetail(idDrugsclass);
    }

    @Override
    public boolean isExistDrugCatalogue(String cd) {
        return pfDrugDao.isExistDrugCatalogue(cd) >= 1 ? true : false;
    }

    @Override
    public Long saveDrugCatalogue(BasDrugsClass dto) {
        if (dto.getIdDrugsclass() == null) {
            pfDrugDao.saveDrugCatalogue(dto);
        } else {
            pfDrugDao.editDrugCatalogue(dto);
        }
        return dto.getIdDrugsclass();
    }

    @Override
    public boolean delDrugCatalogue(PfChangeStatusDto dto) {
        String[] idStr = StringUtils.split(dto.getIdStr(), ",");
        List<Long> idList = new ArrayList<>(idStr.length);
        for (String str : idStr) {
            idList.add(Long.valueOf(str));
        }
        return pfDrugDao.delDrugCatalogue(idList, dto.getStatus(), dto.getOperator()) >= 1 ? true : false;
    }

    @Override
    public Long countDrugInfo(PfDrugInfoDto dto) {
        return pfDrugDao.countDrugInfo(dto);
    }

    @Override
    public List<BasDrugs> listDrugInfo(PfDrugInfoDto dto) {
        return pfDrugDao.listDrugInfo(dto);
    }

    @Override
    public boolean addDrugInfo(BasDrugs dto) {
        if (StringUtils.isBlank(dto.getPinyin())) {
            dto.setPinyin(PinyinUtil.getFirstLettersUp(dto.getName()));
        }
        return pfDrugDao.addDrugInfo(dto) == 1 ? true : false;
    }

    @Override
    public boolean editDrugInfo(BasDrugs dto) {
        if (StringUtils.isBlank(dto.getPinyin())) {
            dto.setPinyin(PinyinUtil.getFirstLettersUp(dto.getName()));
        }
        return pfDrugDao.editDrugInfo(dto) == 1 ? true : false;
    }

    @Override
    public boolean delDrugInfo(PfBachChangeStatusDto dto) {
        return pfDrugDao.delDrugInfo(dto) >= 1 ? true : false;
    }

    @Override
    public boolean dealPinyin() {
        List<BasDrugs> basDrugs = pfDrugDao.listAllDrugs();
        for (BasDrugs basDrug : basDrugs) {
            pfDrugDao.updatePinyin(basDrug.getIdDrugs(), PinyinUtil.getFirstLettersUp(basDrug.getName()));
        }
        return true;
    }
}
