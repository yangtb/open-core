package com.sm.open.core.service.service.pf.biz.tests.impl;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.core.dal.pf.biz.kb.PfCaseHistoryDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestPaperDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestPlanDao;
import com.sm.open.core.dal.pf.biz.tests.PfTestWaitingRoomDao;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamTagDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestWatingRoomDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.casehistory.FaqMedicalrecVo;
import com.sm.open.core.model.vo.pf.biz.test.*;
import com.sm.open.core.model.vo.pf.biz.test.paper.PfTestPaperInfoVo;
import com.sm.open.core.service.service.pf.biz.tests.PfTestWaitingRoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfTestWaitingRoomServiceimpl implements PfTestWaitingRoomService {

    @Resource
    private PfTestWaitingRoomDao pfTestWaitingRoomDao;

    @Resource
    private PfCaseHistoryDao pfCaseHistoryDao;

    @Resource
    private PfTestPaperDao pfTestPaperDao;

    @Resource
    private PfTestPlanDao pfTestPlanDao;

    @Override
    public Long countWaitingRoom(PfTestWatingRoomDto dto) {
        return pfTestWaitingRoomDao.countWaitingRoom(dto);
    }

    @Override
    public List<PfTestWaitingRoomVo> listWaitingRoom(PfTestWatingRoomDto dto) {
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

    @Override
    public PfTestPaperInfoVo selectTestPaperInto(PfTestExamDto dto) {
        PfTestPaperInfoVo pfTestPaperInfoVo = pfTestWaitingRoomDao.selectTestPaperInfo(dto.getIdTestplanDetail(), dto.getIdStudent());
        if (pfTestPaperInfoVo == null) {
            pfTestPaperInfoVo = new PfTestPaperInfoVo();
        }
        // 试卷信息
        ExmTestpaper exmTestpaper = new ExmTestpaper();
        if (dto.getIdTestpaper() == -1) {
            exmTestpaper.setNaTestpaper("自定义");
            ExmTestplan exmTestplan = pfTestPlanDao.selectPlanById(dto.getIdTestplan());
            if (exmTestplan != null) {
                pfTestPaperInfoVo.setPaperCreator(exmTestplan.getCreator());
                pfTestPaperInfoVo.setOrgName(exmTestplan.getOrgName());
            }
        } else {
            exmTestpaper = pfTestPaperDao.selectTestPaperById(dto.getIdTestpaper());
            pfTestPaperInfoVo.setOrgName(exmTestpaper.getOrgName());
            pfTestPaperInfoVo.setPaperCreator(exmTestpaper.getCreator());
        }

        // 病例信息
        FaqMedicalrecVo faqMedicalrecVo = pfCaseHistoryDao.selectCaseInfoById(dto.getIdMedicalrec());
        String testPaperName = exmTestpaper != null ? exmTestpaper.getNaTestpaper() : "";
        if (faqMedicalrecVo != null) {
            testPaperName += StringUtils.isNotBlank(faqMedicalrecVo.getName()) ? "-" + faqMedicalrecVo.getName() : "";
        }
        pfTestPaperInfoVo.setTestPaperName(testPaperName);
        return pfTestPaperInfoVo;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public PfWaitingRoomStartVo startExam(ExmTestexec dto) {
        PfWaitingRoomStartVo pfWaitingRoomStartVo = new PfWaitingRoomStartVo();
        Integer num = pfTestWaitingRoomDao.startExam(dto);
        if (num == 1) {
            ExmMedResult exmMedResult = new ExmMedResult();
            exmMedResult.setIdTestexec(dto.getIdTestexec());
            // 序号，暂时写死
            exmMedResult.setSerial(1);
            exmMedResult.setFgFinalResult(YesOrNoNum.YES.getCode());
            exmMedResult.setFgAsses(YesOrNoNum.YES.getCode());
            pfTestWaitingRoomDao.insertExmMedResult(exmMedResult);
            pfWaitingRoomStartVo.setIdTestexecResult(exmMedResult.getIdTestexecResult());
        }
        pfWaitingRoomStartVo.setIdTestexec(dto.getIdTestexec());
        return pfWaitingRoomStartVo;
    }

    @Override
    public boolean endExam(ExmTestexec dto) {
        return pfTestWaitingRoomDao.endExam(dto) == 1 ? true : false;
    }

    @Override
    public PfWaitingRoomPatVo selectPatInfo(PfTestExamTagDto dto) {
        PfWaitingRoomPatVo pfWaitingRoomPatVo = pfTestWaitingRoomDao.selectPatInfo(dto);
        PfWaitingRoomPatVo photo = pfTestWaitingRoomDao.selectPhotoInfo(dto.getIdMedicalrec());
        pfWaitingRoomPatVo.setExt(photo != null ? photo.getExt() : null);
        return pfWaitingRoomPatVo;
    }

    @Override
    public List<FaqMedCaseInquesList> listTestCons(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.listTestCons(dto);
    }

    @Override
    public Long saveConsQa(ExmMedResultInques dto) {
        Long idTestexecResultInques = pfTestWaitingRoomDao.isExistConsQa(dto);
        if (idTestexecResultInques == null) {
            pfTestWaitingRoomDao.saveConsQa(dto);
        } else {
            pfTestWaitingRoomDao.delConsQa(idTestexecResultInques, dto.getFgValid());
            dto.setIdTestexecResultInques(idTestexecResultInques);
        }
        return dto.getIdTestexecResultInques();
    }

    @Override
    public boolean updateConsStatus(PfBachChangeStatusDto dto) {
        return pfTestWaitingRoomDao.updateConsStatus(dto) >= 1 ? true : false;
    }

    @Override
    public List<PfWaitingRoomConsVo> listConsQa(PfTestExamTagDto dto) {
        return pfTestWaitingRoomDao.listConsQa(dto);
    }
}
