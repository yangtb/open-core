package com.sm.open.core.service.service.pf.biz.clinic.impl;

import com.sm.open.core.dal.pf.biz.clinic.PfClinicTemplateDao;
import com.sm.open.core.model.dto.pf.biz.clinic.PfClinicTemplateDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasDemo;
import com.sm.open.core.model.entity.BasDemoCa;
import com.sm.open.core.model.entity.BasDemoTag;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.service.service.pf.biz.clinic.PfClinicTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfClinicTemplateServiceImpl implements PfClinicTemplateService {

    @Resource
    private PfClinicTemplateDao pfClinicTemplateDao;

    @Override
    public List<PfCommonZtreeVo> listClassifyTree() {
        return pfClinicTemplateDao.listClassifyTree();
    }

    @Override
    public Long addClassify(BasDemoCa dto) {
        return pfClinicTemplateDao.addClassify(dto) == 1 ? dto.getIdDemoCa() : null;
    }

    @Override
    public boolean editClassify(BasDemoCa dto) {
        return pfClinicTemplateDao.editClassify(dto) == 1 ? true : false;
    }

    @Override
    public boolean delClassify(PfBachChangeStatusDto dto) {
        return pfClinicTemplateDao.delClassify(dto) >= 1 ? true : false;
    }

    @Override
    public Long countTemplate(PfClinicTemplateDto dto) {
        return pfClinicTemplateDao.countTemplate(dto);
    }

    @Override
    public List<BasDemo> listTemplate(PfClinicTemplateDto dto) {
        return pfClinicTemplateDao.listTemplate(dto);
    }

    @Override
    public boolean addTemplate(BasDemo dto) {
        return pfClinicTemplateDao.addTemplate(dto) == 1 ? true : false;
    }

    @Override
    public boolean editTemplate(BasDemo dto) {
        return pfClinicTemplateDao.editTemplate(dto) == 1 ? true : false;
    }

    @Override
    public boolean delTemplate(PfBachChangeStatusDto dto) {
        return pfClinicTemplateDao.delTemplate(dto) >= 1 ? true : false;
    }

    @Override
    public List<BasDemoTag> listTag(PfClinicTemplateDto dto) {
        return pfClinicTemplateDao.listTag(dto);
    }

    @Override
    public boolean delTag(PfBachChangeStatusDto dto) {
        return pfClinicTemplateDao.delTag(dto) >= 1 ? true : false;
    }

    @Override
    public Long saveTag(BasDemoTag dto) {
        Integer num;
        if (dto.getIdTag() != null) {
            num = pfClinicTemplateDao.editTag(dto);
        } else {
            num = pfClinicTemplateDao.saveTag(dto);
        }
        return num == 1 ? dto.getIdTag() : null;
    }

    @Override
    public List<BasDemo> listAllBasDemo() {
        return pfClinicTemplateDao.listAllBasDemo();
    }
}
