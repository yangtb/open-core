package com.sm.open.core.service.facade.mb.test;

import com.sm.open.core.facade.mb.test.MbTestFacade;
import com.sm.open.core.service.service.mb.test.MbTestService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("mbTestFacade")
public class MbTestFacadeImpl implements MbTestFacade {

    @Resource
    private MbTestService mbTestService;

    @Override
    public Integer testOpen() {
        return mbTestService.testOpen();
    }
}
