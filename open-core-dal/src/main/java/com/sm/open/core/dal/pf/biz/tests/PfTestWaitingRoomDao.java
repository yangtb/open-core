package com.sm.open.core.dal.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.PfTestWatingRoomDto;
import com.sm.open.core.model.vo.pf.biz.test.PfTestReceivePatVo;
import com.sm.open.core.model.vo.pf.biz.test.PfTestWatingRoomVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfTestWaitingRoomDao {

    /**
     * 候诊室列表count
     *
     * @param dto
     * @return
     */
    Long countWaitingRoom(PfTestWatingRoomDto dto);

    /**
     * 候诊室列表
     *
     * @param dto
     * @return
     */
    List<PfTestWatingRoomVo> listWaitingRoom(PfTestWatingRoomDto dto);

    /**
     * 接诊列表count
     *
     * @param dto
     * @return
     */
    Long countReceivePat(PfTestWatingRoomDto dto);

    /**
     * 接诊列表
     *
     * @param dto
     * @return
     */
    List<PfTestReceivePatVo> listReceivePat(PfTestWatingRoomDto dto);

}
