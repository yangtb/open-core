package com.sm.open.core.service.service.pf.biz.tests.impl;

import com.sm.open.core.dal.pf.biz.tests.PfTestPlanDao;
import com.sm.open.core.dal.pf.system.grade.PfGradeDao;
import com.sm.open.core.model.dto.pf.biz.tests.PfAddCaseDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestPlanDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.entity.ExmTestplan;
import com.sm.open.core.model.entity.ExmTestplanMedicalrec;
import com.sm.open.core.model.entity.ExmTestplanStudent;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.service.service.pf.biz.tests.PfTestPlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfTestPlanServiceImpl implements PfTestPlanService {

    @Resource
    private PfTestPlanDao pfTestPlanDao;

    @Resource
    private PfGradeDao pfGradeDao;

    @Override
    public Long countPlans(PfTestPlanDto dto) {
        return pfTestPlanDao.countPlans(dto);
    }

    @Override
    public List<ExmTestplan> listPlans(PfTestPlanDto dto) {
        return pfTestPlanDao.listPlans(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long savePlan(ExmTestplan dto) {
        Integer num;
        if (dto.getIdTestplan() == null) {
            if (StringUtils.isBlank(dto.getSdTestplan())) {
                dto.setSdTestplan("0");
            }
            num = pfTestPlanDao.addPlan(dto);
            if (!dto.getIdTestpaper().equals(-1)) {
                pfTestPlanDao.defaultPlamItem(dto.getIdTestplan(), dto.getIdTestpaper());
            }
        } else {
            num = pfTestPlanDao.editPlan(dto);
        }
        return num == 1 ? dto.getIdTestplan() : null;
    }

    @Override
    public boolean delPlan(PfBachChangeStatusDto dto) {
        return pfTestPlanDao.delPlan(dto) >= 1 ? true : false;
    }

    @Override
    public List<PfCommonZtreeVo> listCaseTree(PfCatalogueTreeDto dto) {
        return pfTestPlanDao.listCaseTree(dto);
    }

    @Override
    public List<ExmTestplanMedicalrec> listPlanItem(PfTestPlanDto dto) {
        return pfTestPlanDao.listPlanItem(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addPlanItem(PfAddCaseDto dto) {
        List<Long> list = dto.getList();
        List<ExmTestplanMedicalrec> oldPlans = pfTestPlanDao.listAllPlanItem(dto.getIdTestplan());
        pfTestPlanDao.delAllPlanItem(dto.getIdTestplan());
        for (Long id : list) {
            ExmTestplanMedicalrec testplanMedicalrec = new ExmTestplanMedicalrec();
            testplanMedicalrec.setIdTestplan(dto.getIdTestplan());
            testplanMedicalrec.setIdMedicalrec(id);
            for (ExmTestplanMedicalrec oldPlan : oldPlans) {
                if (oldPlan.getIdMedicalrec().equals(id)) {
                    testplanMedicalrec.setSort(oldPlan.getSort());
                }
            }
            pfTestPlanDao.addPlanItem(testplanMedicalrec);
        }
        return true;
    }

    @Override
    public boolean delPlanItem(PfBachChangeStatusDto dto) {
        return pfTestPlanDao.delPlanItem(dto) >= 1 ? true : false;
    }

    @Override
    public boolean updatePlanItemSort(ExmTestplanMedicalrec dto) {
        return pfTestPlanDao.updatePlanItemSort(dto) >= 1 ? true : false;
    }

    @Override
    public List<PfCommonZtreeVo> listStudentTree(PfCatalogueTreeDto dto) {
        List<PfCommonZtreeVo> classTree = pfGradeDao.listGradeTree(dto.getIdOrg());
        classTree.addAll(pfTestPlanDao.listStudentTree(dto));
        return classTree;
    }

    @Override
    public List<ExmTestplanStudent> listPlanStudent(PfTestPlanDto dto) {
        return pfTestPlanDao.listPlanStudent(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addPlanStudent(PfAddCaseDto dto) {
        pfTestPlanDao.delAllPlanStudent(dto.getIdTestplan());
        return pfTestPlanDao.addPlanStudent(dto.getList(), dto.getIdTestplan()) >= 1 ? true : false;
    }

    @Override
    public boolean delPlanStudent(PfBachChangeStatusDto dto) {
        return pfTestPlanDao.delPlanStudent(dto) >= 1 ? true : false;
    }
}
