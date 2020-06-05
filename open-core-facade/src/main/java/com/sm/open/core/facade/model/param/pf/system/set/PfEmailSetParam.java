package com.sm.open.core.facade.model.param.pf.system.set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfEmailSetDto
 * @Description: 邮件设置参数
 * @Author yangtongbin
 * @Date 2018/9/16 16:26
 */
@Setter
@Getter
@ToString
public class PfEmailSetParam implements Serializable {

    private static final long serialVersionUID = -621149147593065463L;

    private String host;
    private String sender;
    private String password;
    private String userName;
    private String nickname;
    private String sendSwitch;
}
