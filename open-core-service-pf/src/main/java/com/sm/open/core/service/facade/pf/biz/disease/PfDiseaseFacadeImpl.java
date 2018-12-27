package com.sm.open.core.service.facade.pf.biz.disease;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.disease.BasDieClassParam;
import com.sm.open.core.facade.model.param.pf.biz.disease.BasDieParam;
import com.sm.open.core.facade.model.param.pf.biz.disease.PfDiseaseInfoParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCatalogueTreeParam;
import com.sm.open.core.facade.model.param.pf.common.PfChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.disease.BasDieClassResult;
import com.sm.open.core.facade.model.result.pf.biz.disease.BasDieResult;
import com.sm.open.core.facade.model.result.pf.biz.disease.PfDiseaseZtreeResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.disease.PfDiseaseFacade;
import com.sm.open.core.model.dto.pf.biz.disease.PfDiseaseInfoDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.dto.pf.common.PfChangeStatusDto;
import com.sm.open.core.model.entity.BasDie;
import com.sm.open.core.model.entity.BasDieClass;
import com.sm.open.core.service.service.pf.biz.disease.PfDiseaseService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfDiseaseFacade")
public class PfDiseaseFacadeImpl implements PfDiseaseFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfDiseaseFacadeImpl.class);

    @Resource
    private PfDiseaseService pfDiseaseService;

    @Override
    public CommonResult<List<PfDiseaseZtreeResult>> listDiseaseCatalogueTree(PfCatalogueTreeParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfDiseaseService.listDiseaseCatalogueTree(
                            BeanUtil.convert(param, PfCatalogueTreeDto.class)), PfDiseaseZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDiseaseFacadeImpl-listDiseaseCatalogueTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDiseaseFacadeImpl-listDiseaseCatalogueTree】编辑疾病信息失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDiseaseConstant.LIST_DISEASE_CATALOGUE_TREE_ERROR, PfDiseaseConstant.LIST_DISEASE_CATALOGUE_TREE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<BasDieClassResult> selectDiseaseDetail(Long idDieClass) {
        try {
            Assert.isTrue(idDieClass != null, "idDieClass");
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfDiseaseService.selectDiseaseDetail(idDieClass), BasDieClassResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDiseaseFacadeImpl-selectDiseaseDetail】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDiseaseFacadeImpl-selectDiseaseDetail】编辑疾病信息失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDiseaseConstant.SELECT_DISEASE_DETAIL_ERROR, PfDiseaseConstant.SELECT_DISEASE_DETAIL_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listDiseaseInfo(PfDiseaseInfoParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfDiseaseInfoDto diseaseInfoDto = BeanUtil.convert(param, PfDiseaseInfoDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfDiseaseService.countDiseaseInfo(diseaseInfoDto),
                    BeanUtil.convertList(pfDiseaseService.listDiseaseInfo(diseaseInfoDto), BasDieResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfDiseaseFacadeImpl-listDiseaseInfo-error】获取疾病信息列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfDiseaseConstant.PAGE_DISEASE_INFO_LIST_ERROR, PfDiseaseConstant.PAGE_DISEASE_INFO_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> saveDiseaseCatalogue(BasDieClassParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            Assert.isTrue(StringUtils.isNotBlank(param.getCd()), "cd");
            if (param.getIdDieclass() == null && pfDiseaseService.isExistDiseaseCatalogue(param.getCd())) {
                throw new BizRuntimeException(PfDiseaseConstant.EXIST_DISEASE_CATALOGUE_ERROR,
                        PfDiseaseConstant.EXIST_DISEASE_CATALOGUE_ERROR_MSG);
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfDiseaseService.saveDiseaseCatalogue(BeanUtil.convert(param, BasDieClass.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDiseaseFacadeImpl-saveDiseaseCatalogue】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDiseaseFacadeImpl-saveDiseaseCatalogue】保存疾病目录失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDiseaseConstant.SAVE_DISEASE_CATALOGUE_ERROR, PfDiseaseConstant.SAVE_DISEASE_CATALOGUE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delDiseaseCatalogue(PfChangeStatusParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getIdStr()), "idStr");
            param.setStatus(YesOrNoNum.YES.getCode());
            return ResultFactory.initCommonResultWithSuccess(
                    pfDiseaseService.delDiseaseCatalogue(BeanUtil.convert(param, PfChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDiseaseFacadeImpl-saveDiseaseCatalogue】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDiseaseFacadeImpl-saveDiseaseCatalogue】删除疾病目录失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDiseaseConstant.DEL_DISEASE_CATALOGUE_ERROR, PfDiseaseConstant.DEL_DISEASE_CATALOGUE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> addDiseaseInfo(BasDieParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(pfDiseaseService.addDiseaseInfo(BeanUtil.convert(param, BasDie.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDiseaseFacadeImpl-addDiseaseInfo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDiseaseFacadeImpl-addDiseaseInfo】新增疾病信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDiseaseConstant.ADD_DISEASE_INFO_ERROR, PfDiseaseConstant.ADD_DISEASE_INFO_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editDiseaseInfo(BasDieParam param) {
        try {
            Assert.isTrue(param.getIdDie() != null, "idDie");
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(pfDiseaseService.editDiseaseInfo(BeanUtil.convert(param, BasDie.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDiseaseFacadeImpl-editDiseaseInfo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDiseaseFacadeImpl-editDiseaseInfo】编辑疾病信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDiseaseConstant.EDIT_DISEASE_INFO_ERROR, PfDiseaseConstant.EDIT_DISEASE_INFO_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delDiseaseInfo(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");

            return ResultFactory.initCommonResultWithSuccess(pfDiseaseService.delDiseaseInfo(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDiseaseFacadeImpl-delDiseaseInfo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDiseaseFacadeImpl-delDiseaseInfo】删除疾病信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDiseaseConstant.DEL_DISEASE_INFO_ERROR, PfDiseaseConstant.DEL_DISEASE_INFO_ERROR_MSG));
        }
    }


}
