package com.sm.open.core.facade.pf.system.org;

import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.system.org.*;
import com.sm.open.core.facade.model.result.pf.system.org.SysOrgResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfOrgFacade
 * @Description: 机构服务
 * @Author yangtongbin
 * @Date 2018/9/20 16:12
 */
public interface PfOrgFacade {

    /**
     * 机构列表
     *
     * @param param
     * @return
     */
    PfPageResult listOrgs(PfOrgParam param);

    /**
     * 机构认证列表
     *
     * @param param
     * @return
     */
    PfPageResult listAuthOrg(PfOrgAuthParam param);

    /**
     * 获取所有机构
     *
     * @return
     */
    CommonResult<List<SysOrgResult>> listAllOrg();

    /**
     * 新增机构
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addOrg(SysOrgParam param);

    /**
     * 编辑机构
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editOrg(SysOrgParam param);

    /**
     * 删除机构
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delOrg(PfBachChangeStatusParam param);

    /**
     * 机构认证
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> authOrg(PfBachOrgParam param);

    /**
     * 机构认证驳回
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> rejectOrg(PfBachOrgParam param);

    /**
     * 根据id查询机构信息
     *
     * @param idOrg 机构id
     * @return
     */
    CommonResult<SysOrgResult> selectOrgInfoById(Long idOrg);

    /**
     * 申请激活
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> activeOrg(SysOrgRegParam param);

}
