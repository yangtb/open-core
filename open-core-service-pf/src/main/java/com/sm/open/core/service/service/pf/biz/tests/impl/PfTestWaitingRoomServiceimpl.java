package com.sm.open.core.service.service.pf.biz.tests.impl;

import com.sm.open.core.dal.pf.biz.tests.PfTestWaitingRoomDao;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestWatingRoomDto;
import com.sm.open.core.model.vo.pf.biz.test.PfTestReceivePatVo;
import com.sm.open.core.model.vo.pf.biz.test.PfTestWatingRoomVo;
import com.sm.open.core.service.service.pf.biz.tests.PfTestWaitingRoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfTestWaitingRoomServiceimpl implements PfTestWaitingRoomService {

    @Resource
    private PfTestWaitingRoomDao pfTestWaitingRoomDao;


    @Override
    public Long countWaitingRoom(PfTestWatingRoomDto dto) {
        return pfTestWaitingRoomDao.countWaitingRoom(dto);
    }

    @Override
    public List<PfTestWatingRoomVo> listWaitingRoom(PfTestWatingRoomDto dto) {
        return pfTestWaitingRoomDao.listWaitingRoom(dto);
    }

    @Override
    public Long countReceivePat(PfTestWatingRoomDto dto) {
        return pfTestWaitingRoomDao.countReceivePat(dto);
    }

    @Override
    public List<PfTestReceivePatVo> listReceivePat(PfTestWatingRoomDto dto) {
        return pfTestWaitingRoomDao.listReceivePat(dto);
    }
}
