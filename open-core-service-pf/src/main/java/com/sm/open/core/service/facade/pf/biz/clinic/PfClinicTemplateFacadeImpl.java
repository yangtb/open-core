package com.sm.open.core.service.facade.pf.biz.clinic;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.clinic.BasDemoCaParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.BasDemoParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.BasDemoTagParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.PfClinicTemplateParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.check.BasBodyCheckResult;
import com.sm.open.core.facade.model.result.pf.biz.clinic.BasDemoResult;
import com.sm.open.core.facade.model.result.pf.biz.clinic.BasDemoTagResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.clinic.PfClinicTemplateFacade;
import com.sm.open.core.model.dto.pf.biz.clinic.PfClinicTemplateDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasDemo;
import com.sm.open.core.model.entity.BasDemoCa;
import com.sm.open.core.model.entity.BasDemoTag;
import com.sm.open.core.service.service.pf.biz.clinic.PfClinicTemplateService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfClinicTemplateFacade")
public class PfClinicTemplateFacadeImpl implements PfClinicTemplateFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfClinicTemplateFacadeImpl.class);

    @Resource
    private PfClinicTemplateService pfClinicTemplateService;

    @Override
    public CommonResult<List<PfCommonZtreeResult>> listClassifyTree() {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfClinicTemplateService.listClassifyTree(), PfCommonZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicTemplateFacadeImpl-listClassifyTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicTemplateFacadeImpl-listClassifyTree】查询模板分类tree失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.LIST_TEMPLATE_CLASSIFY_TREE_ERROR,
                    PfClinicTemplateConstant.LIST_TEMPLATE_CLASSIFY_TREE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> addClassify(BasDemoCaParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicTemplateService.addClassify(BeanUtil.convert(param, BasDemoCa.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicTemplateFacadeImpl-addClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicTemplateFacadeImpl-addClassify】新增模板分类信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.ADD_TEMPLATE_CLASSIFY_ERROR, PfClinicTemplateConstant.ADD_TEMPLATE_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editClassify(BasDemoCaParam param) {
        try {
            Assert.isTrue(param.getIdDemoCa() != null, "idDemoCa");
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicTemplateService.editClassify(BeanUtil.convert(param, BasDemoCa.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicTemplateFacadeImpl-editClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicTemplateFacadeImpl-editClassify】编辑模板分类信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.EDIT_TEMPLATE_CLASSIFY_ERROR, PfClinicTemplateConstant.EDIT_TEMPLATE_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delClassify(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicTemplateService.delClassify(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicTemplateFacadeImpl-delClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicTemplateFacadeImpl-delClassify】删除模板分类失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.DEL_TEMPLATE_CLASSIFY_ERROR, PfClinicTemplateConstant.DEL_TEMPLATE_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listTemplate(PfClinicTemplateParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfClinicTemplateDto dto = BeanUtil.convert(param, PfClinicTemplateDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfClinicTemplateService.countTemplate(dto),
                    BeanUtil.convertList(pfClinicTemplateService.listTemplate(dto), BasDemoResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfClinicTemplateFacadeImpl-listTemplate-error】获取模板列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfClinicTemplateConstant.PAGE_TEMPLATE_LIST_ERROR, PfClinicTemplateConstant.PAGE_TEMPLATE_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addTemplate(BasDemoParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            Assert.isTrue(param.getIdDemoCa() != null, "idDemoCa");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicTemplateService.addTemplate(BeanUtil.convert(param, BasDemo.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicTemplateFacadeImpl-addTemplate】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicTemplateFacadeImpl-addTemplate】新增模板失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.ADD_TEMPLATE_ERROR, PfClinicTemplateConstant.ADD_TEMPLATE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editTemplate(BasDemoParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(param.getIdDemo() != null, "idDemo");
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            Assert.isTrue(param.getIdDemoCa() != null, "idDemoCa");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicTemplateService.editTemplate(BeanUtil.convert(param, BasDemo.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicTemplateFacadeImpl-editTemplate】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicTemplateFacadeImpl-editTemplate】编辑模板失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.EDIT_TEMPLATE_ERROR, PfClinicTemplateConstant.EDIT_TEMPLATE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delTemplate(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicTemplateService.delTemplate(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicTemplateFacadeImpl-delTemplate】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicTemplateFacadeImpl-delTemplate】删除模板失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.DEL_TAG_ERROR, PfClinicTemplateConstant.DEL_TAG_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listTag(PfClinicTemplateParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfClinicTemplateDto dto = BeanUtil.convert(param, PfClinicTemplateDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfClinicTemplateService.listTag(dto), BasDemoTagResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfClinicTemplateFacadeImpl-listTag-error】获取模板标签列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfClinicTemplateConstant.PAGE_TAG_LIST_ERROR, PfClinicTemplateConstant.PAGE_TAG_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> delTag(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicTemplateService.delTag(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicTemplateFacadeImpl-delAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicTemplateFacadeImpl-delAnswer】删除标签失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.DEL_TAG_ERROR, PfClinicTemplateConstant.DEL_TAG_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveTag(BasDemoTagParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            Assert.isTrue(param.getIdDemo() != null, "idDemo");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicTemplateService.saveTag(BeanUtil.convert(param, BasDemoTag.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicTemplateFacadeImpl-saveTag】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicTemplateFacadeImpl-saveTag】保存标签失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.SAVE_TAG_ERROR, PfClinicTemplateConstant.SAVE_TAG_ERROR_MSG));
        }
    }
}
