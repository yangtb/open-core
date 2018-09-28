package com.sm.open.core.service.facade.pf.biz.drug;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.drug.PfDrugInfoParam;
import com.sm.open.core.facade.model.result.pf.biz.drug.BasDrugsResult;
import com.sm.open.core.facade.model.rpc.PfPageParam;
import com.sm.open.core.facade.model.rpc.PfPageResult;
import com.sm.open.core.facade.model.rpc.PfResultFactory;
import com.sm.open.core.facade.pf.biz.drug.PfDrugFacade;
import com.sm.open.core.model.dto.pf.biz.drug.PfDrugInfoDto;
import com.sm.open.core.service.service.pf.biz.drug.PfDrugService;
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
            LOGGER.error("【PfOrgFacadeImpl-listDrugInfo-error】获取药品信息列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfDrugConstant.PAGE_DRUG_INFO_LIST_ERROR, PfDrugConstant.PAGE_DRUG_INFO_LIST_ERROR_MSG);
        }
    }
}
