package com.sm.open.core.service.service.pf.system.notice.impl;

import com.sm.open.core.dal.pf.system.notice.PfNoticeDao;
import com.sm.open.core.model.dto.pf.system.notice.PfNoticeDto;
import com.sm.open.core.model.entity.SysNotice;
import com.sm.open.core.model.vo.pf.system.notice.SysNoticeVo;
import com.sm.open.core.service.service.pf.system.notice.PfNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("pfNoticeService")
public class PfNoticeServiceImpl implements PfNoticeService {

    @Resource
    private PfNoticeDao pfNoticeDao;

    @Override
    public Long countNotices(PfNoticeDto dto) {
        return pfNoticeDao.countNotices(dto);
    }

    @Override
    public List<SysNoticeVo> listNotices(PfNoticeDto dto) {
        return pfNoticeDao.listNotices(dto);
    }

    @Override
    public boolean addNotice(SysNotice dto) {
        return pfNoticeDao.addNotice(dto) == 1 ? true : false;
    }

    @Override
    public boolean editNotice(SysNotice dto) {
        return pfNoticeDao.editNotice(dto) == 1 ? true : false;
    }

    @Override
    public boolean delNotice(List<Long> notices) {
        return pfNoticeDao.delNotice(notices) >= 1 ? true : false;
    }
}
