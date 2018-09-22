package com.sm.open.core.facade.pf.system.org;

import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.system.org.PfOrgParam;
import com.sm.open.core.facade.model.param.pf.system.org.SysOrgParam;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

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
    CommonResult<Boolean> authOrg(PfBachChangeStatusParam param);
}
