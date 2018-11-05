package com.sm.open.core.facade.pf.biz.tests;

import com.sm.open.core.facade.model.param.pf.biz.tests.room.PfTestWatingRoomParam;
import com.sm.open.core.facade.model.rpc.PfPageResult;

/**
 * @ClassName: PfTestWaitingRoomFacade
 * @Description: 模拟就诊facade
 * @Author yangtongbin
 * @Date 2018/11/4
 */
public interface PfTestWaitingRoomFacade {

    /**
     * 候诊室列表
     *
     * @param param
     * @return
     */
    PfPageResult listWaitingRoom(PfTestWatingRoomParam param);

    /**
     * 接诊列表
     *
     * @param param
     * @return
     */
    PfPageResult listReceivePat(PfTestWatingRoomParam param);
}
