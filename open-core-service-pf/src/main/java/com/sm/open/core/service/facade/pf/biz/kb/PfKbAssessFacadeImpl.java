package com.sm.open.core.service.facade.pf.biz.kb;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.kb.assess.*;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.kb.assess.*;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.kb.PfKbAssessFacade;
import com.sm.open.core.model.dto.pf.biz.kb.assess.*;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.service.service.pf.biz.kb.PfKbAssessService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfKbAssessFacade")
public class PfKbAssessFacadeImpl implements PfKbAssessFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfKbAssessFacadeImpl.class);

    @Resource
    private PfKbAssessService pfKbAssessService;

    @Override
    public PfPageResult listKbAssess(PfEvaCaseParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfEvaCaseDto dto = BeanUtil.convert(param, PfEvaCaseDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfKbAssessService.countKbAssess(dto),
                    BeanUtil.convertList(pfKbAssessService.listKbAssess(dto), FaqEvaCaseResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listKbAssess-error】获取评估组件用例列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbAssessConstant.PAGE_KB_ASSESS_LIST_ERROR, PfKbAssessConstant.PAGE_KB_ASSESS_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> saveKbAssess(FaqEvaCaseParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.saveKbAssess(BeanUtil.convert(param, FaqEvaCase.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-saveKbAssess】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-saveKbAssess】保存评估组件用例失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.ADD_KB_ASSESS_ERROR, PfKbAssessConstant.ADD_KB_ASSESS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delKbAssess(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.delKbAssess(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-delKbAssess】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-delKbAssess】删除评估组件用例失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.DEL_KB_ASSESS_ERROR, PfKbAssessConstant.DEL_KB_ASSESS_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listKbReferral(PfAssessCommonParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfKbAssessService.listKbReferral(dto), FaqEvaCaseItemResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listKbReferral-error】获取拟诊列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbAssessConstant.LIST_KB_REFERRAL_ERROR, PfKbAssessConstant.LIST_KB_REFERRAL_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<List<FaqEvaCaseItemReferralResult>> listReferral(PfAssessCommonParam param) {
        try {
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfKbAssessService.listReferral(dto), FaqEvaCaseItemReferralResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-listReferral】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listReferral】获取评估项-拟诊列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.LIST_REFERRAL_ERROR, PfKbAssessConstant.LIST_REFERRAL_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delReferral(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.delReferral(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-delReferral】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-delReferral】删除评估项-拟诊失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.DEL_REFERRAL_ERROR, PfKbAssessConstant.DEL_REFERRAL_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveReferral(PfAssessReferralParam param) {
        try {
            List<FaqEvaCaseItemReferral> list = BeanUtil.convertList(param.getList(), FaqEvaCaseItemReferral.class);
            PfAssessReferralDto dto = BeanUtil.convert(param, PfAssessReferralDto.class);
            dto.setList(list);
            return ResultFactory.initCommonResultWithSuccess(pfKbAssessService.saveReferral(dto));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-saveReferral】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-saveReferral】保存评估项-拟诊失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.SAVE_REFERRAL_ERROR, PfKbAssessConstant.SAVE_REFERRAL_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listKbDiagnosis(PfAssessCommonParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfKbAssessService.listKbDiagnosis(dto), FaqEvaCaseItemResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listKbDiagnosis-error】获取评估项-确诊列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbAssessConstant.LIST_KB_DIAGNOSIS_ERROR, PfKbAssessConstant.LIST_KB_DIAGNOSIS_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<List<FaqEvaCaseItemDiagnosisResult>> listDiagnosisAnswer(PfAssessCommonParam param) {
        try {
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfKbAssessService.listDiagnosisAnswer(dto), FaqEvaCaseItemDiagnosisResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-listDiagnosisAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listDiagnosisAnswer】获取确诊列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.LIST_DIAGNOSIS_ERROR, PfKbAssessConstant.LIST_DIAGNOSIS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delDiagnosis(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.delDiagnosis(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-delDiagnosis】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-delDiagnosis】删除确诊列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.DEL_DIAGNOSIS_ERROR, PfKbAssessConstant.DEL_DIAGNOSIS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveDiagnosis(PfAssessDiagnosisParam param) {
        try {
            List<FaqEvaCaseItemDiagnosis> list = BeanUtil.convertList(param.getList(), FaqEvaCaseItemDiagnosis.class);
            PfAssessDiagnosisDto dto = BeanUtil.convert(param, PfAssessDiagnosisDto.class);
            dto.setList(list);
            return ResultFactory.initCommonResultWithSuccess(pfKbAssessService.saveDiagnosis(dto));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-saveDiagnosis】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-saveDiagnosis】保存确诊列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.SAVE_DIAGNOSIS_ERROR, PfKbAssessConstant.SAVE_DIAGNOSIS_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listKbReason(PfAssessCommonParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfKbAssessService.listKbReason(dto), FaqEvaCaseItemResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listKbReason-error】获取评估项-确诊理由列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbAssessConstant.LIST_KB_REASON_ERROR, PfKbAssessConstant.LIST_KB_REASON_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<List<FaqEvaCaseItemReasonResult>> listReasonAnswer(PfAssessCommonParam param) {
        try {
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfKbAssessService.listReasonAnswer(dto), FaqEvaCaseItemReasonResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-listReasonAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listReasonAnswer】获取确诊理由列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.LIST_REASON_ERROR, PfKbAssessConstant.LIST_REASON_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delReason(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.delReason(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-delReason】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-delReason】删除确诊理由列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.DEL_REASON_ERROR, PfKbAssessConstant.DEL_REASON_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveReason(PfAssessReasonParam param) {
        try {
            List<FaqEvaCaseItemReason> list = BeanUtil.convertList(param.getList(), FaqEvaCaseItemReason.class);
            PfAssessReasonDto dto = BeanUtil.convert(param, PfAssessReasonDto.class);
            dto.setList(list);
            return ResultFactory.initCommonResultWithSuccess(pfKbAssessService.saveReason(dto));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-saveReason】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-saveReason】保存确诊理由列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.SAVE_REASON_ERROR, PfKbAssessConstant.SAVE_REASON_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listKbCover(PfAssessCommonParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfKbAssessService.listKbCover(dto), FaqEvaCaseItemResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listKbCover-error】获取评估项-鉴定检查列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbAssessConstant.LIST_KB_COVER_ERROR, PfKbAssessConstant.LIST_KB_COVER_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<List<FaqEvaCaseItemCoverResult>> listCoverAnswer(PfAssessCommonParam param) {
        try {
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfKbAssessService.listCoverAnswer(dto), FaqEvaCaseItemCoverResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-listCoverAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listCoverAnswer】获取鉴定检查列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.LIST_COVER_ERROR, PfKbAssessConstant.LIST_COVER_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delCover(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.delCover(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-delCover】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-delCover】删除鉴定检查列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.DEL_COVER_ERROR, PfKbAssessConstant.DEL_COVER_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveCover(PfAssessCoverParam param) {
        try {
            List<FaqEvaCaseItemCover> list = BeanUtil.convertList(param.getList(), FaqEvaCaseItemCover.class);
            PfAssessCoverDto dto = BeanUtil.convert(param, PfAssessCoverDto.class);
            dto.setList(list);
            return ResultFactory.initCommonResultWithSuccess(pfKbAssessService.saveCover(dto));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-saveCover】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-saveCover】保存鉴定检查列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.SAVE_COVER_ERROR, PfKbAssessConstant.SAVE_COVER_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listKbMust(PfAssessCommonParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfKbAssessService.listKbMust(dto), FaqEvaCaseItemResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listKbMust-error】获取评估项-覆盖必须检查列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbAssessConstant.LIST_KB_MUST_ERROR, PfKbAssessConstant.LIST_KB_MUST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<List<FaqEvaCaseItemMustResult>> listMustAnswer(PfAssessCommonParam param) {
        try {
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfKbAssessService.listMustAnswer(dto), FaqEvaCaseItemMustResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-listMustAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listMustAnswer】获取覆盖必须检查列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.LIST_MUST_ERROR, PfKbAssessConstant.LIST_MUST_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delMust(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.delMust(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-delMust】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-delMust】删除覆盖必须检查列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.DEL_MUST_ERROR, PfKbAssessConstant.DEL_MUST_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveMust(PfAssessMustParam param) {
        try {
            List<FaqEvaCaseItemMust> list = BeanUtil.convertList(param.getList(), FaqEvaCaseItemMust.class);
            PfAssessMustDto dto = BeanUtil.convert(param, PfAssessMustDto.class);
            dto.setList(list);
            return ResultFactory.initCommonResultWithSuccess(pfKbAssessService.saveMust(dto));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-saveMust】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-saveMust】保存覆盖必须检查列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.SAVE_MUST_ERROR, PfKbAssessConstant.SAVE_MUST_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listKbEffciency(PfAssessCommonParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfKbAssessService.listKbEffciency(dto), FaqEvaCaseItemResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listKbEffciency-error】获取评估项-检查效率列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbAssessConstant.LIST_KB_EFFCIENCY_ERROR, PfKbAssessConstant.LIST_KB_EFFCIENCY_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> delEffciency(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.delEffciency(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-delEffciency】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-delEffciency】删除检查效率列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.DEL_EFFCIENCY_ERROR, PfKbAssessConstant.DEL_EFFCIENCY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveEffciency(PfAssessEffciencyParam param) {
        try {
            PfAssessEffciencyDto dto = BeanUtil.convert(param, PfAssessEffciencyDto.class);
            return ResultFactory.initCommonResultWithSuccess(pfKbAssessService.saveEffciency(dto));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-saveEffciency】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-saveEffciency】保存检查效率列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.SAVE_EFFCIENCY_ERROR, PfKbAssessConstant.SAVE_EFFCIENCY_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listKbOrder(PfAssessCommonParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfKbAssessService.listKbOrder(dto), FaqEvaCaseItemResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listKbOrder-error】获取评估项-临时医嘱用药列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbAssessConstant.LIST_KB_ORDER_ERROR, PfKbAssessConstant.LIST_KB_ORDER_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<List<FaqEvaCaseItemOrderResult>> listOrderAnswer(PfAssessCommonParam param) {
        try {
            PfAssessCommonDto dto = BeanUtil.convert(param, PfAssessCommonDto.class);
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfKbAssessService.listOrderAnswer(dto), FaqEvaCaseItemOrderResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-listOrderAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-listOrderAnswer】获取覆盖必须检查列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.LIST_ORDER_ERROR, PfKbAssessConstant.LIST_ORDER_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delOrder(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.delOrder(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-delOrder】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-delOrder】删除临时医嘱用药列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.DEL_ORDER_ERROR, PfKbAssessConstant.DEL_ORDER_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveOrder(PfAssessOrderParam param) {
        try {
            List<FaqEvaCaseItemOrder> list = BeanUtil.convertList(param.getList(), FaqEvaCaseItemOrder.class);
            PfAssessOrderDto dto = BeanUtil.convert(param, PfAssessOrderDto.class);
            dto.setList(list);
            return ResultFactory.initCommonResultWithSuccess(pfKbAssessService.saveOrder(dto));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-saveOrder】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-saveOrder】保存临时医嘱用药列表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.SAVE_ORDER_ERROR, PfKbAssessConstant.SAVE_ORDER_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delCommonAssess(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbAssessService.delCommonAssess(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbAssessFacadeImpl-delCommonAssess】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbAssessFacadeImpl-delCommonAssess】删除评估阶段失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbAssessConstant.DEL_COMMON_ASSESS_ERROR, PfKbAssessConstant.DEL_COMMON_ASSESS_ERROR_MSG));
        }
    }

}
