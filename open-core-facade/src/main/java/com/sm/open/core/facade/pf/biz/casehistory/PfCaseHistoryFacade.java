package com.sm.open.core.facade.pf.biz.casehistory;

import com.sm.open.core.facade.model.param.pf.biz.casehistory.FaqMedicalrecCaParam;
import com.sm.open.core.facade.model.param.pf.biz.casehistory.FaqMedicalrecParam;
import com.sm.open.core.facade.model.param.pf.biz.casehistory.PfCaseHistoryParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfCaseHistoryFacade
 * @Description: 病历facade服务
 * @Author yangtongbin
 * @Date 2018/10/10
 */
public interface PfCaseHistoryFacade {

    /**
     * 分类树
     *
     * @return
     */
    CommonResult<List<PfCommonZtreeResult>> listClassifyTree();

    /**
     * 新增分类信息
     *
     * @param param
     * @return
     */
    CommonResult<Long> addClassify(FaqMedicalrecCaParam param);

    /**
     * 编辑分类信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editClassify(FaqMedicalrecCaParam param);

    /**
     * 删除分类信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delClassify(PfBachChangeStatusParam param);

    /**
     * 模板列表
     *
     * @param param
     * @return
     */
    PfPageResult listTemplate(PfCaseHistoryParam param);

    /**
     * 新增模板
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addTemplate(FaqMedicalrecParam param);

    /**
     * 编辑模板
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editTemplate(FaqMedicalrecParam param);

    /**
     * 删除模板
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delTemplate(PfBachChangeStatusParam param);


}
