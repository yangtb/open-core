package com.sm.open.core.service.service.pf.system.org;

import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.system.org.PfOrgDto;
import com.sm.open.core.model.entity.SysOrg;

import java.util.List;

public interface PfOrgService {

    /**
     * 机构总数
     *
     * @param dto
     * @return
     */
    Long countOrgs(PfOrgDto dto);

    /**
     * 机构列表
     *
     * @param dto
     * @return
     */
    List<SysOrg> listOrgs(PfOrgDto dto);

    /**
     * 新增机构
     *
     * @param dto
     * @return
     */
    boolean addOrg(SysOrg dto);

    /**
     * 一个邮箱只能注册一个机构
     *
     * @param email 邮箱
     * @return
     */
    boolean isExistOrgEmail(String email);

    /**
     * 编辑机构
     *
     * @param dto
     * @return
     */
    boolean editOrg(SysOrg dto);

    /**
     * 删除机构
     *
     * @param dto
     * @return
     */
    boolean delOrg(PfBachChangeStatusDto dto);

    /**
     * 机构认证
     *
     * @param dto
     * @return
     */
    boolean authOrg(PfBachChangeStatusDto dto);
}
