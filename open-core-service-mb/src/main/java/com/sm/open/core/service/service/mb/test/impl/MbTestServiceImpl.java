package com.sm.open.core.service.service.mb.test.impl;

import com.sm.open.core.dal.mb.test.MbTestDao;
import com.sm.open.core.service.service.mb.test.MbTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("mbTestService")
public class MbTestServiceImpl implements MbTestService {

    @Resource
    private MbTestDao mbTestDao;

    @Override
    public Integer testOpen() {
        return mbTestDao.testOpen();
    }
}
