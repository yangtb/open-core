package com.sm.open.core.service.service.mb.test.impl;

import com.sm.open.core.dal.mb.test.MbTestDao;
import com.sm.open.core.service.service.mb.test.MbTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("mbTestService")
public class MbTestServiceImpl implements MbTestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MbTestServiceImpl.class);

    @Resource
    private MbTestDao mbTestDao;

    @Override
    public Integer testOpen() {
        LOGGER.info("3-测试service层");
        return mbTestDao.testOpen();
    }
}
