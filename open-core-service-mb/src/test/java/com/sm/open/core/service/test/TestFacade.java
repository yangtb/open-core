package com.sm.open.core.service.test;

import com.sm.open.care.core.log.LoggerUtil;
import com.sm.open.core.facade.mb.test.MbTestFacade;
import com.sm.open.core.facade.model.param.mb.test.MbTestParam;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.service.common.ApplicationContextTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class TestFacade extends ApplicationContextTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestFacade.class);

    @Resource(name = "mbTestFacade")
    private MbTestFacade mbTestFacade;

    @Test
    public void doTestFacade() {
        LoggerUtil.info(LOGGER,"1-【TestFacade-testOpen-params】");
        MbTestParam mbTestParam = new MbTestParam();
        CommonResult<Integer> result = mbTestFacade.testOpen(mbTestParam);
        System.out.println(result.getContent());
    }

}
