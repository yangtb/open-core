package com.sm.open.core.service.service.pf.biz.media;

import com.sm.open.core.model.dto.pf.biz.media.PfMediaDto;
import com.sm.open.core.model.entity.Notice;

import java.util.List;

/**
 * @ClassName: PfMediaService
 * @Description: 咨媒服务
 * @Author yangtongbin
 * @Date 2018/8/28 10:31
 */
public interface PfMediaService {

    /**
     * 获取banner总数
     *
     * @param dto
     * @return
     */
    Long countBanner(PfMediaDto dto);

    /**
     * banner列表
     * @param dto
     * @return
     */
    List<Notice> listBanners(PfMediaDto dto);
}
