package com.sm.open.core.service.facade.pf.biz.clinic;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.clinic.parts.BasAlgorithmParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.parts.BasEvaAsseParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.parts.BasMedAsseParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.parts.PfClinicPartsParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.clinic.parts.BasAlgorithmResult;
import com.sm.open.core.facade.model.result.pf.biz.clinic.parts.BasEvaAsseResult;
import com.sm.open.core.facade.model.result.pf.biz.clinic.parts.BasMedAsseResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.clinic.PfClinicPartsFacade;
import com.sm.open.core.model.dto.pf.biz.clinic.parts.PfClinicPartsDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasAlgorithm;
import com.sm.open.core.model.entity.BasEvaAsse;
import com.sm.open.core.model.entity.BasMedAsse;
import com.sm.open.core.service.service.pf.biz.clinic.PfClinicPartsService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfClinicPartsFacade")
public class PfClinicPartsFacadeImpl implements PfClinicPartsFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfClinicPartsFacadeImpl.class);

    @Resource
    private PfClinicPartsService pfClinicPartsService;

    @Override
    public PfPageResult listParts(PfClinicPartsParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfClinicPartsDto dto = BeanUtil.convert(param, PfClinicPartsDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfClinicPartsService.countParts(dto),
                    BeanUtil.convertList(pfClinicPartsService.listParts(dto), BasMedAsseResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-listParts-error】获取组件定义列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfClinicTemplateConstant.PAGE_PART_LIST_ERROR, PfClinicTemplateConstant.PAGE_PART_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addPart(BasMedAsseParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicPartsService.addPart(BeanUtil.convert(param, BasMedAsse.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicPartsFacadeImpl-addPart】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-addPart】新增组件失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.ADD_PART_ERROR, PfClinicTemplateConstant.ADD_PART_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editPart(BasMedAsseParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicPartsService.editPart(BeanUtil.convert(param, BasMedAsse.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicPartsFacadeImpl-editPart】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-editPart】编辑组件失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.EDIT_PART_ERROR, PfClinicTemplateConstant.EDIT_PART_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delPart(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicPartsService.delPart(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicPartsFacadeImpl-delPart】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-delPart】删除组件失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.DEL_PART_ERROR, PfClinicTemplateConstant.DEL_PART_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listSheet(PfClinicPartsParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfClinicPartsDto dto = BeanUtil.convert(param, PfClinicPartsDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfClinicPartsService.countSheet(dto),
                    BeanUtil.convertList(pfClinicPartsService.listSheet(dto), BasEvaAsseResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-listSheet-error】获取评估表列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfClinicTemplateConstant.PAGE_SHEET_LIST_ERROR, PfClinicTemplateConstant.PAGE_SHEET_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addSheet(BasEvaAsseParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicPartsService.addSheet(BeanUtil.convert(param, BasEvaAsse.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicPartsFacadeImpl-addSheet】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-addSheet】add组件失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.ADD_SHEET_ERROR, PfClinicTemplateConstant.ADD_SHEET_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editSheet(BasEvaAsseParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicPartsService.editSheet(BeanUtil.convert(param, BasEvaAsse.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicPartsFacadeImpl-editSheet】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-editSheet】编辑组件失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.EDIT_SHEET_ERROR, PfClinicTemplateConstant.EDIT_SHEET_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delSheet(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicPartsService.delSheet(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicPartsFacadeImpl-delSheet】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-delSheet】删除评估表失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.DEL_SHEET_ERROR, PfClinicTemplateConstant.DEL_SHEET_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listAlgorithm(PfClinicPartsParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfClinicPartsDto dto = BeanUtil.convert(param, PfClinicPartsDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfClinicPartsService.countAlgorithm(dto),
                    BeanUtil.convertList(pfClinicPartsService.listAlgorithm(dto), BasAlgorithmResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-listAlgorithm-error】获取算法定义列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfClinicTemplateConstant.PAGE_ALGORITHM_LIST_ERROR, PfClinicTemplateConstant.PAGE_ALGORITHM_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addAlgorithm(BasAlgorithmParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicPartsService.addAlgorithm(BeanUtil.convert(param, BasAlgorithm.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicPartsFacadeImpl-addAlgorithm】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-addAlgorithm】add算法失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.ADD_ALGORITHM_ERROR, PfClinicTemplateConstant.ADD_ALGORITHM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editAlgorithm(BasAlgorithmParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicPartsService.editAlgorithm(BeanUtil.convert(param, BasAlgorithm.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicPartsFacadeImpl-editAlgorithm】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-editAlgorithm】edit算法失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.EDIT_ALGORITHM_ERROR, PfClinicTemplateConstant.EDIT_ALGORITHM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delAlgorithm(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfClinicPartsService.delAlgorithm(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfClinicPartsFacadeImpl-delAlgorithm】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-delAlgorithm】删除算法失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.DEL_ALGORITHM_ERROR, PfClinicTemplateConstant.DEL_ALGORITHM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<BasMedAsseResult>> listAllPart() {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfClinicPartsService.listAllPart(), BasMedAsseResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-listAllPart】查询所有组件失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.LIST_ALL_PART_ERROR, PfClinicTemplateConstant.LIST_ALL_PART_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<BasEvaAsseResult>> listAllSheet() {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfClinicPartsService.listAllSheet(), BasEvaAsseResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-listAllSheet】查询所有评估表失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.LIST_ALL_SHEET_ERROR, PfClinicTemplateConstant.LIST_ALL_SHEET_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<BasAlgorithmResult>> listAllAlgorithm() {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfClinicPartsService.listAllAlgorithm(), BasAlgorithmResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfClinicPartsFacadeImpl-listAlgorithm】查询所有算法失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfClinicTemplateConstant.LIST_ALL_ALGORITHM_ERROR, PfClinicTemplateConstant.LIST_ALL_ALGORITHM_ERROR_MSG));
        }
    }
}
