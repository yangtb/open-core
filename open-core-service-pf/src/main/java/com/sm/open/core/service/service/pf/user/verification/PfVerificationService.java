package com.sm.open.core.service.service.pf.user.verification;

import com.sm.open.core.model.entity.RegisterVerification;

/**
 * @ClassName: PfVerificationService
 * @Description: 验证码
 * @Author yangtongbin
 * @Date 2018/9/22 15:02
 */
public interface PfVerificationService {

    /**
     * 发送验证码邮件
     *
     * @param email  邮箱
     * @param userId 用户id
     * @return
     */
    boolean sendRegisterEmailVcode(String email, Long userId);

    /**
     * 获取验证码信息
     *
     * @param receiver 验证码接收地址
     * @return
     */
    RegisterVerification selectRvByReceiver(String receiver);
}
