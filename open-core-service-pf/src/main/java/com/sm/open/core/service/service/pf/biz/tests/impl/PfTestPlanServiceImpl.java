package com.sm.open.core.service.service.pf.biz.tests.impl;

import com.sm.open.core.dal.pf.biz.tests.PfTestPlanDao;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestPlanDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.ExmTestplan;
import com.sm.open.core.service.service.pf.biz.tests.PfTestPlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfTestPlanServiceImpl implements PfTestPlanService {

    @Resource
    private PfTestPlanDao pfTestPlanDao;

    @Override
    public Long countPlans(PfTestPlanDto dto) {
        return pfTestPlanDao.countPlans(dto);
    }

    @Override
    public List<ExmTestplan> listPlans(PfTestPlanDto dto) {
        return pfTestPlanDao.listPlans(dto);
    }

    @Override
    public Long savePlan(ExmTestplan dto) {
        Integer num;
        if (dto.getIdTestplan() == null) {
            if (StringUtils.isBlank(dto.getSdTestplan())) {
                dto.setSdTestplan("0");
            }
            num = pfTestPlanDao.addPlan(dto);
        } else {
            num = pfTestPlanDao.editPlan(dto);
        }
        return num == 1 ? dto.getIdTestplan() : null;
    }

    @Override
    public boolean delPlan(PfBachChangeStatusDto dto) {
        return pfTestPlanDao.delPlan(dto) >= 1 ? true : false;
    }
}
