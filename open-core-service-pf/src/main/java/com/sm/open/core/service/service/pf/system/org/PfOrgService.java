package com.sm.open.core.service.service.pf.system.org;

import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.system.org.PfBachOrgDto;
import com.sm.open.core.model.dto.pf.system.org.PfOrgAuthDto;
import com.sm.open.core.model.dto.pf.system.org.PfOrgDto;
import com.sm.open.core.model.entity.SysOrg;
import com.sm.open.core.model.entity.SysOrgReg;
import com.sm.open.core.model.vo.pf.system.org.SysOrgAuthVo;

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
     * 机构认证总数
     *
     * @param dto
     * @return
     */
    Long countAuthOrg(PfOrgAuthDto dto);

    /**
     * 机构认证列表
     *
     * @param dto
     * @return
     */
    List<SysOrgAuthVo> listAuthOrg(PfOrgAuthDto dto);

    /**
     * 查询所有机构
     *
     * @return
     */
    List<SysOrg> listAllOrg();

    /**
     * 新增机构
     *
     * @param dto
     * @return
     */
    Long addOrg(SysOrg dto);

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
    boolean authOrg(PfBachOrgDto dto);

    /**
     * 机构认证驳回
     *
     * @param dto
     * @return
     */
    boolean rejectOrg(PfBachOrgDto dto);

    /**
     * 根据id查询机构信息
     *
     * @param idOrg 机构id
     * @return
     */
    SysOrg selectOrgInfoById(Long idOrg);

    /**
     * 申请激活
     *
     * @param dto
     * @return
     */
    boolean activeOrg(SysOrgReg dto);

    /**
     * 已存在申请激活记录
     *
     * @param idOrg 机构id
     * @return
     */
    boolean isExistApplyActiveRecord(Long idOrg);

}
