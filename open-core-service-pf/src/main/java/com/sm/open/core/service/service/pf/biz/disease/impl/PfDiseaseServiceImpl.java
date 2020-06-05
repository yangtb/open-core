package com.sm.open.core.service.service.pf.biz.disease.impl;

import com.sm.open.care.core.utils.PinyinUtil;
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
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PfDiseaseServiceImpl implements PfDiseaseService {


    @Resource
    private PfDiseaseDao pfDiseaseDao;

    @Override
    public List<PfDiseaseZtreeVo> listDiseaseCatalogueTree(PfCatalogueTreeDto dto) {

        // 查询单个目录
        if (dto.getQueryChildCatalogue() == 1) {
            return pfDiseaseDao.listDiseaseTreeDetail(dto);
        }

        if (dto.getIncludeDie() == 0) {
            return pfDiseaseDao.listDiseaseCatalogueTree(dto);
        } else {
            List<PfDiseaseZtreeVo> list = pfDiseaseDao.listDieCatalogueTree(dto);
            List<PfDiseaseZtreeVo> listDie = pfDiseaseDao.listDiseaseTree();
            if (!CollectionUtils.isEmpty(list)) {
                list.addAll(listDie);
            }
            return list;
        }
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
    public List<String> getChildCatalogueByCatalogueId(String catalogueId) {
        List<String> list = new ArrayList<>();
        list.add(catalogueId);

        List<String> totalList = new ArrayList<>();
        totalList.add(catalogueId);
        return childCatalogueByCatalogue(totalList, list);
    }

    private List<String> childCatalogueByCatalogue(List<String> totalList, List<String> list) {
        List<String> tempList = pfDiseaseDao.getChildCatalogueByCatalogueId(list);
        if (CollectionUtils.isEmpty(tempList)) {
            return totalList;
        } else {
            totalList.addAll(tempList);
            childCatalogueByCatalogue(totalList, tempList);
        }
        return totalList;
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
    public Long getIdEvaCaseByIdMedicalrec(Long idMedicalrec) {
        return pfDiseaseDao.getIdEvaCaseByIdMedicalrec(idMedicalrec);
    }

    @Override
    public Long countIdeReason(PfDiseaseInfoDto dto) {
        return pfDiseaseDao.countIdeReason(dto);
    }

    @Override
    public List<BasDie> listIdeReason(PfDiseaseInfoDto dto) {
        return pfDiseaseDao.listIdeReason(dto);
    }

    @Override
    public Long countDiseaseByCatalogueId(PfDiseaseInfoDto dto) {
        return pfDiseaseDao.countDiseaseByCatalogueId(dto);
    }

    @Override
    public List<BasDie> listDiseaseByCatalogueId(PfDiseaseInfoDto dto) {
        return pfDiseaseDao.listDiseaseByCatalogueId(dto);
    }

    @Override
    public boolean addDiseaseInfo(BasDie dto) {
        if (StringUtils.isBlank(dto.getPinyin())) {
            dto.setPinyin(PinyinUtil.getFirstLettersUp(dto.getName()));
        }
        return pfDiseaseDao.addDiseaseInfo(dto) == 1 ? true : false;
    }

    @Override
    public boolean editDiseaseInfo(BasDie dto) {
        if (StringUtils.isBlank(dto.getPinyin())) {
            dto.setPinyin(PinyinUtil.getFirstLettersUp(dto.getName()));
        }
        return pfDiseaseDao.editDiseaseInfo(dto) == 1 ? true : false;
    }

    @Override
    public boolean delDiseaseInfo(PfBachChangeStatusDto dto) {
        return pfDiseaseDao.delDiseaseInfo(dto) >= 1 ? true : false;
    }
}
