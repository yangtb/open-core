package com.sm.open.core.facade.pf.system.param;

import com.sm.open.core.facade.model.param.pf.system.param.Param;
import com.sm.open.core.facade.model.param.pf.system.param.PfParamListParam;
import com.sm.open.core.facade.model.param.pf.system.param.SysParaPram;
import com.sm.open.core.facade.model.result.pf.system.param.SysParamResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

/**
 * @ClassName: PfParamFacade
 * @Description: 参数facade接口
 * @Author yangtongbin
 * @Date 2017/10/10 10:16
 */
public interface PfParamFacade {

    /**
     * 获取参数列表
     *
     * @param param
     * @return
     */
    PfPageResult<SysParamResult> listParams(Param param);

    /**
     * 新增参数
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addParam(SysParaPram param);

    /**
     * 编辑参数
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editParam(SysParaPram param);

    /**
     * 停用、启用参数
     *
     * @param param 参数id集合
     * @return
     */
    CommonResult<Boolean> changeStatus(PfParamListParam param);

}
