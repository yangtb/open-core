package com.sm.open.core.facade.pf.biz.tests;

import com.sm.open.core.facade.model.param.pf.biz.tests.room.*;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseBodyResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.*;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.paper.PfTestPaperResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

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

    /**
     * 获取试卷信息
     *
     * @param param
     * @return
     */
    CommonResult<PfTestPaperResult> selectTestPaperInfo(PfTestExamParam param);

    /**
     * 开始考试
     *
     * @param param
     * @return
     */
    CommonResult<PfWaitingRoomStartResult> startExam(ExmTestexecParam param);

    /**
     * 交卷
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> endExam(ExmTestexecParam param);

    /**
     * 查询患者信息
     *
     * @param param
     * @return
     */
    CommonResult<PfWaitingRoomPatResult> selectPatInfo(PfTestExamTagParam param);

    /**
     * 查询问诊列表
     *
     * @param param
     * @return
     */
    PfPageResult listTestCons(PfTestExamTagParam param);


    /**
     * 问诊 - 保存问答问题
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveConsQa(ExmMedResultInquesParam param);

    /**
     * 问诊 - 线索标志
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updateConsStatus(PfBachChangeStatusParam param);

    /**
     * 问诊 - qa列表
     *
     * @param param
     * @return
     */
    CommonResult<List<PfWaitingRoomConsResult>> listConsQa(PfTestExamTagParam param);

    /**
     * 查询检查图片
     *
     * @param param
     * @return
     */
    CommonResult<FaqMedCaseBodyResult> selectPic(PfTestExamTagParam param);

    /**
     * 检查 - 列表
     *
     * @param param
     * @return
     */
    PfPageResult listTestCheck(PfTestExamTagParam param);

    /**
     * 检查 - 保存问答问题
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveCheckQa(ExmMedResultBodyParam param);

    /**
     * 检查 - 线索标志
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updateCheckStatus(PfBachChangeStatusParam param);

    /**
     * 检查 - qa列表
     *
     * @param param
     * @return
     */
    CommonResult<List<PfWaitingRoomCheckResult>> listCheckQa(PfTestExamTagParam param);


    /**
     * 检验 - 列表
     *
     * @param param
     * @return
     */
    PfPageResult listTestExam(PfTestExamTagParam param);


    /**
     * 检验 - 保存
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveExamQa(ExmMedResultInspectParam param);

    /**
     * 检验 - 线索标志
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updateExamStatus(PfBachChangeStatusParam param);

    /**
     * 检验 - qa列表
     *
     * @param param
     * @return
     */
    CommonResult<List<PfWaitingRoomExamResult>> listExamQa(PfTestExamTagParam param);
}
