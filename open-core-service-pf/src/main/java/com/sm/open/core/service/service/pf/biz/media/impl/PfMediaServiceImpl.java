package com.sm.open.core.service.service.pf.biz.media.impl;

import com.sm.open.core.dal.pf.biz.media.PfMediaDao;
import com.sm.open.core.model.dto.pf.biz.media.PfMediaDto;
import com.sm.open.core.model.entity.Notice;
import com.sm.open.core.service.service.pf.biz.media.PfMediaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("pfMediaService")
public class PfMediaServiceImpl implements PfMediaService {

    @Resource
    private PfMediaDao pfMediaDao;

    @Override
    public Long countBanner(PfMediaDto dto) {
        return pfMediaDao.countBanner(dto);
    }

    @Override
    public List<Notice> listBanners(PfMediaDto dto) {
        return pfMediaDao.listBanners(dto);
    }
}
