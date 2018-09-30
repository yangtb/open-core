package com.sm.open.core.facade.pf.biz.drug;

import com.sm.open.core.facade.model.param.pf.biz.drug.BasDrugsClassParam;
import com.sm.open.core.facade.model.param.pf.biz.drug.BasDrugsParam;
import com.sm.open.core.facade.model.param.pf.biz.drug.PfDrugInfoParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCatalogueTreeParam;
import com.sm.open.core.facade.model.param.pf.common.PfChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.drug.BasDrugsClassResult;
import com.sm.open.core.facade.model.result.pf.biz.drug.PfDrugZtreeResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfDrugFacade
 * @Description: 药品服务
 * @Author yangtongbin
 * @Date 2018/9/28
 */
public interface PfDrugFacade {

    /**
     * 药品目录树
     *
     * @param
     * @return
     */
    CommonResult<List<PfDrugZtreeResult>> listDrugCatalogueTree(PfCatalogueTreeParam param);

    /**
     * 药品目录信息
     *
     * @param idDrugsclass 药品目录id
     * @return
     */
    CommonResult<BasDrugsClassResult> selectDrugDetail(Long idDrugsclass);

    /**
     * 保存药品目录
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveDrugCatalogue(BasDrugsClassParam param);

    /**
     * 删除药品目录
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delDrugCatalogue(PfChangeStatusParam param);
    
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
