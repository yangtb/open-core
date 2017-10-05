package com.sm.open.core.service.facade.mb.test;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.log.LoggerUtil;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.core.facade.mb.test.MbTestFacade;
import com.sm.open.core.facade.model.param.mb.test.MbTestParam;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.ResultFactory;
import com.sm.open.core.service.service.mb.test.MbTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("mbTestFacade")
public class MbTestFacadeImpl implements MbTestFacade {

    private static final Logger     LOGGER = LoggerFactory.getLogger(MbTestFacadeImpl.class);

    @Resource
    private MbTestService           mbTestService;

    @Override
    public CommonResult<Integer> testOpen(MbTestParam mbTestParam) {
        try {
            LoggerUtil.info(LOGGER,"2-【MbTestFacadeImpl-testOpen-params】{0}", mbTestParam.toString());
            /* 参数校验 */
            Assert.isTrue(null != mbTestParam.getTestId(), MbTestConstant.ID_PARAM_IS_NULL, MbTestConstant.ID_PARAM_IS_NULL_MSG);

            return ResultFactory.initCommonResultWithSuccess(mbTestService.testOpen());
        } catch (BizRuntimeException e) {
            LoggerUtil.info(LOGGER,"【MbTestFacadeImpl-testOpen-error】, e.getMessage():{0}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LoggerUtil.info(LOGGER,"【MbTestFacadeImpl-testOpen-error】, e.getMessage():{0}", e.getMessage());
            return CommonResult.toCommonResult(
                    ResultFactory.initResultWithError(MbTestConstant.TESTOPEN_UNKNOWN_ERROR, MbTestConstant.TESTOPEN_UNKNOWN_ERROR_MSG));
        }
    }
}
