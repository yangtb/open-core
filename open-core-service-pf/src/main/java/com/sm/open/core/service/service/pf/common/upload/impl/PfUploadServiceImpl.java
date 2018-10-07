package com.sm.open.core.service.service.pf.common.upload.impl;

import com.sm.open.core.dal.pf.common.upload.PfUploadDao;
import com.sm.open.core.model.entity.BasMedia;
import com.sm.open.core.service.service.pf.common.upload.PfUploadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PfUploadServiceImpl implements PfUploadService {

    @Resource
    private PfUploadDao pfUploadDao;

    @Override
    public Long addBasMedia(BasMedia dto) {
        return pfUploadDao.addBasMedia(dto) == 1 ? dto.getIdMedia() : null;
    }
}
