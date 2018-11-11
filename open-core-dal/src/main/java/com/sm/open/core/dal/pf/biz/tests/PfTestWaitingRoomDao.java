package com.sm.open.core.dal.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamTagDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestWatingRoomDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.test.PfTestReceivePatVo;
import com.sm.open.core.model.vo.pf.biz.test.PfTestWaitingRoomVo;
import com.sm.open.core.model.vo.pf.biz.test.PfWaitingRoomConsVo;
import com.sm.open.core.model.vo.pf.biz.test.PfWaitingRoomPatVo;
import com.sm.open.core.model.vo.pf.biz.test.paper.PfTestPaperInfoVo;
import org.apache.ibatis.annotations.Param;
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
     * @param idTestplanDetail 计划明细id
     * @param idStudent        学生id
     * @return
     */
    PfTestPaperInfoVo selectTestPaperInfo(@Param("idTestplanDetail") Long idTestplanDetail,
                                          @Param("idStudent") Long idStudent);

    /**
     * 开始考试
     *
     * @param dto
     * @return
     */
    Integer startExam(ExmTestexec dto);

    /**
     * 插入测试执行_病历结果
     *
     * @param dto
     * @return
     */
    Integer insertExmMedResult(ExmMedResult dto);

    /**
     * 交卷
     *
     * @param dto
     * @return
     */
    Integer endExam(ExmTestexec dto);

    /**
     * 查询患者信息
     *
     * @param dto
     * @return
     */
    PfWaitingRoomPatVo selectPatInfo(PfTestExamTagDto dto);

    /**
     * 根据病例id查询照片
     *
     * @param idMedicalrec 病例ID
     * @return
     */
    PfWaitingRoomPatVo selectPhotoInfo(Long idMedicalrec);

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
    Integer saveConsQa(ExmMedResultInques dto);

    /**
     * 问诊 - 是否存在
     *
     * @param dto
     * @return
     */
    Long isExistConsQa(ExmMedResultInques dto);

    /**
     * 问诊 - 删除
     *
     * @param idTestexecResultInques 主键
     * @param fgValid                删除标识
     * @return
     */
    Integer delConsQa(@Param("idTestexecResultInques") Long idTestexecResultInques,
                      @Param("fgValid") String fgValid);

    /**
     * 问诊 - 线索标志
     *
     * @param dto
     * @return
     */
    Integer updateConsStatus(PfBachChangeStatusDto dto);

    /**
     * 问诊 - qa列表
     *
     * @param dto
     * @return
     */
    List<PfWaitingRoomConsVo> listConsQa(PfTestExamTagDto dto);
}
