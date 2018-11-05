package com.sm.open.core.service.facade.pf.biz.tests;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.tests.room.PfTestWatingRoomParam;
import com.sm.open.core.facade.model.result.pf.biz.tests.paper.ExmTestpaperResult;
import com.sm.open.core.facade.model.rpc.PfPageParam;
import com.sm.open.core.facade.model.rpc.PfPageResult;
import com.sm.open.core.facade.model.rpc.PfResultFactory;
import com.sm.open.core.facade.pf.biz.tests.PfTestWaitingRoomFacade;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestWatingRoomDto;
import com.sm.open.core.service.service.pf.biz.tests.PfTestWaitingRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfTestWaitingRoomFacade")
public class PfTestWaitingRoomFacadeImpl implements PfTestWaitingRoomFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfTestWaitingRoomFacadeImpl.class);

    @Resource
    private PfTestWaitingRoomService pfTestWaitingRoomService;


    @Override
    public PfPageResult listWaitingRoom(PfTestWatingRoomParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfTestWatingRoomDto dto = BeanUtil.convert(param, PfTestWatingRoomDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfTestWaitingRoomService.countWaitingRoom(dto),
                    BeanUtil.convertList(pfTestWaitingRoomService.listWaitingRoom(dto), ExmTestpaperResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listWaitingRoom-error】分页查询候诊室列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.PAGE_WAITING_ROOM_LIST_ERROR, PfTestPaperConstant.PAGE_WAITING_ROOM_LIST_ERROR_MSG);
        }
    }

    @Override
    public PfPageResult listReceivePat(PfTestWatingRoomParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfTestWatingRoomDto dto = BeanUtil.convert(param, PfTestWatingRoomDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfTestWaitingRoomService.countReceivePat(dto),
                    BeanUtil.convertList(pfTestWaitingRoomService.listReceivePat(dto), ExmTestpaperResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listReceivePat-error】分页查询接诊列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.PAGE_RECEIVE_PAT_LIST_ERROR, PfTestPaperConstant.PAGE_RECEIVE_PAT_LIST_ERROR_MSG);
        }
    }


}
