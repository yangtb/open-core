package com.sm.open.core.service.facade.pf.system.notice;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.system.notice.PfNoticeParam;
import com.sm.open.core.facade.model.param.pf.system.notice.SysNoticeParam;
import com.sm.open.core.facade.model.result.pf.system.notice.SysNoticeResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.system.notice.PfNoticeFacade;
import com.sm.open.core.model.dto.pf.system.notice.PfNoticeDto;
import com.sm.open.core.model.entity.SysNotice;
import com.sm.open.core.service.service.pf.system.notice.PfNoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfNoticeFacade")
public class PfNoticeFacadeImpl implements PfNoticeFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfNoticeFacadeImpl.class);

    @Resource
    private PfNoticeService pfNoticeService;

    @Override
    public PfPageResult<SysNoticeResult> listNotices(PfNoticeParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfNoticeDto pfNoticeDto = BeanUtil.convert(param, PfNoticeDto.class);

            return PfResultFactory.initPagePfResultWithSuccess(pfNoticeService.countNotices(pfNoticeDto),
                    BeanUtil.convertList(pfNoticeService.listNotices(pfNoticeDto), SysNoticeResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfNoticeFacadeImpl-listNotices-error】获取系统公告列表失败，param={}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfNoticeConstant.SELECT_PAGE_NOTICE_LIST_ERROR, PfNoticeConstant.SELECT_PAGE_NOTICE_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addNotice(SysNoticeParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfNoticeService.addNotice(BeanUtil.convert(param, SysNotice.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfNoticeFacadeImpl-addNotice】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfNoticeFacadeImpl-addNotice-error】新增公告失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfNoticeConstant.ADD_NOTICE_ERROR, PfNoticeConstant.ADD_NOTICE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editNotice(SysNoticeParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfNoticeService.editNotice(BeanUtil.convert(param, SysNotice.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfNoticeFacadeImpl-editNotice】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfNoticeFacadeImpl-editNotice-error】编辑公告失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfNoticeConstant.EDIT_NOTICE_ERROR, PfNoticeConstant.EDIT_NOTICE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delNotice(List<Long> notices) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfNoticeService.delNotice(notices));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfNoticeFacadeImpl-delNotice】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfNoticeFacadeImpl-delNotice-error】删除公告失败, notices=" + notices.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfNoticeConstant.DEL_NOTICE_ERROR, PfNoticeConstant.DEL_NOTICE_ERROR_MSG));
        }
    }
}
