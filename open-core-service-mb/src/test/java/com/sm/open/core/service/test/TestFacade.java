package com.sm.open.core.service.test;

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
        LOGGER.info("1-测试facade层");
        MbTestParam mbTestParam = new MbTestParam();
        mbTestParam.setTestId(1L);
        CommonResult<Integer> result = mbTestFacade.testOpen(mbTestParam);
        System.out.println(result.getContent());
    }

}
