package com.sm.open.core.service.facade.pf.common.upload;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.common.upload.BasMediaParam;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.ResultFactory;
import com.sm.open.core.facade.pf.common.upload.PfUploadFacade;
import com.sm.open.core.model.entity.BasMedia;
import com.sm.open.core.service.service.pf.common.upload.PfUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfUploadFacade")
public class PfUploadFacadeImpl implements PfUploadFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfUploadFacadeImpl.class);

    @Resource
    private PfUploadService pfUploadService;

    @Override
    public CommonResult<Long> addBasMedia(BasMediaParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfUploadService.addBasMedia(BeanUtil.convert(param, BasMedia.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfUploadFacadeImpl-addBasMedia】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfUploadFacadeImpl-addBasMedia】新增多媒体信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfUploadConstant.ADD_MEDIA_INFO_ERROR, PfUploadConstant.ADD_MEDIA_INFO_ERROR_MSG));
        }
    }
}
