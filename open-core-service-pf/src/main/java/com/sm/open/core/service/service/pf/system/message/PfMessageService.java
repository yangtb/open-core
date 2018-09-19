package com.sm.open.core.service.service.pf.system.message;

import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.system.message.PfMessageDto;
import com.sm.open.core.model.dto.pf.system.message.PfMessageTemplateDto;
import com.sm.open.core.model.entity.MessageTemplate;

import java.util.List;

/**
 * @ClassName: PfMessageService
 * @Description: 消息接口
 * @Author yangtongbin
 * @Date 2018/9/16 20:19
 */
public interface PfMessageService {

    /**
     * 消息模板总数
     *
     * @param dto
     * @return
     */
    Long countMessages(PfMessageDto dto);

    /**
     * 消息模板列表
     *
     * @param dto
     * @return
     */
    List<MessageTemplate> listMessages(PfMessageDto dto);

    /**
     * 新增消息模板
     *
     * @param dto
     * @return
     */
    boolean addMessageTemplate(PfMessageTemplateDto dto);

    /**
     * 编辑消息模板
     *
     * @param dto
     * @return
     */
    boolean editMessageTemplate(PfMessageTemplateDto dto);

    /**
     * 删除消息模板
     *
     * @param dto
     * @return
     */
    boolean updateStatus(PfBachChangeStatusDto dto);
}
