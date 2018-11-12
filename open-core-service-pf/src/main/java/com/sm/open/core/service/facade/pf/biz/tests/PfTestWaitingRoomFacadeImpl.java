package com.sm.open.core.service.facade.pf.biz.tests;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.tests.room.*;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.clinic.PfCaseHistoryTagResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseBodyListResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseBodyResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseInquesListResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseInspectListResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.*;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.paper.PfTestPaperInfoResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.paper.PfTestPaperResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.paper.PfTestPaperStudentResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.tests.PfTestWaitingRoomFacade;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestExamTagDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestWatingRoomDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.test.PfWaitingRoomPatVo;
import com.sm.open.core.service.facade.pf.biz.kb.PfKbPartConstant;
import com.sm.open.core.service.service.pf.biz.clinic.PfClinicTemplateService;
import com.sm.open.core.service.service.pf.biz.kb.PfKbPartService;
import com.sm.open.core.service.service.pf.biz.tests.PfTestWaitingRoomService;
import com.sm.open.core.service.service.pf.user.login.PfUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfTestWaitingRoomFacade")
public class PfTestWaitingRoomFacadeImpl implements PfTestWaitingRoomFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfTestWaitingRoomFacadeImpl.class);

    @Resource
    private PfTestWaitingRoomService pfTestWaitingRoomService;

    @Resource
    private PfClinicTemplateService pfClinicTemplateService;

    @Resource
    private PfUserService pfUserService;

    @Resource
    private PfKbPartService pfKbPartService;


    @Override
    public PfPageResult listWaitingRoom(PfTestWatingRoomParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfTestWatingRoomDto dto = BeanUtil.convert(param, PfTestWatingRoomDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfTestWaitingRoomService.countWaitingRoom(dto),
                    BeanUtil.convertList(pfTestWaitingRoomService.listWaitingRoom(dto), PfTestWaitingRoomResult.class));
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
                    BeanUtil.convertList(pfTestWaitingRoomService.listReceivePat(dto), PfTestWaitingRoomResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listReceivePat-error】分页查询接诊列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.PAGE_RECEIVE_PAT_LIST_ERROR, PfTestPaperConstant.PAGE_RECEIVE_PAT_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<PfTestPaperResult> selectTestPaperInfo(PfTestExamParam param) {
        try {
            Assert.isTrue(param.getIdDemo() != null, "idDemo");
            Assert.isTrue(param.getIdStudent() != null, "idStudent");
            Assert.isTrue(param.getIdTestplanDetail() != null, "idTestplanDetail");
            Assert.isTrue(param.getIdTestpaper() != null, "idTestpaper");
            Assert.isTrue(param.getIdMedicalrec() != null, "idMedicalrec");

            PfTestPaperResult pfTestPaperResult = new PfTestPaperResult();
            pfTestPaperResult.setStudentInfo(BeanUtil.convert(pfUserService.selectStudentInfo(param.getIdStudent()), PfTestPaperStudentResult.class));
            pfTestPaperResult.setPaperInfo(BeanUtil.convert(
                    pfTestWaitingRoomService.selectTestPaperInto(
                            BeanUtil.convert(param, PfTestExamDto.class)), PfTestPaperInfoResult.class));
            pfTestPaperResult.setTags(BeanUtil.convertList(pfClinicTemplateService.listAllCaseHistoryTag(param.getIdDemo()), PfCaseHistoryTagResult.class));

            return ResultFactory.initCommonResultWithSuccess(pfTestPaperResult);
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectTestPaperInfo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectTestPaperInfo】查询试卷信息出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_TEST_PAPER_INFO_ERROR, PfTestPaperConstant.SELECT_TEST_PAPER_INFO_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<PfWaitingRoomStartResult> startExam(ExmTestexecParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(
                            pfTestWaitingRoomService.startExam(BeanUtil.convert(param, ExmTestexec.class)),
                            PfWaitingRoomStartResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-startExam】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-startExam】开始答题出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.START_EXAM_ERROR, PfTestPaperConstant.START_EXAM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> endExam(ExmTestexecParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.endExam(BeanUtil.convert(param, ExmTestexec.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-endExam】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-endExam】提交试卷出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.END_EXAM_ERROR, PfTestPaperConstant.END_EXAM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<PfWaitingRoomPatResult> selectPatInfo(PfTestExamTagParam param) {
        try {
            Assert.isTrue(param.getIdMedicalrec() != null, "idMedicalrec");
            Assert.isTrue(StringUtils.isNotBlank(param.getCdMedAsse()), "cdMedAsse");

            PfWaitingRoomPatVo pfWaitingRoomPatVo = pfTestWaitingRoomService.selectPatInfo(BeanUtil.convert(param, PfTestExamTagDto.class));

            if (pfWaitingRoomPatVo.getExt() != null) {
                FaqMedCaseBody faqMedCaseBody = pfKbPartService.selectFaqMedCaseBody(pfWaitingRoomPatVo.getExt());
                if (faqMedCaseBody != null) {
                    pfWaitingRoomPatVo.setFrontPhotoUrl(faqMedCaseBody.getFrontPath());
                    pfWaitingRoomPatVo.setBackPhotoUrl(faqMedCaseBody.getBackPath());
                }
            }
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfWaitingRoomPatVo, PfWaitingRoomPatResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectPatInfo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectPatInfo】查询患者信息出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_PAT_INFO_ERROR, PfTestPaperConstant.SELECT_PAT_INFO_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listTestCons(PfTestExamTagParam param) {
        try {
            PfTestExamTagDto dto = BeanUtil.convert(param, PfTestExamTagDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfTestWaitingRoomService.listTestCons(dto), FaqMedCaseInquesListResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listTestCons-error】查询问诊列表出错，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.LIST_TEST_CONS_ERROR, PfTestPaperConstant.LIST_TEST_CONS_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> saveConsQa(ExmMedResultInquesParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveConsQa(BeanUtil.convert(param, ExmMedResultInques.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveConsQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveConsQa】保存问诊出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_CONS_QA_ERROR, PfTestPaperConstant.SAVE_CONS_QA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> updateConsStatus(PfBachChangeStatusParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.updateConsStatus(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-updateConsStatus】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-updateConsStatus】修改问诊状态出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.UPDATE_CONS_STATUS_ERROR, PfTestPaperConstant.UPDATE_CONS_STATUS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfWaitingRoomConsResult>> listConsQa(PfTestExamTagParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.listConsQa(BeanUtil.convert(param, PfTestExamTagDto.class)),
                            PfWaitingRoomConsResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listConsQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listConsQa】查询问诊出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_CONS_QA_ERROR, PfTestPaperConstant.LIST_CONS_QA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<FaqMedCaseBodyResult> selectPic(PfTestExamTagParam param) {
        try {
            Long idMedCase = pfTestWaitingRoomService.selectPic(BeanUtil.convert(param, PfTestExamTagDto.class));
            Assert.isTrue(idMedCase != null, "idMedCase");
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfKbPartService.selectFaqMedCaseBody(idMedCase), FaqMedCaseBodyResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-selectFaqMedCaseBody】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-selectFaqMedCaseBody】查询检查定义图片失败, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SELECT_FAQ_MED_CASE_BODY_ERROR, PfKbPartConstant.SELECT_FAQ_MED_CASE_BODY_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listTestCheck(PfTestExamTagParam param) {
        try {
            PfTestExamTagDto dto = BeanUtil.convert(param, PfTestExamTagDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfTestWaitingRoomService.listTestCheck(dto), FaqMedCaseBodyListResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listTestCheck-error】查询检查列表出错，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.LIST_TEST_CHECK_ERROR, PfTestPaperConstant.LIST_TEST_CHECK_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> saveCheckQa(ExmMedResultBodyParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveCheckQa(BeanUtil.convert(param, ExmMedResultBody.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveCheckQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveCheckQa】保存检查出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_CHECK_QA_ERROR, PfTestPaperConstant.SAVE_CHECK_QA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> updateCheckStatus(PfBachChangeStatusParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.updateCheckStatus(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-updateCheckStatus】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-updateCheckStatus】修改检查状态出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.UPDATE_CHECK_STATUS_ERROR, PfTestPaperConstant.UPDATE_CHECK_STATUS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfWaitingRoomCheckResult>> listCheckQa(PfTestExamTagParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.listCheckQa(BeanUtil.convert(param, PfTestExamTagDto.class)),
                            PfWaitingRoomCheckResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listCheckQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listCheckQa】查询检查出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_CHECK_QA_ERROR, PfTestPaperConstant.LIST_CHECK_QA_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listTestExam(PfTestExamTagParam param) {
        try {
            PfTestExamTagDto dto = BeanUtil.convert(param, PfTestExamTagDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfTestWaitingRoomService.listTestExam(dto), FaqMedCaseInspectListResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listTestExam-error】查询检验列表出错，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.LIST_TEST_EXAM_ERROR, PfTestPaperConstant.LIST_TEST_EXAM_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> saveExamQa(ExmMedResultInspectParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveExamQa(BeanUtil.convert(param, ExmMedResultInspect.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveExamQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveExamQa】保存检验出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_EXAM_QA_ERROR, PfTestPaperConstant.SAVE_EXAM_QA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> updateExamStatus(PfBachChangeStatusParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.updateExamStatus(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-updateExamStatus】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-updateExamStatus】修改检验状态出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.UPDATE_EXAM_STATUS_ERROR, PfTestPaperConstant.UPDATE_EXAM_STATUS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfWaitingRoomExamResult>> listExamQa(PfTestExamTagParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.listExamQa(BeanUtil.convert(param, PfTestExamTagDto.class)),
                            PfWaitingRoomExamResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listExamQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listExamQa】查询检验出错, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_EXAM_QA_ERROR, PfTestPaperConstant.LIST_EXAM_QA_ERROR_MSG));
        }
    }


}
