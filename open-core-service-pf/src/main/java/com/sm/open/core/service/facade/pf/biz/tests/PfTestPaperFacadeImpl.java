package com.sm.open.core.service.facade.pf.biz.tests;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.tests.paper.*;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCatalogueTreeParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.paper.ExmTestpaperMedicalrecResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.paper.ExmTestpaperResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.tests.PfTestPaperFacade;
import com.sm.open.core.model.dto.pf.biz.tests.PfAddCaseDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestPaperDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.entity.ExmTestpaper;
import com.sm.open.core.model.entity.ExmTestpaperCa;
import com.sm.open.core.model.entity.ExmTestpaperMedicalrec;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.service.service.pf.biz.kb.PfCaseHistoryService;
import com.sm.open.core.service.service.pf.biz.tests.PfTestPaperService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfTestPaperFacade")
public class PfTestPaperFacadeImpl implements PfTestPaperFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfTestPaperFacadeImpl.class);

    @Resource
    private PfTestPaperService pfTestPaperService;

    @Resource
    private PfCaseHistoryService pfCaseHistoryService;

    @Override
    public CommonResult<List<PfCommonZtreeResult>> listPaperClassifyTree(Long idOrg) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfTestPaperService.listPaperClassifyTree(idOrg), PfCommonZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPaperFacadeImpl-listPaperClassifyTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-listPaperClassifyTree】查询试卷分类tree失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_PAPER_CLASSIFY_TREE_ERROR,
                    PfTestPaperConstant.LIST_PAPER_CLASSIFY_TREE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> savePaperClassify(ExmTestpaperCaParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPaperService.savePaperClassify(BeanUtil.convert(param, ExmTestpaperCa.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPaperFacadeImpl-savePaperClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-savePaperClassify】新增试卷分类信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_PAPER_CLASSIFY_ERROR, PfTestPaperConstant.SAVE_PAPER_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delPaperClassify(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPaperService.delPaperClassify(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPaperFacadeImpl-delPaperClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-delPaperClassify】删除试卷分类失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.DEL_PAPER_CLASSIFY_ERROR, PfTestPaperConstant.DEL_PAPER_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<ExmTestpaperResult>> listAllPaper(PfTestPaperParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(
                            pfTestPaperService.listAllPaper(BeanUtil.convert(param, PfTestPaperDto.class)),
                                    ExmTestpaperResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-listAllPaper】获取所有书卷失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_ALL_PAPER_ERROR, PfTestPaperConstant.LIST_ALL_PAPER_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listPaper(PfTestPaperParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfTestPaperDto dto = BeanUtil.convert(param, PfTestPaperDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfTestPaperService.countPaper(dto),
                    BeanUtil.convertList(pfTestPaperService.listPaper(dto), ExmTestpaperResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-listPaper-error】获取试卷列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.PAGE_PAPER_LIST_ERROR, PfTestPaperConstant.PAGE_PAPER_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Long> savePaper(ExmTestpaperParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getNaTestpaper()), "naTestpaper");
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPaperService.savePaper(BeanUtil.convert(param, ExmTestpaper.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPaperFacadeImpl-savePaper】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-savePaper】新增试卷信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_PAPER_ERROR, PfTestPaperConstant.SAVE_PAPER_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delPaper(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPaperService.delPaper(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPaperFacadeImpl-delPaper】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-delPaper】删除试卷失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.DEL_PAPER_ERROR, PfTestPaperConstant.DEL_PAPER_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<PfCommonZtreeResult>> listCaseTree(PfCatalogueTreeParam param) {
        try {
            List<PfCommonZtreeVo> catalogueList = pfCaseHistoryService.listClassifyTree();
            for (PfCommonZtreeVo pfCommonZtreeVo : catalogueList) {
                pfCommonZtreeVo.setNocheck(true);
            }
            List<PfCommonZtreeVo> caseList = pfTestPaperService.listCaseTree(BeanUtil.convert(param, PfCatalogueTreeDto.class));
            catalogueList.addAll(caseList);

            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(catalogueList, PfCommonZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPaperFacadeImpl-listCaseTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-listCaseTree】查询病例tree失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.LIST_CASE_CLASSIFY_TREE_ERROR,
                    PfTestPaperConstant.LIST_CASE_CLASSIFY_TREE_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listPaperItem(PfTestPaperParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfTestPaperDto dto = BeanUtil.convert(param, PfTestPaperDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfTestPaperService.listPaperItem(dto), ExmTestpaperMedicalrecResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-listPaperItem-error】获取试卷清单失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfTestPaperConstant.PAGE_PAPER_ITEM_LIST_ERROR, PfTestPaperConstant.PAGE_PAPER_ITEM_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addPaperItem(PfAddCaseParam param) {
        try {
            Assert.isTrue(param.getIdTestpaper() != null, "idTestpaper");
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "list");
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPaperService.addPaperItem(BeanUtil.convert(param, PfAddCaseDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPaperFacadeImpl-addPaperItem】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-addPaperItem】新增试卷清单失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.SAVE_PAPER_ITEM_ERROR, PfTestPaperConstant.SAVE_PAPER_ITEM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delPaperItem(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            if (StringUtils.isBlank(param.getStatus())) {
                param.setStatus(YesOrNoNum.YES.getCode());
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPaperService.delPaperItem(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPaperFacadeImpl-delPaperItem】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-delPaperItem】删除试卷清单失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.DEL_PAPER_ITEM_ERROR, PfTestPaperConstant.DEL_PAPER_ITEM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> updatePaperItemSort(ExmTestpaperMedicalrecParam param) {
        try {
            Assert.isTrue(param.getIdTestpaperMedicalrec() != null, "idTestpaperMedicalrec");
            Assert.isTrue(param.getSort() != null, "sort");

            return ResultFactory.initCommonResultWithSuccess(
                    pfTestPaperService.updatePaperItemSort(BeanUtil.convert(param, ExmTestpaperMedicalrec.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfTestPaperFacadeImpl-updatePaperItemSort】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfTestPaperFacadeImpl-updatePaperItemSort】修改试卷清单排序失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfTestPaperConstant.UPDATE_PAPER_ITEM_SORT_ERROR, PfTestPaperConstant.UPDATE_PAPER_ITEM_SORT_ERROR_MSG));
        }
    }
}
