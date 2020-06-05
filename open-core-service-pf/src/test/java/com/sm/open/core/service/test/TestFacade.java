package com.sm.open.core.service.test;

import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.pf.biz.drug.PfDrugFacade;
import com.sm.open.core.service.common.ApplicationContextTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

public class TestFacade extends ApplicationContextTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestFacade.class);

    @Resource
    private PfDrugFacade pfDrugFacade;


    @Rollback(value = false)
    @Test
    public void testPinyin() {
        CommonResult<Boolean> result = pfDrugFacade.dealPinyin();
        System.out.println(result.getContent());
    }

}
