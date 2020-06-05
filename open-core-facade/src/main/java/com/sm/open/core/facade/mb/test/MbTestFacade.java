package com.sm.open.core.facade.mb.test;

import com.sm.open.core.facade.model.param.mb.test.MbTestParam;
import com.sm.open.core.facade.model.rpc.CommonResult;

public interface MbTestFacade {

    /**
     * 测试
     * @return
     */
    CommonResult<Integer> testOpen(MbTestParam mbTestParam);
}
