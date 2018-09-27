package com.sm.open.core.service.service.pf.system.org.impl;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.core.dal.pf.system.org.PfOrgDao;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.system.org.PfBachOrgDto;
import com.sm.open.core.model.dto.pf.system.org.PfOrgAuthDto;
import com.sm.open.core.model.dto.pf.system.org.PfOrgDto;
import com.sm.open.core.model.entity.SysOrg;
import com.sm.open.core.model.entity.SysOrgReg;
import com.sm.open.core.model.enums.SdRegEnum;
import com.sm.open.core.model.vo.pf.system.org.SysOrgAuthVo;
import com.sm.open.core.service.facade.pf.system.org.PfOrgConstant;
import com.sm.open.core.service.service.pf.system.org.PfOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Long countAuthOrg(PfOrgAuthDto dto) {
        return pfOrgDao.countAuthOrg(dto);
    }

    @Override
    public List<SysOrgAuthVo> listAuthOrg(PfOrgAuthDto dto) {
        return pfOrgDao.listAuthOrg(dto);
    }

    @Override
    public List<SysOrg> listAllOrg() {
        return pfOrgDao.listAllOrg();
    }

    @Override
    public Long addOrg(SysOrg dto) {
        if (pfOrgDao.countOrgByEmail(dto.getEmail()) >= 1) {
            throw new BizRuntimeException(PfOrgConstant.ORG_EMAIL_ERROR, PfOrgConstant.ORG_EMAIL_ERROR_MSG);
        }
        return pfOrgDao.addOrg(dto) == 1 ? dto.getIdOrg() : null;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean authOrg(PfBachOrgDto dto) {
        // 修改机构认证表状态
        pfOrgDao.updateAuthRecord(dto.getIdRegList(), dto.getConfirmor(), dto.getOperator(), SdRegEnum.PASS.getCode());
        // 修改机构表状态
        pfOrgDao.authOrg(dto.getIdOrgList(), dto.getOperator(), YesOrNoNum.YES.getCode());
        return true;
    }

    @Override
    public boolean rejectOrg(PfBachOrgDto dto) {
        int num = pfOrgDao.updateAuthRecord(dto.getIdRegList(), dto.getConfirmor(),
                dto.getOperator(), SdRegEnum.REJECT.getCode());
        return num >= 1 ? true : false;
    }

    @Override
    public SysOrg selectOrgInfoById(Long idOrg) {
        return pfOrgDao.selectOrgInfoById(idOrg);
    }

    @Override
    public boolean activeOrg(SysOrgReg dto) {
        return pfOrgDao.addActiveOrg(dto);
    }

    @Override
    public boolean isExistApplyActiveRecord(Long idOrg) {
        return pfOrgDao.isExistApplyActiveRecord(idOrg) >= 1 ? true : false;
    }

}
