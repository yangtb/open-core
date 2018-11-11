package com.sm.open.core.service.service.pf.biz.tests;

import com.sm.open.core.facade.model.result.pf.biz.tests.room.PfWaitingRoomStartResult;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamTagDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestWatingRoomDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.ExmMedResultInques;
import com.sm.open.core.model.entity.ExmTestexec;
import com.sm.open.core.model.entity.FaqMedCaseInquesList;
import com.sm.open.core.model.vo.pf.biz.test.*;
import com.sm.open.core.model.vo.pf.biz.test.paper.PfTestPaperInfoVo;
import com.sm.open.core.model.vo.pf.biz.test.paper.PfTestPaperVo;

import java.util.List;

/**
 * @ClassName: PfTestWaitingRoomService
 * @Description: 模拟就诊service
 * @Author yangtongbin
 * @Date 2018/11/4
 */
public interface PfTestWaitingRoomService {

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
    List<PfTestWaitingRoomVo> listWaitingRoom(PfTestWatingRoomDto dto);

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

    /**
     * 查询试卷信息
     *
     * @param dto
     * @return
     */
    PfTestPaperInfoVo selectTestPaperInto(PfTestExamDto dto);

    /**
     * 开始考试
     *
     * @param dto
     * @return
     */
    PfWaitingRoomStartVo startExam(ExmTestexec dto);

    /**
     * 交卷
     *
     * @param dto
     * @return
     */
    boolean endExam(ExmTestexec dto);

    /**
     * 查询患者信息
     *
     * @param dto
     * @return
     */
    PfWaitingRoomPatVo selectPatInfo(PfTestExamTagDto dto);

    /**
     * 查询问诊信息
     *
     * @param dto
     * @return
     */
    List<FaqMedCaseInquesList> listTestCons(PfTestExamTagDto dto);

    /**
     * 问诊 - 保存问答问题
     *
     * @param dto
     * @return
     */
    Long saveConsQa(ExmMedResultInques dto);

    /**
     * 问诊 - 线索标志
     *
     * @param dto
     * @return
     */
    boolean updateConsStatus(PfBachChangeStatusDto dto);

    /**
     * 问诊 - qa列表
     *
     * @param dto
     * @return
     */
    List<PfWaitingRoomConsVo> listConsQa(PfTestExamTagDto dto);

}
