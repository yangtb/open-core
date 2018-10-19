package com.sm.open.core.facade.pf.biz.kb;

import com.sm.open.core.facade.model.param.pf.biz.kb.part.FaqMedCaseParam;
import com.sm.open.core.facade.model.param.pf.biz.kb.part.PfMedCaseParam;
import com.sm.open.core.facade.model.param.pf.biz.kb.part.PfPartCommonParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

/**
 * @ClassName: PfCaseHistoryFacade
 * @Description: 病例facade服务
 * @Author yangtongbin
 * @Date 2018/10/10
 */
public interface PfKbPartFacade {

    /**
     * 病例组件用例列表
     *
     * @param param
     * @return
     */
    PfPageResult listKbPart(PfMedCaseParam param);


    /**
     * 新增病例组件用例
     *
     * @param param
     * @return
     */
    CommonResult<Long> addKbPart(FaqMedCaseParam param);

    /**
     * 编辑病例组件用例
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editKbPart(FaqMedCaseParam param);

    /**
     * 删除病例组件用例
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delKbPart(PfBachChangeStatusParam param);

    /**
     * 问诊_问题明细
     *
     * @param param
     * @return
     */
    PfPageResult listFaqMedCaseInques(PfPartCommonParam param);

}
