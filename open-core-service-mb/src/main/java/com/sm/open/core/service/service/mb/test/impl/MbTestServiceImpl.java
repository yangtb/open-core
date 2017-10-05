package com.sm.open.core.service.service.mb.test.impl;

import com.sm.open.care.core.log.LoggerUtil;
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
        LoggerUtil.info(LOGGER,"3-【MbTestServiceImpl-testOpen-params】");
        return mbTestDao.testOpen();
    }
}
