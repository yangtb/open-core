package com.sm.open.core.facade.pf.system.set;

import com.sm.open.core.facade.model.param.pf.system.set.PfEmailSendParam;
import com.sm.open.core.facade.model.param.pf.system.set.PfEmailSetParam;
import com.sm.open.core.facade.model.result.pf.system.set.PfEmailSetResult;
import com.sm.open.core.facade.model.rpc.CommonResult;

/**
 * @ClassName: PfSetFacade
 * @Description: 设置facade服务
 * @Author yangtongbin
 * @Date 2018/9/16 16:39
 */
public interface PfSetFacade {

    /**
     * 邮件发送设置
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> emailSet(PfEmailSetParam param);

    /**
     * 获取邮件发送参数
     *
     * @return
     */
    CommonResult<PfEmailSetResult> selectEmailSet();

    /**
     * 邮件发送
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> sendEmail(PfEmailSendParam param);
}
