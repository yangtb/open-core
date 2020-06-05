package com.sm.open.core.service.service.pf.user.verification.impl;


import com.sm.open.care.core.enums.YesOrNo;
import com.sm.open.care.core.utils.CommonUtil;
import com.sm.open.care.core.utils.DateUtil;
import com.sm.open.care.core.utils.HtmlMailUtil;
import com.sm.open.care.core.utils.RandomUtil;
import com.sm.open.core.dal.pf.system.message.PfMessageDao;
import com.sm.open.core.dal.pf.system.verification.PfRegisterVerificationDao;
import com.sm.open.core.model.entity.MessageTemplate;
import com.sm.open.core.model.entity.RegisterVerification;
import com.sm.open.core.model.enums.TemplateCodeEnum;
import com.sm.open.core.service.service.pf.user.verification.PfVerificationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PfVerificationServiceImpl implements PfVerificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfVerificationServiceImpl.class);


    @Resource
    private PfMessageDao pfMessageDao;

    @Resource
    private PfRegisterVerificationDao pfRegisterVerificationDao;

    @Resource
    private HtmlMailUtil htmlMailUtil;

    /**
     * 邮件服务开关
     */
    @Value("${core.email.sendSwitch}")
    private String emailSendSwitch;

    @Override
    public boolean sendRegisterEmailVcode(String email, Long userId) {
        if (emailSendSwitch.equals(YesOrNo.NO.getCode())) {
            LOGGER.warn("【PfUserServiceImpl-sendRegisterEmailVcode】邮件服务未开通，core.email.sendSwitch:" + emailSendSwitch);
        }
        MessageTemplate messageTemplate = pfMessageDao.selectTempInfoByCode(TemplateCodeEnum.EMAIL_REGISTER_CODE.getCode());
        if (messageTemplate == null) {
            LOGGER.warn("【PfUserServiceImpl-sendRegisterEmailVcode】邮件模板为空，跳过发送，templateCode:"
                    + TemplateCodeEnum.EMAIL_REGISTER_CODE.getCode());
            return false;
        }

        String emailCode;
        RegisterVerification registerVerification = pfRegisterVerificationDao.selectRvByReceiver(email);
        if (registerVerification != null && registerVerification.getEndTime().after(new Date())) {
            emailCode = registerVerification.getCode();
            registerVerification.setStartTime(new Date());
            registerVerification.setEndTime(DateUtil.add(new Date(), Calendar.MINUTE, 15));
            pfRegisterVerificationDao.updateRegisterVerification(registerVerification);
        } else {
            // 获取随机验证码
            emailCode = String.valueOf(RandomUtil.getFourDigitsRandom());
            // 掺入验证码记录
            if (registerVerification == null) {
                registerVerification = new RegisterVerification();
            }
            registerVerification.setUserId(userId);
            registerVerification.setCode(emailCode);
            registerVerification.setStartTime(new Date());
            registerVerification.setEndTime(DateUtil.add(new Date(), Calendar.MINUTE, 15));
            registerVerification.setType("email");
            registerVerification.setReceiver(email);

            if (pfRegisterVerificationDao.insertRegisterVerification(registerVerification) != 1) {
                return false;
            }
        }

        // 替换模板
        Map<String, String> contentMap = new HashMap<>(1);
        contentMap.put("emailCode", emailCode);
        String content = CommonUtil.freemarkerParser(contentMap, messageTemplate.getContent());
        if (StringUtils.isBlank(content)) {
            return false;
        }
        return htmlMailUtil.send(new String[]{email}, content, messageTemplate.getTemplateName());
    }

    @Override
    public RegisterVerification selectRvByReceiver(String receiver) {
        return pfRegisterVerificationDao.selectRvByReceiver(receiver);
    }
}
