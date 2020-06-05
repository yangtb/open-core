package com.sm.open.core.service.service.pf.system.monitor;

import com.sm.open.core.model.vo.pf.system.monitor.PfServerVo;

/**
 * @ClassName: PfMonitorFacade
 * @Description: 监控接口
 * @Author yangtongbin
 * @Date 2018/9/5 11:31
 */
public interface PfMonitorService {

    /**
     * 查询服务器信息
     *
     * @return
     */
    PfServerVo selectServerInfo();
}
