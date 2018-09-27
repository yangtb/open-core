package com.sm.open.core.dal.pf.system.org;

import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.system.org.PfOrgDto;
import com.sm.open.core.model.entity.SysOrg;
import com.sm.open.core.model.entity.SysOrgReg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfOrgDao {

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
    int addOrg(SysOrg dto);

    /**
     * 查询邮箱注册机构数
     *
     * @param email 邮箱
     * @return
     */
    int countOrgByEmail(@Param("email") String email);

    /**
     * 编辑机构
     *
     * @param dto
     * @return
     */
    int editOrg(SysOrg dto);

    /**
     * 删除机构
     *
     * @param dto
     * @return
     */
    int delOrg(PfBachChangeStatusDto dto);

    /**
     * 机构认证
     *
     * @param dto
     * @return
     */
    int authOrg(PfBachChangeStatusDto dto);

    /**
     * 根据id查询机构信息
     *
     * @param idOrg 机构id
     * @return
     */
    SysOrg selectOrgInfoById(@Param("idOrg") Long idOrg);


    /**
     * 申请激活
     *
     * @param dto
     * @return
     */
    boolean addActiveOrg(SysOrgReg dto);

    /**
     * 已存在申请激活记录
     *
     * @param idOrg 机构id
     * @return
     */
    int isExistApplyActiveRecord(Long idOrg);

}
