package com.sm.open.core.facade.pf.biz.clinic;

import com.sm.open.core.facade.model.param.pf.biz.clinic.parts.BasAlgorithmParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.parts.BasEvaAsseParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.parts.BasMedAsseParam;
import com.sm.open.core.facade.model.param.pf.biz.clinic.parts.PfClinicPartsParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.clinic.parts.BasAlgorithmResult;
import com.sm.open.core.facade.model.result.pf.biz.clinic.parts.BasEvaAsseResult;
import com.sm.open.core.facade.model.result.pf.biz.clinic.parts.BasMedAsseResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfClinicPartsService
 * @Description: 临床模板组件定义facade接口
 * @Author yangtongbin
 * @Date 2018/10/12
 */
public interface PfClinicPartsFacade {

    /**
     * 组件列表
     *
     * @param param
     * @return
     */
    PfPageResult listParts(PfClinicPartsParam param);

    /**
     * 新增组件定义
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addPart(BasMedAsseParam param);

    /**
     * 编辑组件定义
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editPart(BasMedAsseParam param);

    /**
     * 删除组件定义
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delPart(PfBachChangeStatusParam param);

    /**
     * 评估表列表
     *
     * @param param
     * @return
     */
    PfPageResult listSheet(PfClinicPartsParam param);

    /**
     * 新增评估表定义
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addSheet(BasEvaAsseParam param);

    /**
     * 编辑评估表定义
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editSheet(BasEvaAsseParam param);

    /**
     * 删除评估表定义
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delSheet(PfBachChangeStatusParam param);

    /**
     * 算法列表
     *
     * @param param
     * @return
     */
    PfPageResult listAlgorithm(PfClinicPartsParam param);

    /**
     * 新增评估表定义
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addAlgorithm(BasAlgorithmParam param);

    /**
     * 编辑算法定义
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editAlgorithm(BasAlgorithmParam param);

    /**
     * 删除算法定义
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delAlgorithm(PfBachChangeStatusParam param);

    /**
     * all所有组件
     *
     * @return
     */
    CommonResult<List<BasMedAsseResult>> listAllPart();

    /**
     * all评估表列表
     *
     * @return
     */
    CommonResult<List<BasEvaAsseResult>> listAllSheet();

    /**
     * all算法
     *
     * @return
     */
    CommonResult<List<BasAlgorithmResult>> listAllAlgorithm();
}