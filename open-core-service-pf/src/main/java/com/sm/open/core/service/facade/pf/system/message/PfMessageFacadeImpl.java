package com.sm.open.core.service.facade.pf.system.message;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.system.message.PfMessageParam;
import com.sm.open.core.facade.model.param.pf.system.message.PfMessageTemplateParam;
import com.sm.open.core.facade.model.result.pf.system.message.MessageTemplateResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.system.message.PfMessageFacade;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.system.message.PfMessageDto;
import com.sm.open.core.model.dto.pf.system.message.PfMessageTemplateDto;
import com.sm.open.core.service.facade.pf.user.menu.PfMenuConstant;
import com.sm.open.core.service.service.pf.system.message.PfMessageService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfMessageFacade")
public class PfMessageFacadeImpl implements PfMessageFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfMessageFacadeImpl.class);

    @Resource
    private PfMessageService pfMessageService;

    @Override
    public PfPageResult<MessageTemplateResult> listMessages(PfMessageParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfMessageDto pfMessageDto = BeanUtil.convert(param, PfMessageDto.class);

            return PfResultFactory.initPagePfResultWithSuccess(pfMessageService.countMessages(pfMessageDto),
                    BeanUtil.convertList(pfMessageService.listMessages(pfMessageDto), MessageTemplateResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfMessageFacadeImpl-listMessages-error】获取消息模板列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfMessageConstant.SELECT_PAGE_MESSAGE_LIST_ERROR, PfMessageConstant.SELECT_PAGE_MESSAGE_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addMessageTemplate(PfMessageTemplateParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getTemplateName()), "templateName");
            Assert.isTrue(StringUtils.isNotBlank(param.getTemplateCode()), "templateCode");
            Assert.isTrue(StringUtils.isNotBlank(param.getTemplateType()), "templateType");
            Assert.isTrue(StringUtils.isNotBlank(param.getContent()), "content");
            Assert.isTrue(StringUtils.isNotBlank(param.getOperator()), "operator");
            if (pfMessageService.isExistTemplate(param.getTemplateCode())) {
                throw new BizRuntimeException(PfMessageConstant.TEMPLATE_ISEXIST, PfMessageConstant.TEMPLATE_ISEXIST_MSG);
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfMessageService.addMessageTemplate(BeanUtil.convert(param, PfMessageTemplateDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfMessageFacadeImpl-addMessageTemplate】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfMessageFacadeImpl-addMessageTemplate-error】新增消息模板失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfMessageConstant.ADD_MESSAGE_TEMPLATE_ERROR, PfMessageConstant.ADD_MESSAGE_TEMPLATE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editMessageTemplate(PfMessageTemplateParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getTemplateName()), "templateName");
            Assert.isTrue(StringUtils.isNotBlank(param.getTemplateCode()), "templateCode");
            Assert.isTrue(StringUtils.isNotBlank(param.getTemplateType()), "templateType");
            Assert.isTrue(StringUtils.isNotBlank(param.getContent()), "content");
            Assert.isTrue(StringUtils.isNotBlank(param.getOperator()), "operator");
            return ResultFactory.initCommonResultWithSuccess(
                    pfMessageService.editMessageTemplate(BeanUtil.convert(param, PfMessageTemplateDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfMessageFacadeImpl-editMessageTemplate】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfMessageFacadeImpl-editMessageTemplate-error】编辑消息模板失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfMessageConstant.EDIT_MESSAGE_TEMPLATE_ERROR, PfMessageConstant.EDIT_MESSAGE_TEMPLATE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> updateStatus(PfBachChangeStatusParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            Assert.isTrue(StringUtils.isNotBlank(param.getStatus()), "isDeleted不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfMessageService.updateStatus(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfMessageFacadeImpl-delMessageTemplate】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfMessageFacadeImpl-delMessageTemplate-error】编辑消息模板失败, list:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfMessageConstant.DEL_MESSAGE_TEMPLATE_ERROR, PfMessageConstant.DEL_MESSAGE_TEMPLATE_ERROR_MSG));
        }
    }
}
