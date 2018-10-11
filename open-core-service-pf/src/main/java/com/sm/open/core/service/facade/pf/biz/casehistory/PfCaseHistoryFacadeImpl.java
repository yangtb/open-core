package com.sm.open.core.service.facade.pf.biz.casehistory;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.casehistory.FaqMedicalrecCaParam;
import com.sm.open.core.facade.model.param.pf.biz.casehistory.FaqMedicalrecParam;
import com.sm.open.core.facade.model.param.pf.biz.casehistory.PfCaseHistoryParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.casehistory.FaqMedicalrecResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.casehistory.PfCaseHistoryFacade;
import com.sm.open.core.model.dto.pf.biz.casehistory.PfCaseHistoryDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.FaqMedicalrec;
import com.sm.open.core.model.entity.FaqMedicalrecCa;
import com.sm.open.core.service.service.pf.biz.casehistory.PfCaseHistoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfCaseHistoryFacade")
public class PfCaseHistoryFacadeImpl implements PfCaseHistoryFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfCaseHistoryFacadeImpl.class);

    @Resource
    private PfCaseHistoryService pfCaseHistoryService;

    @Override
    public CommonResult<List<PfCommonZtreeResult>> listClassifyTree() {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfCaseHistoryService.listClassifyTree(), PfCommonZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCaseHistoryFacadeImpl-listClassifyTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCaseHistoryFacadeImpl-listClassifyTree】查询模板分类tree失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCaseHistoryConstant.LIST_CASEHISTORY_TEMPLATE_CLASSIFY_TREE_ERROR,
                    PfCaseHistoryConstant.LIST_CASEHISTORY_TEMPLATE_CLASSIFY_TREE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> addClassify(FaqMedicalrecCaParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCaseHistoryService.addClassify(BeanUtil.convert(param, FaqMedicalrecCa.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCaseHistoryFacadeImpl-addClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCaseHistoryFacadeImpl-addClassify】新增模板分类信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCaseHistoryConstant.ADD_CASEHISTORY_TEMPLATE_CLASSIFY_ERROR,
                    PfCaseHistoryConstant.ADD_CASEHISTORY_TEMPLATE_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editClassify(FaqMedicalrecCaParam param) {
        try {
            Assert.isTrue(param.getIdMedicalrecCa() != null, "idMedicalrecCa");
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCaseHistoryService.editClassify(BeanUtil.convert(param, FaqMedicalrecCa.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCaseHistoryFacadeImpl-editClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCaseHistoryFacadeImpl-editClassify】编辑模板分类信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCaseHistoryConstant.EDIT_CASEHISTORY_TEMPLATE_CLASSIFY_ERROR,
                    PfCaseHistoryConstant.EDIT_CASEHISTORY_TEMPLATE_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delClassify(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfCaseHistoryService.delClassify(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCaseHistoryFacadeImpl-delClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCaseHistoryFacadeImpl-delClassify】删除模板分类失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCaseHistoryConstant.DEL_CASEHISTORY_TEMPLATE_CLASSIFY_ERROR,
                    PfCaseHistoryConstant.DEL_CASEHISTORY_TEMPLATE_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listTemplate(PfCaseHistoryParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfCaseHistoryDto dto = BeanUtil.convert(param, PfCaseHistoryDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfCaseHistoryService.countTemplate(dto),
                    BeanUtil.convertList(pfCaseHistoryService.listTemplate(dto), FaqMedicalrecResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfCaseHistoryFacadeImpl-listTemplate-error】获取模板列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfCaseHistoryConstant.PAGE_CASEHISTORY_TEMPLATELIST_ERROR, PfCaseHistoryConstant.PAGE_CASEHISTORY_TEMPLATELIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addTemplate(FaqMedicalrecParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(param.getIdDemo() != null, "idDemo");
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCaseHistoryService.addTemplate(BeanUtil.convert(param, FaqMedicalrec.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCaseHistoryFacadeImpl-addTemplate】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCaseHistoryFacadeImpl-addTemplate】新增模板失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCaseHistoryConstant.ADD_CASEHISTORY_TEMPLATE_ERROR, PfCaseHistoryConstant.ADD_CASEHISTORY_TEMPLATE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editTemplate(FaqMedicalrecParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(param.getIdDemo() != null, "idDemo");
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            Assert.isTrue(param.getIdMedicalrec() != null, "idMedicalrec");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCaseHistoryService.editTemplate(BeanUtil.convert(param, FaqMedicalrec.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCaseHistoryFacadeImpl-editTemplate】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCaseHistoryFacadeImpl-editTemplate】编辑模板失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCaseHistoryConstant.EDIT_CASEHISTORY_TEMPLATE_ERROR, PfCaseHistoryConstant.EDIT_CASEHISTORY_TEMPLATE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delTemplate(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCaseHistoryService.delTemplate(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCaseHistoryFacadeImpl-delTemplate】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCaseHistoryFacadeImpl-delTemplate】删除模板失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCaseHistoryConstant.DEL_CASEHISTORY_TEMPLATE_ERROR, PfCaseHistoryConstant.DEL_CASEHISTORY_TEMPLATE_ERROR_MSG));
        }
    }
}
