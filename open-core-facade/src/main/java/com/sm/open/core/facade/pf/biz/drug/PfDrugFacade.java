package com.sm.open.core.facade.pf.biz.drug;

import com.sm.open.core.facade.model.param.pf.biz.drug.PfDrugInfoParam;
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


}
