package com.sm.open.core.service.service.pf.system.message.impl;

import com.sm.open.core.dal.pf.system.message.PfMessageDao;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.system.message.PfMessageDto;
import com.sm.open.core.model.dto.pf.system.message.PfMessageTemplateDto;
import com.sm.open.core.model.entity.MessageTemplate;
import com.sm.open.core.service.service.pf.system.message.PfMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfMessageServiceImpl implements PfMessageService {

    @Resource
    private PfMessageDao pfMessageDao;

    @Override
    public Long countMessages(PfMessageDto dto) {
        return pfMessageDao.countMessages(dto);
    }

    @Override
    public List<MessageTemplate> listMessages(PfMessageDto dto) {
        return pfMessageDao.listMessages(dto);
    }

    @Override
    public boolean addMessageTemplate(PfMessageTemplateDto dto) {
        return pfMessageDao.addMessageTemplate(dto) == 1 ? true : false;
    }

    @Override
    public boolean editMessageTemplate(PfMessageTemplateDto dto) {
        return pfMessageDao.editMessageTemplate(dto) == 1 ? true : false;
    }

    @Override
    public boolean updateStatus(PfBachChangeStatusDto dto) {
        return pfMessageDao.updateStatus(dto.getList(), dto.getStatus()) >= 1 ? true : false;
    }

    @Override
    public boolean isExistTemplate(String templateCode) {
        return pfMessageDao.isExistTemplate(templateCode) >= 1 ? true : false;
    }
}
