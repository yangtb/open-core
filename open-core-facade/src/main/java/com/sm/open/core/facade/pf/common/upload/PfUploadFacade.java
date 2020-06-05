package com.sm.open.core.facade.pf.common.upload;

import com.sm.open.core.facade.model.param.pf.common.upload.BasMediaParam;
import com.sm.open.core.facade.model.rpc.CommonResult;

/**
 * @ClassName: PfUploadFacade
 * @Description: 上传服务
 * @Author yangtongbin
 * @Date 2018/10/7
 */
public interface PfUploadFacade {

    /**
     * 增加基础多媒体信息
     *
     * @param param
     * @return
     */
    CommonResult<Long> addBasMedia(BasMediaParam param);
}
