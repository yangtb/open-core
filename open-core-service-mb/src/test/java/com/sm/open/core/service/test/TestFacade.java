package com.sm.open.core.service.test;

import com.sm.open.core.facade.mb.test.MbTestFacade;
import com.sm.open.core.service.common.ApplicationContextTest;
import org.junit.Test;

import javax.annotation.Resource;

public class TestFacade extends ApplicationContextTest {

    @Resource(name = "mbTestFacade")
    private MbTestFacade mbTestFacade;

    @Test
    public void doTestFacade() {
        System.out.println(mbTestFacade.testOpen());
    }


}
