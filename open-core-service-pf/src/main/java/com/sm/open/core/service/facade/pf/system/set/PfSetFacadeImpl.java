package com.sm.open.core.service.facade.pf.system.set;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.system.set.PfEmailSendParam;
import com.sm.open.core.facade.model.param.pf.system.set.PfEmailSetParam;
import com.sm.open.core.facade.model.result.pf.system.set.PfEmailSetResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.ResultFactory;
import com.sm.open.core.facade.pf.system.set.PfSetFacade;
import com.sm.open.core.model.dto.pf.system.set.PfEmailSendDto;
import com.sm.open.core.model.dto.pf.system.set.PfEmailSetDto;
import com.sm.open.core.service.service.pf.system.set.PfSetService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("pfSetFacade")
public class PfSetFacadeImpl implements PfSetFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfSetFacadeImpl.class);

    @Resource
    private PfSetService pfSetService;

    @Override
    public CommonResult<Boolean> emailSet(PfEmailSetParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getHost()), "host");
            Assert.isTrue(StringUtils.isNotBlank(param.getNickname()), "nickname");
            Assert.isTrue(StringUtils.isNotBlank(param.getPassword()), "password");
            Assert.isTrue(StringUtils.isNotBlank(param.getSender()), "sender");
            Assert.isTrue(StringUtils.isNotBlank(param.getUserName()), "userName");

            return ResultFactory.initCommonResultWithSuccess(
                    pfSetService.emailSet(BeanUtil.convert(param, PfEmailSetDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfSetFacadeImpl-emailSet】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfSetFacadeImpl-emailSet-error】邮件设置失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfSetConstant.EMAIL_SET_ERROR, PfSetConstant.EMAIL_SET_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<PfEmailSetResult> selectEmailSet() {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convert(pfSetService.selectEmailSet(), PfEmailSetResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfSetFacadeImpl-selectEmailSet-error】获取邮件设置失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfSetConstant.SELECT_EMAIL_SET_ERROR, PfSetConstant.SELECT_EMAIL_SET_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> sendEmail(PfEmailSendParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getRecipients()), "recipients");
            Assert.isTrue(StringUtils.isNotBlank(param.getTitle()), "title");
            Assert.isTrue(StringUtils.isNotBlank(param.getContent()), "content");

            return ResultFactory.initCommonResultWithSuccess(
                    pfSetService.sendEmail(BeanUtil.convert(param, PfEmailSendDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfSetFacadeImpl-sendEmail】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfSetFacadeImpl-sendEmail-error】邮件发送失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfSetConstant.SEND_EMAIL_ERROR, PfSetConstant.SSEND_EMAIL_ERROR_MSG));
        }
    }
}
