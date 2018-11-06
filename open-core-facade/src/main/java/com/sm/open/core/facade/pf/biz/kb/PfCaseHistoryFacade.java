package com.sm.open.core.facade.pf.biz.kb;

import com.sm.open.core.facade.model.param.pf.biz.clinic.FaqEvaTagParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.FaqMedTagParam;
import com.sm.open.core.facade.model.param.pf.biz.kb.casehistory.FaqMedicalrecCaParam;
import com.sm.open.core.facade.model.param.pf.biz.kb.casehistory.FaqMedicalrecParam;
import com.sm.open.core.facade.model.param.pf.biz.kb.casehistory.PfCaseHistoryParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.clinic.PfAssessTagResult;
import com.sm.open.core.facade.model.result.pf.biz.clinic.PfCaseHistoryTagResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfCaseHistoryFacade
 * @Description: 病例facade服务
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

    /**
     * 保存病例标签
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveMedTag(FaqMedTagParam param);

    /**
     * 保存评估标签
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveEvaTag(FaqEvaTagParam param);

    /**
     * 获取病例标签列表
     *
     * @param idDemo
     * @return
     */
    CommonResult<List<PfCaseHistoryTagResult>> listAllCaseHistoryTag(Long idDemo);

    /**
     * 获取评估表标签列表
     * @param idDemo
     * @return
     */
    CommonResult<List<PfAssessTagResult>> listAllAssessTag(Long idDemo);

}
