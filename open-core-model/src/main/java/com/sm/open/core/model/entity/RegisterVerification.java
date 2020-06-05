package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class RegisterVerification implements Serializable {

    private static final long serialVersionUID = -8584946216799315713L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 验证码
     */
    private String code;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 截止时间
     */
    private Date endTime;

    /**
     * 验证码类型:sms=短信，email=邮件
     */
    private String type;

    /**
     * 接受地址：手机号or邮箱
     */
    private String receiver;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModify;
}
