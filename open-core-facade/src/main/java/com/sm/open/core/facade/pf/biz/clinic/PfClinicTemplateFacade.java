package com.sm.open.core.facade.pf.biz.clinic;

import com.sm.open.core.facade.model.param.pf.biz.clinic.*;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.clinic.*;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PageResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfClinicTemplateFacade
 * @Description: 临床定义模板facade
 * @Author yangtongbin
 * @Date 2018/10/8
 */
public interface PfClinicTemplateFacade {

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
    CommonResult<Long> addClassify(BasDemoCaParam param);

    /**
     * 编辑分类信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editClassify(BasDemoCaParam param);

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
    PfPageResult listTemplate(PfClinicTemplateParam param);

    /**
     * 新增模板
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addTemplate(BasDemoParam param);

    /**
     * 编辑模板
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editTemplate(BasDemoParam param);

    /**
     * 删除模板
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delTemplate(PfBachChangeStatusParam param);

    /**
     * 病例标签列表
     *
     * @param param
     * @return
     */
    PfPageResult listCaseHistoryTag(PfClinicTemplateParam param);

    /**
     * 删除病例标签
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delCaseHistoryTag(PfBachChangeStatusParam param);

    /**
     * 保存病例标签
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveCaseHistoryTag(BasMedicalTagParam param);

    /**
     * 标签评估列表
     *
     * @param param
     * @return
     */
    PfPageResult listSheetTag(PfClinicTemplateParam param);

    /**
     * 删除评估标签信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delSheetTag(PfBachChangeStatusParam param);

    /**
     * 保存评估标签信息
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveSheetTag(BasEvaTagParam param);

    /**
     * 查询病例所有模板
     *
     * @return
     */
    CommonResult<List<BasDemoResult>> listAllBasDemo();

    /**
     * 根据idDemo查询模板标签
     *
     * @param idDemo 模板id
     * @return
     */
    CommonResult<List<BasDemoTagResult>> listTagByIdDemo(Long idDemo);

    /**
     * 评价维度分类tree
     *
     * @return
     */
    CommonResult<List<PfCommonZtreeResult>> listDimensionTree();

    /**
     * 删除评估维度
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delDimensionTag(PfBachChangeStatusParam param);

    /**
     * 保存评估维度
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveDimensionTag(BasDemoAssesParam param);

    /**
     * 根据id查询评估维度信息
     *
     * @param idDimemsion
     * @return
     */
    CommonResult<BasDemoAssesResult> selectDimensionTagInfo(Long idDimemsion);

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
