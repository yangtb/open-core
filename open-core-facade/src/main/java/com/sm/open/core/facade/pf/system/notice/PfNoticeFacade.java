package com.sm.open.core.facade.pf.system.notice;

import com.sm.open.core.facade.model.param.pf.system.notice.PfNoticeParam;
import com.sm.open.core.facade.model.param.pf.system.notice.SysNoticeParam;
import com.sm.open.core.facade.model.result.pf.system.notice.SysNoticeResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfNoticeFacade
 * @Description: 公告facade服务
 * @Author yangtongbin
 * @Date 2018/9/14 19:00
 */
public interface PfNoticeFacade {

    /**
     * 公告列表
     *
     * @param param
     * @return
     */
    PfPageResult<SysNoticeResult> listNotices(PfNoticeParam param);

    /**
     * 新增公告
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addNotice(SysNoticeParam param);

    /**
     * 编辑公告
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editNotice(SysNoticeParam param);

    /**
     * 删除公告
     *
     * @param notices 公告ID
     * @return
     */
    CommonResult<Boolean> delNotice(List<Long> notices);

}
