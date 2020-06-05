package com.sm.open.core.facade.model.param.pf.system.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfMessageTemplateParam implements Serializable {

    private static final long serialVersionUID = -4364499034109410377L;
    /**
     * 模板ID
     */
    private String templateId;
    /**
     * 模板编码
     */
    private String templateCode;
    /**
     *模板名称
     */
    private String templateName;
    /**
     *模板类型sms=短信, email=邮件
     */
    private String templateType;
    /**
     *模板内容
     */
    private String content;
    /**
     *删除标示，N未删除 Y-已删除
     */
    private String isDeleted;
    /**
     *最后修改管理员ID
     */
    private String operator;
}
