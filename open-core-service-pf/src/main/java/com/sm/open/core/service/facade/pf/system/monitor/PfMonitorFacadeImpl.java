package com.sm.open.core.service.facade.pf.system.monitor;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.result.pf.system.monitor.PfServerResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.ResultFactory;
import com.sm.open.core.facade.pf.system.monitor.PfMonitorFacade;
import com.sm.open.core.service.service.pf.system.monitor.PfMonitorService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfMonitorFacade")
public class PfMonitorFacadeImpl implements PfMonitorFacade {

    @Resource
    private PfMonitorService pfMonitorService;

    @Override
    public CommonResult<PfServerResult> selectServerInfo() {

        return ResultFactory.initCommonResultWithSuccess(
                BeanUtil.convert(pfMonitorService.selectServerInfo(), PfServerResult.class));
    }
}
