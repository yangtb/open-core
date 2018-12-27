package com.sm.open.core.facade.pf.home;

import com.sm.open.core.facade.model.param.pf.home.PfHomeParam;
import com.sm.open.core.facade.model.result.pf.home.PfHomeResult;
import com.sm.open.core.facade.model.rpc.CommonResult;

/**
 * @author yangtongbin
 * @className: PfHomeFacade
 * @description: 首页facade接口
 * @date 2018/9/26
 */
public interface PfHomeFacade {

    /**
     * 获取首页信息
     *
     * @param param
     * @return
     */
    CommonResult<PfHomeResult> selectHomeInfo(PfHomeParam param);
}
