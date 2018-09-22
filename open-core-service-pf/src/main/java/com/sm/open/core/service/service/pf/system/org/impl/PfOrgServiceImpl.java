package com.sm.open.core.service.service.pf.system.org.impl;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.core.dal.pf.system.org.PfOrgDao;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.system.org.PfOrgDto;
import com.sm.open.core.model.entity.SysOrg;
import com.sm.open.core.service.facade.pf.system.org.PfOrgConstant;
import com.sm.open.core.service.service.pf.system.org.PfOrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfOrgServiceImpl implements PfOrgService {

    @Resource
    private PfOrgDao pfOrgDao;

    @Override
    public Long countOrgs(PfOrgDto dto) {
        return pfOrgDao.countOrgs(dto);
    }

    @Override
    public List<SysOrg> listOrgs(PfOrgDto dto) {
        return pfOrgDao.listOrgs(dto);
    }

    @Override
    public boolean addOrg(SysOrg dto) {
        if (pfOrgDao.countOrgByEmail(dto.getEmail()) >= 1) {
            throw new BizRuntimeException(PfOrgConstant.ORG_EMAIL_ERROR, PfOrgConstant.ORG_EMAIL_ERROR_MSG);
        }
        return pfOrgDao.addOrg(dto) == 1 ? true : false;
    }

    @Override
    public boolean isExistOrgEmail(String email) {
        return pfOrgDao.countOrgByEmail(email) >= 1 ? true : false;
    }

    @Override
    public boolean editOrg(SysOrg dto) {
        return pfOrgDao.editOrg(dto) == 1 ? true : false;
    }

    @Override
    public boolean delOrg(PfBachChangeStatusDto dto) {
        return pfOrgDao.delOrg(dto) >= 1 ? true : false;
    }

    @Override
    public boolean authOrg(PfBachChangeStatusDto dto) {
        return pfOrgDao.authOrg(dto) >= 1 ? true : false;
    }
}
