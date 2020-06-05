package com.sm.open.core.service.facade.pf.biz.media;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.media.PfMediaParam;
import com.sm.open.core.facade.model.result.pf.system.notice.NoticeResult;
import com.sm.open.core.facade.model.rpc.PfPageParam;
import com.sm.open.core.facade.model.rpc.PfPageResult;
import com.sm.open.core.facade.model.rpc.PfResultFactory;
import com.sm.open.core.facade.pf.biz.media.PfMediaFacade;
import com.sm.open.core.model.dto.pf.biz.media.PfMediaDto;
import com.sm.open.core.service.service.pf.biz.media.PfMediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfMediaFacade")
public class PfMediaFacadeImpl implements PfMediaFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfMediaFacadeImpl.class);

    @Resource
    private PfMediaService pfMediaService;

    @Override
    public PfPageResult<NoticeResult> listBanners(PfMediaParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfMediaDto pfMediaDto = BeanUtil.convert(param, PfMediaDto.class);

            return PfResultFactory.initPagePfResultWithSuccess(pfMediaService.countBanner(pfMediaDto),
                            BeanUtil.convertList(pfMediaService.listBanners(pfMediaDto), NoticeResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfMediaFacadeImpl-listBanners-error】获取banner列表失败，param={}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfMediaConstant.SELECT_BANNERS_ERROR, PfMediaConstant.SELECT_BANNERS_ERROR_MSG);
        }
    }
}
