package com.sm.open.core.dal.pf.system.message;

import com.sm.open.core.model.dto.pf.system.message.PfMessageDto;
import com.sm.open.core.model.dto.pf.system.message.PfMessageTemplateDto;
import com.sm.open.core.model.entity.MessageTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfMessageDao {

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
    int addMessageTemplate(PfMessageTemplateDto dto);

    /**
     * 编辑消息模板
     *
     * @param dto
     * @return
     */
    int editMessageTemplate(PfMessageTemplateDto dto);

    /**
     * 删除消息模板
     *
     * @param list      主键集合
     * @param isDeleted 是否删除
     * @return
     */
    int updateStatus(@Param("list") List<Long> list,
                     @Param("isDeleted") String isDeleted);
}
