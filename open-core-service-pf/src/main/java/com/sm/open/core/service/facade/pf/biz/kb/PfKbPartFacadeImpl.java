package com.sm.open.core.service.facade.pf.biz.kb;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.kb.part.*;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCommonListParam;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.*;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.kb.PfKbPartFacade;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfMedCaseDto;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfPartCommonDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.service.service.pf.biz.kb.PfKbPartService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfKbPartFacade")
public class PfKbPartFacadeImpl implements PfKbPartFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfKbPartFacadeImpl.class);

    @Resource
    private PfKbPartService pfKbPartService;

    @Override
    public PfPageResult listKbPart(PfMedCaseParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfMedCaseDto dto = BeanUtil.convert(param, PfMedCaseDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfKbPartService.countKbPart(dto),
                    BeanUtil.convertList(pfKbPartService.listKbPart(dto), FaqMedCaseResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listKbPart-error】获取病例组件用例列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbPartConstant.PAGE_KB_PART_LIST_ERROR, PfKbPartConstant.PAGE_KB_PART_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> addKbPart(FaqMedCaseParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.addKbPart(BeanUtil.convert(param, FaqMedCase.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-addKbPart】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-addKbPart】新增病例组件用例失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.ADD_KB_PART_ERROR, PfKbPartConstant.ADD_KB_PART_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editKbPart(FaqMedCaseParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.editKbPart(BeanUtil.convert(param, FaqMedCase.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-editKbPart】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-editKbPart】编辑病例组件用例失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.EDIT_KB_PART_ERROR, PfKbPartConstant.EDIT_KB_PART_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delKbPart(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.delKbPart(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-delKbPart】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-delKbPart】删除病例组件用例失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.DEL_KB_PART_ERROR, PfKbPartConstant.DEL_KB_PART_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listFaqMedCaseInques(PfPartCommonParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfPartCommonDto dto = BeanUtil.convert(param, PfPartCommonDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfKbPartService.countFaqMedCaseInques(dto),
                    BeanUtil.convertList(pfKbPartService.listFaqMedCaseInques(dto), FaqMedCaseInquesListResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listFaqMedCaseInques-error】获取问诊问题明细列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbPartConstant.LIST_FAQ_MED_CASE_INQUES_ERROR, PfKbPartConstant.LIST_FAQ_MED_CASE_INQUES_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> saveFaqMedCaseInques(FaqMedCaseInquesListParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.saveFaqMedCaseInques(BeanUtil.convert(param, FaqMedCaseInquesList.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-saveFaqMedCaseInques】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-saveFaqMedCaseInques】保存问诊问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SAVE_FAQ_MED_CASE_INQUES_ERROR, PfKbPartConstant.SAVE_FAQ_MED_CASE_INQUES_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delFaqMedCaseInques(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.delFaqMedCaseInques(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-delFaqMedCaseInques】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-delFaqMedCaseInques】删除问诊问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.DEL_FAQ_MED_CASE_INQUES_ERROR, PfKbPartConstant.DEL_FAQ_MED_CASE_INQUES_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<FaqMedCaseInquesListResult> resetKbCons(FaqMedCaseInquesListParam param) {
        try {
            Assert.isTrue(param.getIdMedCaseList() != null, "idMedCaseList");
            Assert.isTrue(param.getIdInques() != null, "idInques");
            Assert.isTrue(param.getIdAnswer() != null, "idAnswer");

            FaqMedCaseInquesList dto = BeanUtil.convert(param, FaqMedCaseInquesList.class);

            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfKbPartService.resetKbCons(dto), FaqMedCaseInquesListResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-resetKbCons】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-resetKbCons】重载咨询问题失败,  param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.RESET_KB_CONS_ERROR, PfKbPartConstant.RESET_KB_CONS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> saveKbText(FaqMedCaseTextParam param) {
        try {
            Assert.isTrue(param.getIdMedCase() != null, "idMedCase");
            Assert.isTrue(StringUtils.isNotBlank(param.getContent()), "content");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.saveKbText(BeanUtil.convert(param, FaqMedCaseText.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-saveKbText】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-saveKbText】保存文本失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SAVE_KB_TEXT_ERROR, PfKbPartConstant.SAVE_KB_TEXT_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<FaqMedCaseTextResult> selectKbText(Long idMedCase) {
        try {
            Assert.isTrue(idMedCase != null, "idMedCase");
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfKbPartService.selectKbText(idMedCase), FaqMedCaseTextResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-selectKbText】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-selectKbText】查询文本失败, idMedCase:" + idMedCase, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SELECT_KB_TEXT_ERROR, PfKbPartConstant.SELECT_KB_TEXT_ERROR_MSG));
        }
    }


    @Override
    public CommonResult<Boolean> saveKbPic(FaqMedCasePicParam param) {
        try {
            Assert.isTrue(param.getIdMedCase() != null, "idMedCase");
            Assert.isTrue(param.getIdMedia() != null, "idMedia");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.saveKbPic(BeanUtil.convert(param, FaqMedCasePic.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-saveKbPic】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-saveKbPic】保存图片失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SAVE_KB_PIC_ERROR, PfKbPartConstant.SAVE_KB_PIC_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<FaqMedCasePicResult> selectKbPic(Long idMedCase) {
        try {
            Assert.isTrue(idMedCase != null, "idMedCase");
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfKbPartService.selectKbPic(idMedCase), FaqMedCasePicResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-selectKbPic】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-selectKbPic】查询图片失败, idMedCase:" + idMedCase, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SELECT_KB_PIC_ERROR, PfKbPartConstant.SELECT_KB_PIC_ERROR_MSG));
        }

    }

    @Override
    public CommonResult<Boolean> saveKbPat(FaqMedCasePatientParam param) {
        try {
            Assert.isTrue(param.getIdMedCase() != null, "idMedCase");
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            Assert.isTrue(param.getAge() != 0, "age");
            Assert.isTrue(StringUtils.isNotBlank(param.getComplaint()), "complaint");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.saveKbPat(BeanUtil.convert(param, FaqMedCasePatient.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-saveKbPat】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-saveKbPat】保存患者信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SAVE_KB_PAT_ERROR, PfKbPartConstant.SAVE_KB_PAT_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<FaqMedCasePatientResult> selectKbPat(Long idMedCase) {
        try {
            Assert.isTrue(idMedCase != null, "idMedCase");
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfKbPartService.selectKbPat(idMedCase), FaqMedCasePatientResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-selectKbPat】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-selectKbPat】查询患者信息失败, idMedCase:" + idMedCase, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SELECT_KB_PAT_ERROR, PfKbPartConstant.SELECT_KB_PAT_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult<FaqMedCaseInspectListResult> listExams(PfPartCommonParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfPartCommonDto dto = BeanUtil.convert(param, PfPartCommonDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfKbPartService.countExams(dto),
                    BeanUtil.convertList(pfKbPartService.listExams(dto), FaqMedCaseInspectListResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listExams-error】获取检验定义列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbPartConstant.LIST_EXAM_ERROR, PfKbPartConstant.LIST_EXAM_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> saveExam(FaqMedCaseInspectListParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.saveExam(BeanUtil.convert(param, FaqMedCaseInspectList.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-saveExam】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-saveExam】保存检验定义失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SAVE_KB_EXAM_ERROR, PfKbPartConstant.SAVE_KB_EXAM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delKbExam(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.delKbExam(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-delKbExam】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-delKbExam】删除检验项目失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.DEL_KB_EXAM_ERROR, PfKbPartConstant.DEL_KB_EXAM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<FaqMedCaseInspectListResult> resetKbExam(FaqMedCaseInspectListParam param) {
        try {
            Assert.isTrue(param.getIdMedCaseList() != null, "idMedCaseList");
            Assert.isTrue(param.getIdInspectItem() != null, "idInspectItem");
            Assert.isTrue(param.getIdResult() != null, "idResult");

            FaqMedCaseInspectList dto = BeanUtil.convert(param, FaqMedCaseInspectList.class);

            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfKbPartService.resetKbExam(dto), FaqMedCaseInspectListResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-resetKbExam】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-resetKbExam】重载检验项目失败,  param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.RESET_KB_EXAM_ERROR, PfKbPartConstant.RESET_KB_EXAM_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listChecks(PfPartCommonParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfPartCommonDto dto = BeanUtil.convert(param, PfPartCommonDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfKbPartService.countChecks(dto),
                    BeanUtil.convertList(pfKbPartService.listChecks(dto), FaqMedCaseBodyListResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-listChecks-error】获取检查列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfKbPartConstant.LIST_FAQ_MED_CASE_INQUES_ERROR, PfKbPartConstant.LIST_FAQ_MED_CASE_INQUES_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> saveCheck(FaqMedCaseBodyListparam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.saveCheck(BeanUtil.convert(param, FaqMedCaseBodyList.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-saveCheck】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-saveCheck】保存检查定义失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SAVE_CHECK_ERROR, PfKbPartConstant.SAVE_CHECK_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delKbCheck(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.delKbCheck(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-delKbCheck】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-delKbCheck】删除检查失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.DEL_KB_CHECK_ERROR, PfKbPartConstant.DEL_KB_CHECK_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<FaqMedCaseBodyListResult> resetKbCheck(FaqMedCaseBodyListparam param) {
        try {
            Assert.isTrue(param.getIdMedCaseList() != null, "idMedCaseList");
            Assert.isTrue(param.getIdMedCase() != null, "idMedCase");
            Assert.isTrue(param.getIdBody() != null, "idBody");

            FaqMedCaseBodyList dto = BeanUtil.convert(param, FaqMedCaseBodyList.class);

            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfKbPartService.resetKbCheck(dto), FaqMedCaseBodyListResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-resetKbCheck】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-resetKbCheck】重载检查定义失败,  param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.RESET_KB_CHECK_ERROR, PfKbPartConstant.RESET_KB_CHECK_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> saveFaqMedCaseBody(FaqMedCaseBodyParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.saveFaqMedCaseBody(BeanUtil.convert(param, FaqMedCaseBody.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-saveFaqMedCaseBody】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-saveFaqMedCaseBody】保存检查定义图片失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SAVE_FAQ_MED_CASE_BODY_ERROR, PfKbPartConstant.SAVE_FAQ_MED_CASE_BODY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<FaqMedCaseBodyResult> selectFaqMedCaseBody(Long idMedCase) {
        try {

            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfKbPartService.selectFaqMedCaseBody(idMedCase), FaqMedCaseBodyResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-selectFaqMedCaseBody】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-selectFaqMedCaseBody】查询检查定义图片失败, idMedCase:" + idMedCase, e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.SELECT_FAQ_MED_CASE_BODY_ERROR, PfKbPartConstant.SELECT_FAQ_MED_CASE_BODY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> bachAddCons(PfCommonListParam param) {
        try {
            if (param.getExtType().equals(YesOrNoNum.NO.getCode())) {
                Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            }
            Assert.isTrue(param.getExtId() != null, "extId");
            Assert.isTrue(StringUtils.isNotBlank(param.getExtType()), "extType");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.bachAddCons(BeanUtil.convert(param, PfCommonListDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-bachAddCons】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-bachAddCons】批量添加问诊问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.BACH_ADD_CONS_ERROR, PfKbPartConstant.BACH_ADD_CONS_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> bachAddCheck(PfCommonListParam param) {
        try {
            if (param.getExtType().equals(YesOrNoNum.NO.getCode())) {
                Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            }
            Assert.isTrue(param.getExtId() != null, "extId");
            Assert.isTrue(StringUtils.isNotBlank(param.getExtType()), "extType");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.bachAddCheck(BeanUtil.convert(param, PfCommonListDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-bachAddCheck】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-bachAddCheck】批量添加体格检查失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.BACH_ADD_CHECK_ERROR, PfKbPartConstant.BACH_ADD_CHECK_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> bachAddExam(PfCommonListParam param) {
        try {
            if (param.getExtType().equals(YesOrNoNum.NO.getCode())) {
                Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            }
            Assert.isTrue(param.getExtId() != null, "extId");
            Assert.isTrue(StringUtils.isNotBlank(param.getExtType()), "extType");
            return ResultFactory.initCommonResultWithSuccess(
                    pfKbPartService.bachAddExam(BeanUtil.convert(param, PfCommonListDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfKbPartFacadeImpl-bachAddExam】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfKbPartFacadeImpl-bachAddExam】批量添加辅助检查失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfKbPartConstant.BACH_ADD_EXAM_ERROR, PfKbPartConstant.BACH_ADD_EXAM_ERROR_MSG));
        }
    }
}
