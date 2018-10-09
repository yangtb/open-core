package com.sm.open.core.facade.pf.biz.clinic;

import com.sm.open.core.facade.model.param.pf.biz.clinic.BasDemoCaParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.BasDemoParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.BasDemoTagParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.PfClinicTemplateParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
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
     * 标签列表
     *
     * @param param
     * @return
     */
    PfPageResult listTag(PfClinicTemplateParam param);

    /**
     * 删除标签信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delTag(PfBachChangeStatusParam param);

    /**
     * 保存标签信息
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveTag(BasDemoTagParam param);
}
