package com.sm.open.core.facade.pf.system.message;

import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.system.message.PfMessageParam;
import com.sm.open.core.facade.model.param.pf.system.message.PfMessageTemplateParam;
import com.sm.open.core.facade.model.result.pf.system.message.MessageTemplateResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

/**
 * @ClassName: PfMessageService
 * @Description: 消息facade接口
 * @Author yangtongbin
 * @Date 2018/9/16 20:19
 */
public interface PfMessageFacade {

    /**
     * 消息模板列表
     *
     * @param param
     * @return
     */
    PfPageResult<MessageTemplateResult> listMessages(PfMessageParam param);


    /**
     * 新增消息模板
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addMessageTemplate(PfMessageTemplateParam param);

    /**
     * 编辑消息模板
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editMessageTemplate(PfMessageTemplateParam param);

    /**
     * 删除消息模板
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updateStatus(PfBachChangeStatusParam param);
}
