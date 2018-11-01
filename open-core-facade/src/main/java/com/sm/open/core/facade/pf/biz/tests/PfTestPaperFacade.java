package com.sm.open.core.facade.pf.biz.tests;

import com.sm.open.core.facade.model.param.pf.biz.tests.paper.*;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCatalogueTreeParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfTestPaperFacade
 * @Description: 试卷定义facade
 * @Author yangtongbin
 * @Date 2018/10/31
 */
public interface PfTestPaperFacade {


    /**
     * Paper分类tree
     *
     * @return
     */
    CommonResult<List<PfCommonZtreeResult>> listPaperClassifyTree(Long idOrg);

    /**
     * 新增Paper信息
     *
     * @param param
     * @return
     */
    CommonResult<Long> savePaperClassify(ExmTestpaperCaParam param);

    /**
     * 删除Paper信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delPaperClassify(PfBachChangeStatusParam param);

    /**
     * 试卷列表
     *
     * @param param
     * @return
     */
    PfPageResult listPaper(PfTestPaperParam param);

    /**
     * 新增试卷信息
     *
     * @param param
     * @return
     */
    CommonResult<Long> savePaper(ExmTestpaperParam param);

    /**
     * 删除试卷信息
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delPaper(PfBachChangeStatusParam param);

    /**
     * 病例tree
     *
     * @param param
     * @return
     */
    CommonResult<List<PfCommonZtreeResult>> listCaseTree(PfCatalogueTreeParam param);

    /**
     * 试题清单列表
     *
     * @param param
     * @return
     */
    PfPageResult listPaperItem(PfTestPaperParam param);

    /**
     * 添加试题清单
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addPaperItem(PfAddCaseParam param);

    /**
     * 删除试题清单
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delPaperItem(PfBachChangeStatusParam param);

    /**
     * 更新排序
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> updatePaperItemSort(ExmTestpaperMedicalrecParam param);
}
