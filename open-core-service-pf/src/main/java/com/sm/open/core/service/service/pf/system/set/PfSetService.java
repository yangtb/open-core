package com.sm.open.core.service.service.pf.system.set;

import com.sm.open.core.model.dto.pf.system.set.PfEmailSendDto;
import com.sm.open.core.model.dto.pf.system.set.PfEmailSetDto;
import com.sm.open.core.model.entity.PfEmailSet;

/**
 * @ClassName: PfSetService
 * @Description: 站点设置接口
 * @Author yangtongbin
 * @Date 2018/9/16 16:19
 */
public interface PfSetService {

    /**
     * 邮件发送设置
     *
     * @param dto
     * @return
     */
    boolean emailSet(PfEmailSetDto dto);

    /**
     * 邮件发送设置参数
     *
     * @return
     */
    PfEmailSet selectEmailSet();

    /**
     * 邮件发送
     *
     * @param dto
     * @return
     */
    boolean sendEmail(PfEmailSendDto dto);
}
