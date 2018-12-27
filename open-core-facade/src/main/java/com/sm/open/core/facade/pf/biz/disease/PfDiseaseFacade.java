package com.sm.open.core.facade.pf.biz.disease;

import com.sm.open.core.facade.model.param.pf.biz.disease.BasDieClassParam;
import com.sm.open.core.facade.model.param.pf.biz.disease.BasDieParam;
import com.sm.open.core.facade.model.param.pf.biz.disease.PfDiseaseInfoParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCatalogueTreeParam;
import com.sm.open.core.facade.model.param.pf.common.PfChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.disease.BasDieClassResult;
import com.sm.open.core.facade.model.result.pf.biz.disease.PfDiseaseZtreeResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfDiseaseFacade
 * @Description: 疾病服务
 * @Author yangtongbin
 * @Date 2018/9/28
 */
public interface PfDiseaseFacade {

    /**
     * 疾病目录树
     *
     * @param
     * @return
     */
    CommonResult<List<PfDiseaseZtreeResult>> listDiseaseCatalogueTree(PfCatalogueTreeParam param);

    /**
     * 疾病目录信息
     *
     * @param idDieClass 基本目录id
     * @return
     */
    CommonResult<BasDieClassResult> selectDiseaseDetail(Long idDieClass);

    /**
     * 保存疾病目录
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveDiseaseCatalogue(BasDieClassParam param);

    /**
     * 删除疾病目录
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delDiseaseCatalogue(PfChangeStatusParam param);

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
