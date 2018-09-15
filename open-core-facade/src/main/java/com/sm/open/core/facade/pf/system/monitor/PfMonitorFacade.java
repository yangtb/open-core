package com.sm.open.core.facade.pf.system.monitor;

import com.sm.open.core.facade.model.result.pf.system.monitor.PfServerResult;
import com.sm.open.core.facade.model.rpc.CommonResult;

/**
 * @ClassName: PfMonitorFacade
 * @Description: 监控接口
 * @Author yangtongbin
 * @Date 2018/9/5 11:31
 */
public interface PfMonitorFacade {

    /**
     * 查询服务器信息
     *
     * @return
     */
    CommonResult<PfServerResult> selectServerInfo();
}
