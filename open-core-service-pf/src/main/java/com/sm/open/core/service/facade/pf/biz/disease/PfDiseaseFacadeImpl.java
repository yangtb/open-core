package com.sm.open.core.service.facade.pf.biz.disease;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.disease.PfDiseaseInfoParam;
import com.sm.open.core.facade.model.result.pf.biz.disease.BasDieResult;
import com.sm.open.core.facade.model.rpc.PfPageParam;
import com.sm.open.core.facade.model.rpc.PfPageResult;
import com.sm.open.core.facade.model.rpc.PfResultFactory;
import com.sm.open.core.facade.pf.biz.disease.PfDiseaseFacade;
import com.sm.open.core.model.dto.pf.biz.disease.PfDiseaseInfoDto;
import com.sm.open.core.service.service.pf.biz.disease.PfDiseaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfDiseaseFacade")
public class PfDiseaseFacadeImpl implements PfDiseaseFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfDiseaseFacadeImpl.class);

    @Resource
    private PfDiseaseService pfDiseaseService;

    @Override
    public PfPageResult listDiseaseInfo(PfDiseaseInfoParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfDiseaseInfoDto diseaseInfoDto = BeanUtil.convert(param, PfDiseaseInfoDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfDiseaseService.countDiseaseInfo(diseaseInfoDto),
                    BeanUtil.convertList(pfDiseaseService.listDiseaseInfo(diseaseInfoDto), BasDieResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfOrgFacadeImpl-listDiseaseInfo-error】获取疾病信息列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfDiseaseConstant.PAGE_DISEASE_INFO_LIST_ERROR, PfDiseaseConstant.PAGE_DISEASE_INFO_LIST_ERROR_MSG);
        }
    }


}
