package com.sm.open.core.service.test;

import com.sm.open.core.facade.mb.test.MbTestFacade;
import com.sm.open.core.facade.model.param.mb.test.MbTestParam;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.service.common.ApplicationContextTest;
import org.junit.Test;

import javax.annotation.Resource;

public class TestFacade extends ApplicationContextTest {

    @Resource(name = "mbTestFacade")
    private MbTestFacade mbTestFacade;

    @Test
    public void doTestFacade() {
        MbTestParam mbTestParam = new MbTestParam();
        CommonResult<Integer> result = mbTestFacade.testOpen(mbTestParam);
        System.out.println(result.getContent());
    }

}
