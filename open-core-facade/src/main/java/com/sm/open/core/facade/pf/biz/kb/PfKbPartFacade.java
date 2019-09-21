package com.sm.open.core.facade.pf.biz.kb;

import com.sm.open.core.facade.model.param.pf.biz.kb.PfSaveAsMedParam;
import com.sm.open.core.facade.model.param.pf.biz.kb.part.*;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.*;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

/**
 * @ClassName: PfCaseHistoryFacade
 * @Description: 病例facade服务
 * @Author yangtongbin
 * @Date 2018/10/10
 */
public interface PfKbPartFacade {

    /**
     * 病例组件用例列表
     *
     * @param param
     * @return
     */
    PfPageResult listKbPart(PfMedCaseParam param);


    /**
     * 新增病例组件用例
     *
     * @param param
     * @return
     */
    CommonResult<Long> addKbPart(FaqMedCaseParam param);

    /**
     * 编辑病例组件用例
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editKbPart(FaqMedCaseParam param);

    /**
     * 删除病例组件用例
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delKbPart(PfBachChangeStatusParam param);

    /**
     * 问诊_问题明细
     *
     * @param param
     * @return
     */
    PfPageResult listFaqMedCaseInques(PfPartCommonParam param);

    /**
     * 保存问诊问题
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveFaqMedCaseInques(FaqMedCaseInquesListParam param);

    /**
     * 删除问诊问题
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delFaqMedCaseInques(PfBachChangeStatusParam param);

    /**
     * 重载咨询问题
     *
     * @param param
     * @return
     */
    CommonResult<FaqMedCaseInquesListResult> resetKbCons(FaqMedCaseInquesListParam param);

    /**
     * 组件 - add文本
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> saveKbText(FaqMedCaseTextParam param);

    /**
     * 查询文本信息
     *
     * @param idMedCase
     * @return
     */
    CommonResult<FaqMedCaseTextResult> selectKbText(Long idMedCase);

    /**
     * 组件 - add图片
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> saveKbPic(FaqMedCasePicParam param);

    /**
     * 查询图片信息
     *
     * @param idMedCase
     * @return
     */
    CommonResult<FaqMedCasePicResult> selectKbPic(Long idMedCase);

    /**
     * 组件 - add患者
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> saveKbPat(FaqMedCasePatientParam param);

    /**
     * 查询患者信息
     *
     * @param idMedCase
     * @return
     */
    CommonResult<FaqMedCasePatientResult> selectKbPat(Long idMedCase);

    /**
     * 检验列表
     *
     * @param param
     * @return
     */
    PfPageResult<FaqMedCaseInspectListResult> listExams(PfPartCommonParam param);

    /**
     * 保存检验
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveExam(FaqMedCaseInspectListParam param);

    /**
     * 删除检验组件用例
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delKbExam(PfBachChangeStatusParam param);

    /**
     * 重载检验项目
     *
     * @param param
     * @return
     */
    CommonResult<FaqMedCaseInspectListResult> resetKbExam(FaqMedCaseInspectListParam param);

    /**
     * 检查列表
     *
     * @param param
     * @return
     */
    PfPageResult listChecks(PfPartCommonParam param);

    /**
     * 保存检查
     *
     * @param param
     * @return
     */
    CommonResult<Long> saveCheck(FaqMedCaseBodyListparam param);

    /**
     * 删除检查
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delKbCheck(PfBachChangeStatusParam param);

    /**
     * 重载检查
     *
     * @param param
     * @return
     */
    CommonResult<FaqMedCaseBodyListResult> resetKbCheck(FaqMedCaseBodyListparam param);

    /**
     * 保存检查图片
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> saveFaqMedCaseBody(FaqMedCaseBodyParam param);

    /**
     * 查询检查图片
     *
     * @param idMedCase
     * @return
     */
    CommonResult<FaqMedCaseBodyResult> selectFaqMedCaseBody(Long idMedCase);

    /**
     * 批量添加问诊
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> bachAddCons(PfSaveAsMedParam param);

    /**
     * 批量添加体格检查
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> bachAddCheck(PfSaveAsMedParam param);

    /**
     * 批量添加辅助检查
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> bachAddExam(PfSaveAsMedParam param);

    /**
     * 问诊预设列表
     *
     * @param param
     * @return
     */
    PfPageResult<FaqMedCaseInquesListResult> listPreQuestion(FaqMedCaseInquesListParam param);
}
