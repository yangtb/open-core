package com.sm.open.core.facade.pf.biz.kb;

import com.sm.open.core.facade.model.param.pf.biz.kb.assess.FaqEvaCaseParam;
import com.sm.open.core.facade.model.param.pf.biz.kb.assess.PfEvaCaseParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

/**
 * @ClassName: PfKbAssessFacade
 * @Description: 评估组件facade服务
 * @Author yangtongbin
 * @Date 2018/10/22
 */
public interface PfKbAssessFacade {

    /**
     * 评估组件用例列表
     *
     * @param param
     * @return
     */
    PfPageResult listKbAssess(PfEvaCaseParam param);

    /**
     * 新增评估组件用例
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveKbAssess(FaqEvaCaseParam param);

    /**
     * 删除评估组件用例
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delKbAssess(PfBachChangeStatusParam param);

}
