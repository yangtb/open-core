package com.sm.open.core.service.facade.pf.biz.drug;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.drug.BasDrugsParam;
import com.sm.open.core.facade.model.param.pf.biz.drug.PfDrugInfoParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.drug.BasDrugsResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.drug.PfDrugFacade;
import com.sm.open.core.model.dto.pf.biz.drug.PfDrugInfoDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasDrugs;
import com.sm.open.core.service.service.pf.biz.drug.PfDrugService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfDrugFacade")
public class PfDrugFacadeImpl implements PfDrugFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfDrugFacadeImpl.class);

    @Resource
    private PfDrugService pfDrugService;


    @Override
    public PfPageResult listDrugInfo(PfDrugInfoParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfDrugInfoDto dto = BeanUtil.convert(param, PfDrugInfoDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfDrugService.countDrugInfo(dto),
                    BeanUtil.convertList(pfDrugService.listDrugInfo(dto), BasDrugsResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfDrugFacadeImpl-listDrugInfo-error】获取药品信息列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfDrugConstant.PAGE_DRUG_INFO_LIST_ERROR, PfDrugConstant.PAGE_DRUG_INFO_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addDrugInfo(BasDrugsParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(pfDrugService.addDrugInfo(BeanUtil.convert(param, BasDrugs.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDrugFacadeImpl-addDrugInfo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDrugFacadeImpl-addDrugInfo】新增药品信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDrugConstant.ADD_DRUG_INFO_ERROR, PfDrugConstant.ADD_DRUG_INFO_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editDrugInfo(BasDrugsParam param) {
        try {
            Assert.isTrue(param.getIdDrugs() != null, "idDrug");
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(pfDrugService.editDrugInfo(BeanUtil.convert(param, BasDrugs.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDrugFacadeImpl-editDrugInfo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDrugFacadeImpl-editDrugInfo】编辑药品信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDrugConstant.EDIT_DRUG_INFO_ERROR, PfDrugConstant.EDIT_DRUG_INFO_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delDrugInfo(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(pfDrugService.delDrugInfo(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDrugFacadeImpl-delDrugInfo】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDrugFacadeImpl-delDrugInfo】删除药品信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDrugConstant.DEL_DRUG_INFO_ERROR, PfDrugConstant.DEL_DRUG_INFO_ERROR_MSG));
        }
    }
}
