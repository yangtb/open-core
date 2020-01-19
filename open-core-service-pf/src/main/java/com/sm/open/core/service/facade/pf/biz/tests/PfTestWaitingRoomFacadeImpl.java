package com.sm.open.core.service.facade.pf.biz.tests;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.tests.room.*;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCatalogueTreeParam;
import com.sm.open.core.facade.model.param.pf.common.PfCommonListParam;
import com.sm.open.core.facade.model.result.pf.biz.clinic.PfCaseHistoryTagResult;
import com.sm.open.core.facade.model.result.pf.biz.disease.BasDieResult;
import com.sm.open.core.facade.model.result.pf.biz.disease.PfDiseaseZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseBodyListResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseBodyResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseInquesListResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseInspectListResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.*;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.eva.*;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.paper.PfTestPaperInfoResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.paper.PfTestPaperResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.paper.PfTestPaperStudentResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.tests.PfTestWaitingRoomFacade;
import com.sm.open.core.model.dto.pf.biz.tests.*;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.model.vo.pf.biz.test.PfWaitingRoomPatVo;
import com.sm.open.core.service.facade.pf.biz.disease.PfDiseaseConstant;
import com.sm.open.core.service.facade.pf.biz.kb.PfKbPartConstant;
import com.sm.open.core.service.service.pf.biz.clinic.PfClinicTemplateService;
import com.sm.open.core.service.service.pf.biz.kb.PfKbPartService;
import com.sm.open.core.service.service.pf.biz.tests.PfTestWaitingRoomService;
import com.sm.open.core.service.service.pf.user.login.PfUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
                    BeanUtil.convertList(pfTestWaitingRoomService.listReceivePat(dto), PfTestReceivePatResult.class));
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
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectTestPaperInfo】查询试卷信息出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_TEST_PAPER_INFO_ERROR, PfTestPaperConstant.SELECT_TEST_PAPER_INFO_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<PfTestPaperResult> selectTestPaper(PfTestExamParam param) {
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
            return ResultFactory.initCommonResultWithSuccess(pfTestPaperResult);
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectTestPaperInfo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectTestPaperInfo】查询试卷信息出错, param:{}", param.toString(), e);
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
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-startExam】开始答题出错, param:{}", param.toString(), e);
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
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-endExam】提交试卷出错, param:{}", param.toString(), e);
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
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectPatInfo】查询患者信息出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_PAT_INFO_ERROR, PfTestPaperConstant.SELECT_PAT_INFO_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listTestCons(PfTestExamTagParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfTestExamTagDto dto = BeanUtil.convert(param, PfTestExamTagDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfTestWaitingRoomService.countTestCons(dto),
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
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveConsQa】保存问诊出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_CONS_QA_ERROR, PfTestPaperConstant.SAVE_CONS_QA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editConsQa(PfExmMedResultParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.editConsQa(BeanUtil.convert(param, PfExmMedResultDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-editConsQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-editConsQa】编辑问诊出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_CHECK_QA_ERROR, PfTestPaperConstant.SAVE_CHECK_QA_ERROR_MSG));
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
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-updateConsStatus】修改问诊状态出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.UPDATE_CONS_STATUS_ERROR, PfTestPaperConstant.UPDATE_CONS_STATUS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfWaitingRoomConsResult>> listConsQa(PfTestExamTagParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    PfTestBeanUtil.convertConsList(pfTestWaitingRoomService.listConsQa(BeanUtil.convert(param, PfTestExamTagDto.class))));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listConsQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listConsQa】查询问诊出错, param:{}", param.toString(), e);
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
            PfPageParam.initPageDto(param);
            PfTestExamTagDto dto = BeanUtil.convert(param, PfTestExamTagDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfTestWaitingRoomService.countTestCheck(dto),
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
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveCheckQa】保存检查出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_CHECK_QA_ERROR, PfTestPaperConstant.SAVE_CHECK_QA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editCheckQa(ExmMedResultBodyParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.editCheckQa(BeanUtil.convert(param, ExmMedResultBody.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-editCheckQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-editCheckQa】编辑检查出错, param:{}", param.toString(), e);
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
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-updateCheckStatus】修改检查状态出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.UPDATE_CHECK_STATUS_ERROR, PfTestPaperConstant.UPDATE_CHECK_STATUS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfWaitingRoomCheckResult>> listCheckQa(PfTestExamTagParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    PfTestBeanUtil.convertCheckList(pfTestWaitingRoomService.listCheckQa(BeanUtil.convert(param, PfTestExamTagDto.class))));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listCheckQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listCheckQa】查询检查出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_CHECK_QA_ERROR, PfTestPaperConstant.LIST_CHECK_QA_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listTestExam(PfTestExamTagParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfTestExamTagDto dto = BeanUtil.convert(param, PfTestExamTagDto.class);
            PfPageResult pageResult = PfResultFactory.initPagePfResultWithSuccess(pfTestWaitingRoomService.countTestExam(dto),
                    BeanUtil.convertList(pfTestWaitingRoomService.listTestExam(dto), FaqMedCaseInspectListResult.class));
            // 统计金额
            pageResult.setExt(String.valueOf(pfTestWaitingRoomService.examAmountTotal(dto)));
            return pageResult;
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
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveExamQa】保存检验出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_EXAM_QA_ERROR, PfTestPaperConstant.SAVE_EXAM_QA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<BigDecimal> saveBatchExamQa(List<ExmMedResultInspectParam> param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveBatchExamQa(BeanUtil.convertList(param, ExmMedResultInspect.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveBatchExamQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveBatchExamQa】批量保存检验出错, param:{}", param, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_EXAM_QA_ERROR, PfTestPaperConstant.SAVE_EXAM_QA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editExamQa(ExmMedResultInspectParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.editExamQa(BeanUtil.convert(param, ExmMedResultInspect.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-editExamQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-editExamQa】编辑检验出错, param:{}", param.toString(), e);
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
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-updateExamStatus】修改检验状态出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.UPDATE_EXAM_STATUS_ERROR, PfTestPaperConstant.UPDATE_EXAM_STATUS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfWaitingRoomExamResult>> listExamQa(PfTestExamTagParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    PfTestBeanUtil.convertExamList(pfTestWaitingRoomService.listExamQa(BeanUtil.convert(param, PfTestExamTagDto.class))));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listExamQa】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listExamQa】查询检验出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_EXAM_QA_ERROR, PfTestPaperConstant.LIST_EXAM_QA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveReferral(ExmMedResultReferralParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveReferral(BeanUtil.convert(param, ExmMedResultReferral.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveReferral】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveReferral-error】保存检验出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_REFERRAL_ERROR, PfTestPaperConstant.SAVE_REFERRAL_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> updateReferral(ExmMedResultReferralParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.updateReferral(BeanUtil.convert(param, ExmMedResultReferral.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-updateReferral】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-updateReferral-error】修改拟诊出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_REFERRAL_ERROR, PfTestPaperConstant.SAVE_REFERRAL_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<ExmMedResultReferralResult>> listReferral(PfTestExamTagParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.listReferral(BeanUtil.convert(param, PfTestExamTagDto.class)),
                            ExmMedResultReferralResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listReferral】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listReferral-error】查询拟诊出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_REFERRAL_ERROR, PfTestPaperConstant.LIST_REFERRAL_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<ExmMedResultOrderResult> selectOrders(Long idTestexecResult) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfTestWaitingRoomService.selectOrders(idTestexecResult), ExmMedResultOrderResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectOrders】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectOrders-error】查询医嘱出错, idTestexecResult:{}", idTestexecResult, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_ORDERS_ERROR, PfTestPaperConstant.SELECT_ORDERS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveOrder(ExmMedResultOrderParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveOrder(BeanUtil.convert(param, ExmMedResultOrder.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveOrder】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveOrder-error】保存医嘱出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_ORDERS_ERROR, PfTestPaperConstant.SAVE_ORDERS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> saveDrugs(PfCommonListParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveDrugs(BeanUtil.convert(param, PfCommonListDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveDrugs】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveDrugs-error】保存医嘱用药出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_DRUGS_ERROR, PfTestPaperConstant.SAVE_DRUGS_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listLongDrugs(Long idTestexecResultOrder) {
        try {
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfTestWaitingRoomService.listLongDrugs(idTestexecResultOrder), ExmMedResultOrderLogDrugsResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listLongDrugs-error】查询长期用药列表出错，idTestexecResultOrder:{}", idTestexecResultOrder, e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.LIST_LONG_DRUGS_ERROR, PfTestPaperConstant.LIST_LONG_DRUGS_ERROR_MSG);
        }
    }

    @Override
    public PfPageResult listShortDrugs(Long idTestexecResultOrder) {
        try {
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfTestWaitingRoomService.listShortDrugs(idTestexecResultOrder), ExmMedResultOrderShortDrugsResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listShortDrugs-error】查询临时用药列表出错，idTestexecResultOrder:{}", idTestexecResultOrder, e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.LIST_SHORT_DRUGS_ERROR, PfTestPaperConstant.LIST_SHORT_DRUGS_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> delDrugs(String type, Long id) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfTestWaitingRoomService.delDrugs(type, id));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-delDrugs】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-delDrugs】删除用药出错, type:{}, id:{}", type, id, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.DEL_DRUGS_ERROR, PfTestPaperConstant.DEL_DRUGS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveDiagnosis(ExmMedResultDiagnosisParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveDiagnosis(BeanUtil.convert(param, ExmMedResultDiagnosis.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveDiagnosis】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveDiagnosis-error】保存诊断出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_DIAGNOSIS_ERROR, PfTestPaperConstant.SAVE_DIAGNOSIS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveIdentifyDiagnosis(ExmMedResultIdentifyParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveIdentifyDiagnosis(BeanUtil.convert(param, ExmMedResultIdentify.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveIdentifyDiagnosis】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveIdentifyDiagnosis-error】保存诊断出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_DIAGNOSIS_ERROR, PfTestPaperConstant.SAVE_DIAGNOSIS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delDiagnosis(Long idTestexecResultDiagnosis) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfTestWaitingRoomService.delDiagnosis(idTestexecResultDiagnosis));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-delDiagnosis】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-delDiagnosis-error】删除诊断出错, idTestexecResultDiagnosis:{}", idTestexecResultDiagnosis, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.DEL_DIAGNOSIS_ERROR, PfTestPaperConstant.DEL_DIAGNOSIS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveSummary(ExmMedResultSummaryParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveSummary(BeanUtil.convert(param, ExmMedResultSummary.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveSummary】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveSummary-error】保存诊断小结出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_SUMMARY_ERROR, PfTestPaperConstant.SAVE_SUMMARY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> saveDieReason(List<ExmMedResultDieReasonParam> param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveDieReason(BeanUtil.convertList(param, ExmMedResultDieReason.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveDieReason】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveDieReason-error】保存确诊理由出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_DIE_REASON_ERROR, PfTestPaperConstant.SAVE_DIE_REASON_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delDieReason(Long idDieReason) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfTestWaitingRoomService.delDieReason(idDieReason));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-delDieReason】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-delDieReason-error】删除确诊理由出错, idDieReason:{}", idDieReason, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.DEL_DIE_REASON_ERROR, PfTestPaperConstant.DEL_DIE_REASON_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<ExmMedResultReferralResult>> selectAllReferral(Long idTestexecResult) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.selectAllReferral(idTestexecResult), ExmMedResultReferralResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectAllReferral】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectAllReferral-error】查询拟诊出错, idTestexecResult:{}", idTestexecResult, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_REFERRAL_ERROR, PfTestPaperConstant.SELECT_REFERRAL_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfDiagnosisResult>> selectAllDiagnosis(Long idTestexecResult) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    PfTestPaperBeanUtil.convertZdList(pfTestWaitingRoomService.selectAllDiagnosis(idTestexecResult)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectAllDiagnosis】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectAllDiagnosis-error】查询诊断出错, idTestexecResult:{}", idTestexecResult, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_DIAGNOSIS_ERROR, PfTestPaperConstant.SELECT_DIAGNOSIS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<PfWaitingRoomDiagnosisResult> selectDiagnosis(ExmMedResultDiagnosisParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfTestWaitingRoomService.selectDiagnosis(BeanUtil.convert(param, ExmMedResultDiagnosis.class)), PfWaitingRoomDiagnosisResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectDiagnosis】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectDiagnosis-error】查询诊断出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_DIAGNOSIS_ERROR, PfTestPaperConstant.SELECT_DIAGNOSIS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfWaitingRoomDiagnosisResult>> listDiagnosis(ExmMedResultDiagnosisParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                     BeanUtil.convertList(pfTestWaitingRoomService.listDiagnosis(BeanUtil.convert(param, ExmMedResultDiagnosis.class)),
                             PfWaitingRoomDiagnosisResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listDiagnosis】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listDiagnosis-error】查询初步诊断列表出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_DIAGNOSIS_ERROR, "查询初步诊断列表出错"));
        }
    }

    @Override
    public CommonResult<ExmMedResultSummaryResult> selectSummary(Long idTestexecResult) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfTestWaitingRoomService.selectSummary(idTestexecResult), ExmMedResultSummaryResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectSummary】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectSummary-error】查询诊断小结出错, idTestexecResult:{}", idTestexecResult, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_DIAGNOSIS_ERROR, PfTestPaperConstant.SELECT_DIAGNOSIS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfWaitingRoomDieReasonResult>> listReadyDieReason(Long idTestexecResult, String keyword) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.listReadyDieReason(idTestexecResult, keyword), PfWaitingRoomDieReasonResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listReadyDieReason】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listReadyDieReason-error】查询确诊理由字典出错, idTestexecResult:{}", idTestexecResult, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_READY_DIE_REASON_ERROR, PfTestPaperConstant.SELECT_READY_DIE_REASON_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listDieReason(Long idTestexecResultDiagnosis) {
        try {
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfTestWaitingRoomService.listDieReason(idTestexecResultDiagnosis), PfWaitingRoomDieReasonResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listDieReason-error】查询确诊理由列表出错，idTestexecResultDiagnosis:{}", idTestexecResultDiagnosis, e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.LIST_DIE_REASON_ERROR, PfTestPaperConstant.LIST_DIE_REASON_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<List<PfEvaExecResult>> selectScore(Long idTestexecResult, Long idMedicalrec) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.selectScore(idTestexecResult, idMedicalrec), PfEvaExecResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectScore】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectScore-error】查询评估得分出错, idTestexecResult:{}", idTestexecResult, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_EVA_ERROR, PfTestPaperConstant.LIST_EVA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfEvaExecResult>> listEva(PfTestEvaParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.listEva(BeanUtil.convert(param, PfTestEvaDto.class)), PfEvaExecResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listEva】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listEva-error】查询评估列表出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_EVA_ERROR, PfTestPaperConstant.LIST_EVA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<ExmEvaLogResult>> listEvaLog(Long idTestexecResult) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.listEvaLog(idTestexecResult), ExmEvaLogResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listEvaLog】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listEvaLog-error】查询评估日志出错, idTestexecResult:{}", idTestexecResult, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_EVA_ERROR, PfTestPaperConstant.LIST_EVA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> medEva(Long idTestexecResult) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.medEva(idTestexecResult));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-medEva】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-medEva-error】病例评估出错, idTestexecResult:{}", idTestexecResult, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.MED_EVA_ERROR, PfTestPaperConstant.MED_EVA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editEva(ExmEvaDimensionParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.editEva(BeanUtil.convert(param, ExmEvaDimension.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-editEva】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-editEva-error】编辑得分出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.EDIT_EVA_ERROR, PfTestPaperConstant.EDIT_EVA_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfExecLogResult>> listExecLog(Long idTestexecResult) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.listExecLog(idTestexecResult), PfExecLogResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listExecLog】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listExecLog-error】查询病例执行日志出错, idTestexecResult:{}", idTestexecResult, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_EXEC_LOG_ERROR, PfTestPaperConstant.LIST_EXEC_LOG_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<ExmEvaResultResult> selectEvaResult(Long idTestexecResult) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfTestWaitingRoomService.selectEvaResult(idTestexecResult), ExmEvaResultResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectEvaResult】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectEvaResult-error】查询病例执行结果出错, idTestexecResult:{}", idTestexecResult, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_EVA_RESULT_ERROR, PfTestPaperConstant.SELECT_EVA_RESULT_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> saveReferralReason(List<ExmMedResultReferralReasonParam> params) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveReferralReason(BeanUtil.convertList(params, ExmMedResultReferralReason.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveReferralReason】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveReferralReason-error】保存拟诊原因出错, param:{}", params.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_REFERRAL_REASON_ERROR, PfTestPaperConstant.SAVE_REFERRAL_REASON_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listReferralReason(Long idTestexecResultReferral) {
        try {
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfTestWaitingRoomService.listReferralReason(idTestexecResultReferral), PfReferralReasonResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listDieReason-error】查询拟诊理由列表出错，idTestexecResultReferral:{}", idTestexecResultReferral, e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.LIST_REFERRAL_REASON_ERROR, PfTestPaperConstant.LIST_REFERRAL_REASON_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> delPlanDetail(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.delPlanDetail(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPlanFacadeImpl-delPlanDetail】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPlanFacadeImpl-delPlanDetail】删除计划详情失败, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.DEL_PLAN_DETAIL_ERROR, PfTestPaperConstant.DEL_PLAN_DETAIL_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> saveExecSerialNo(ExmTestexecParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestWaitingRoomService.saveExecSerialNo(BeanUtil.convert(param, ExmTestexec.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-saveExecSerialNo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-saveExecSerialNo-error】保存执行序号出错, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_EXEC_SERIAL_NO_ERROR, PfTestPaperConstant.SAVE_EXEC_SERIAL_NO_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listAllReferralDie(Long idTestexecResult, String keywords) {
        try {
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfTestWaitingRoomService.listAllReferralDie(idTestexecResult, keywords), BasDieResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listAllReferralDie-error】获取拟诊疾病列表失败，param:{}", idTestexecResult, e);
            return PfResultFactory.initPageResultWithError(
                    PfDiseaseConstant.PAGE_DISEASE_INFO_LIST_ERROR, PfDiseaseConstant.PAGE_DISEASE_INFO_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<List<PfDiagnosticAnalysisResult>> listDiagnosticAnalysis(PfTestEvaParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.listDiagnosticAnalysis(
                            BeanUtil.convert(param, PfTestEvaDto.class)
                    ), PfDiagnosticAnalysisResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listDiagnosticAnalysis】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listDiagnosticAnalysis-error】查询确诊项、排除拟诊项失败, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_DIAGNOSTIC_ANALYSIS_ERROR, PfTestPaperConstant.LIST_DIAGNOSTIC_ANALYSIS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfDiagnosticAnalysisDetailResult>> listDiagnosticAnalysisDetail(PfTestEvaParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.listDiagnosticAnalysisDetail(
                            BeanUtil.convert(param, PfTestEvaDto.class)
                    ), PfDiagnosticAnalysisDetailResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listDiagnosticAnalysisDetail】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listDiagnosticAnalysisDetail-error】查询病例诊断分析详情失败, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_DIAGNOSTIC_ANALYSIS_DETAIL_ERROR, PfTestPaperConstant.LIST_DIAGNOSTIC_ANALYSIS_DETAIL_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfDiseaseZtreeResult>> listDiseaseCatalogueTree(PfCatalogueTreeParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestWaitingRoomService.listDiseaseCatalogueTree(
                            BeanUtil.convert(param, PfCatalogueTreeDto.class)), PfDiseaseZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-listDiseaseCatalogueTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listDiseaseCatalogueTree】编辑疾病信息失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDiseaseConstant.LIST_DISEASE_CATALOGUE_TREE_ERROR, PfDiseaseConstant.LIST_DISEASE_CATALOGUE_TREE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<String> selectReferralChartData(PfTestEvaParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfTestWaitingRoomService.selectReferralChartData(
                    BeanUtil.convert(param, PfTestEvaDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectReferralChartData】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectReferralChartData-error】查询思维导图失败, param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_DIAGNOSTIC_ANALYSIS_DETAIL_ERROR, "查询思维导图失败"));
        }
    }

    @Override
    public PfPageResult listDiagnosticChart(PfTestExamTagParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfTestExamTagDto dto = BeanUtil.convert(param, PfTestExamTagDto.class);

            return PfResultFactory.initPagePfResultWithSuccess(pfTestWaitingRoomService.countDiagnosticChart(dto),
                    BeanUtil.convertList(pfTestWaitingRoomService.listDiagnosticChart(dto),
                            PfWaitingRoomChartDetailResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listDiagnosticChart-error】获取诊断分析、鉴别诊断列表，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfDiseaseConstant.PAGE_DISEASE_INFO_LIST_ERROR, PfDiseaseConstant.PAGE_DISEASE_INFO_LIST_ERROR_MSG);
        }
    }

    @Override
    public PfPageResult listEvaDimension(Long idTestexecResultDimension) {
        try {
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfTestWaitingRoomService.listEvaDimension(idTestexecResultDimension),
                            PfWaitingRoomDimensionResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-listEvaDimension-error】获取评估维度明细列表失败，param:{}", idTestexecResultDimension, e);
            return PfResultFactory.initPageResultWithError(
                    PfDiseaseConstant.PAGE_DISEASE_INFO_LIST_ERROR, "获取评估维度明细列表失败");
        }
    }

    @Override
    public CommonResult<String> selectIdStr(PfTestExamTagParam param) {
        try {
            PfTestExamTagDto dto = BeanUtil.convert(param, PfTestExamTagDto.class);
            return ResultFactory.initCommonResultWithSuccess(pfTestWaitingRoomService.selectIdStr(dto));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectIdStr-error】获取诊断分析、鉴别诊断线索，param:{}", param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDiseaseConstant.PAGE_DISEASE_INFO_LIST_ERROR, "获取诊断分析、鉴别诊断线索失败"));
        }
    }

    @Override
    public CommonResult<PfWaitingRoomFinishResult> selectFinishExamInfo(Long idTestplanDetail) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfTestWaitingRoomService.selectFinishExamInfo(idTestplanDetail), PfWaitingRoomFinishResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectFinishExamInfo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectFinishExamInfo-error】获取考试完成后所需跳转信息, idTestplanDetail:{}", idTestplanDetail, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_EVA_RESULT_ERROR, "获取考试完成后所需跳转信息失败"));
        }
    }

    @Override
    public CommonResult<Long> selectAssessPatIdMedCase(Long idTestplanDetail) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfTestWaitingRoomService.selectAssessPatIdMedCase(idTestplanDetail));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectAssessPatIdMedCase】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectAssessPatIdMedCase-error】患者页签idMedCase, idTestplanDetail:{}", idTestplanDetail, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_EVA_RESULT_ERROR, "获取患者页签idMedCase失败"));
        }
    }

    @Override
    public CommonResult<String> selectEvaGuideContent(Long idTestplanDetail) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfTestWaitingRoomService.selectEvaGuideContent(idTestplanDetail));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestWaitingRoomFacadeImpl-selectEvaGuideContent】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestWaitingRoomFacadeImpl-selectEvaGuideContent-error】查询病例评估指南, idTestplanDetail:{}", idTestplanDetail, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SELECT_EVA_RESULT_ERROR, "获取查询病例评估指南失败"));
        }
    }


}
