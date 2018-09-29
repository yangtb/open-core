package com.sm.open.core.facade.pf.biz.drug;

import com.sm.open.core.facade.model.param.pf.biz.drug.BasDrugsParam;
import com.sm.open.core.facade.model.param.pf.biz.drug.PfDrugInfoParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.drug.BasDrugsResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

/**
 * @ClassName: PfDiseaseFacade
 * @Description: 药品服务
 * @Author yangtongbin
 * @Date 2018/9/28
 */
public interface PfDrugFacade {

    /**
     * 药品信息列表
     *
     * @param param
     * @return
     */
    PfPageResult listDrugInfo(PfDrugInfoParam param);


    /**
     * 新增药品信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addDrugInfo(BasDrugsParam param);

    /**
     * 编辑药品信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editDrugInfo(BasDrugsParam param);

    /**
     * 删除药品信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delDrugInfo(PfBachChangeStatusParam param);
}
