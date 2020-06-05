package com.sm.open.core.facade.pf.biz.media;

import com.sm.open.core.facade.model.param.pf.biz.media.PfMediaParam;
import com.sm.open.core.facade.model.result.pf.system.notice.NoticeResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

/**
 * @ClassName: PfMediaFacade
 * @Description: 资媒facade
 * @Author yangtongbin
 * @Date 2018/8/27 19:56
 */
public interface PfMediaFacade {

    /**
     * 获取字典分组
     *
     * @param param
     * @return
     */
    PfPageResult<NoticeResult> listBanners(PfMediaParam param);


}
