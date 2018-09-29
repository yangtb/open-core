package com.sm.open.core.facade.pf.biz.disease;

import com.sm.open.core.facade.model.param.pf.biz.disease.BasDieParam;
import com.sm.open.core.facade.model.param.pf.biz.disease.PfDiseaseInfoParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

/**
 * @ClassName: PfDiseaseFacade
 * @Description: 疾病服务
 * @Author yangtongbin
 * @Date 2018/9/28
 */
public interface PfDiseaseFacade {

    /**
     * 疾病信息列表
     *
     * @param param
     * @return
     */
    PfPageResult listDiseaseInfo(PfDiseaseInfoParam param);


    /**
     * 新增疾病信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addDiseaseInfo(BasDieParam param);

    /**
     * 编辑疾病信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editDiseaseInfo(BasDieParam param);

    /**
     * 删除疾病信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delDiseaseInfo(PfBachChangeStatusParam param);

}
