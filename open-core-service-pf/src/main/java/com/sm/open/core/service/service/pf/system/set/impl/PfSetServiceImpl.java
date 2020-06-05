package com.sm.open.core.service.service.pf.system.set.impl;

import com.sm.open.care.core.utils.HtmlMailUtil;
import com.sm.open.care.core.utils.PropertiesUtil;
import com.sm.open.core.model.dto.pf.system.set.PfEmailSendDto;
import com.sm.open.core.model.dto.pf.system.set.PfEmailSetDto;
import com.sm.open.core.model.entity.PfEmailSet;
import com.sm.open.core.model.enums.EmailSetEnum;
import com.sm.open.core.service.service.pf.system.set.PfSetService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class PfSetServiceImpl implements PfSetService {

    @Resource
    private HtmlMailUtil htmlMailUtil;

    /**
     * 邮件配置相对路径
     */
    public static final String WEBSITE_RELATIVE_PATH = "config/core-email.properties";

    @Override
    public boolean emailSet(PfEmailSetDto dto) {
        // 集合设置初始大小，新增时需要修改
        Map<String, String> websiteMap = new HashMap<>(7);
        websiteMap.put(EmailSetEnum.HOST.getCode(), dto.getHost());
        websiteMap.put(EmailSetEnum.NICKNAME.getCode(), dto.getNickname());
        websiteMap.put(EmailSetEnum.PASSWORD.getCode(), dto.getPassword());
        websiteMap.put(EmailSetEnum.SENDER.getCode(), dto.getSender());
        websiteMap.put(EmailSetEnum.USERNAME.getCode(), dto.getUserName());
        websiteMap.put(EmailSetEnum.SEND_SWITCH.getCode(), dto.getSendSwitch());
        websiteMap.put("core.email.encoding", "UTF-8");

        PropertiesUtil.setProperty(websiteMap,
                this.getClass().getClassLoader().getResource(WEBSITE_RELATIVE_PATH).getPath(), "邮件设置");
        return true;
    }

    @Override
    public PfEmailSet selectEmailSet() {
        Properties properties = PropertiesUtil.readProperty(WEBSITE_RELATIVE_PATH);
        if (properties == null) {
            return null;
        }
        PfEmailSet emailSet = new PfEmailSet();
        emailSet.setHost(properties.getProperty(EmailSetEnum.HOST.getCode()));
        emailSet.setNickname(properties.getProperty(EmailSetEnum.NICKNAME.getCode()));
        emailSet.setSender(properties.getProperty(EmailSetEnum.SENDER.getCode()));
        emailSet.setSendSwitch(properties.getProperty(EmailSetEnum.SEND_SWITCH.getCode()));
        emailSet.setUserName(properties.getProperty(EmailSetEnum.USERNAME.getCode()));
        return emailSet;
    }

    @Override
    public boolean sendEmail(PfEmailSendDto dto) {
        return htmlMailUtil.send(StringUtils.split(dto.getRecipients(),";"), dto.getContent(), dto.getTitle());
    }
}
