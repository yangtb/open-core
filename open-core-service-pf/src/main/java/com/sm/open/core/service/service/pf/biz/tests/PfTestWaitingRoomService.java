package com.sm.open.core.service.service.pf.biz.tests;

import com.sm.open.core.facade.model.param.pf.biz.tests.room.PfTestExamTagParam;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamTagDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestWatingRoomDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.test.*;
import com.sm.open.core.model.vo.pf.biz.test.paper.PfTestPaperInfoVo;

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

    /**
     * 查询图片
     *
     * @param dto
     * @return
     */
    Long selectPic(PfTestExamTagDto dto);

    /**
     * 检查 - 列表
     *
     * @param dto
     * @return
     */
    List<FaqMedCaseBodyList> listTestCheck(PfTestExamTagDto dto);

    /**
     * 检查 - 保存问答问题
     *
     * @param dto
     * @return
     */
    Long saveCheckQa(ExmMedResultBody dto);

    /**
     * 检查 - 线索标志
     *
     * @param dto
     * @return
     */
    boolean updateCheckStatus(PfBachChangeStatusDto dto);

    /**
     * 检查 - qa列表
     *
     * @param dto
     * @return
     */
    List<PfWaitingRoomCheckVo> listCheckQa(PfTestExamTagDto dto);

    /**
     * 检验 - 列表
     *
     * @param dto
     * @return
     */
    List<FaqMedCaseInspectList> listTestExam(PfTestExamTagDto dto);

    /**
     * 检验 - 保存问答问题
     *
     * @param dto
     * @return
     */
    Long saveExamQa(ExmMedResultInspect dto);

    /**
     * 检验 - 线索标志
     *
     * @param dto
     * @return
     */
    boolean updateExamStatus(PfBachChangeStatusDto dto);

    /**
     * 检验 - qa列表
     *
     * @param dto
     * @return
     */
    List<PfWaitingRoomExamVo> listExamQa(PfTestExamTagDto dto);

    /**
     * 拟诊 - 保存
     *
     * @param dto
     * @return
     */
    Long saveReferral(ExmMedResultReferral dto);

    /**
     * 已添加拟诊
     *
     * @param dto
     * @return
     */
    List<ExmMedResultReferral> listReferral(PfTestExamTagDto dto);

}
