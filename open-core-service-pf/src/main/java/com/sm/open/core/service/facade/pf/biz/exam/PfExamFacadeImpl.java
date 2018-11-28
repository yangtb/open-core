package com.sm.open.core.service.facade.pf.biz.exam;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.exam.BasInspectCaParam;
import com.sm.open.core.facade.model.param.pf.biz.exam.BasInspectItemParam;
import com.sm.open.core.facade.model.param.pf.biz.exam.BasItemResultParam;
import com.sm.open.core.facade.model.param.pf.biz.exam.PfExamQuestionParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCommonSearchParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.exam.BasExamSearchResult;
import com.sm.open.core.facade.model.result.pf.biz.exam.BasInspectItemResult;
import com.sm.open.core.facade.model.result.pf.biz.exam.BasItemResultResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.exam.PfExamFacade;
import com.sm.open.core.model.dto.pf.biz.exam.PfExamQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonSearchDto;
import com.sm.open.core.model.entity.BasInspectCa;
import com.sm.open.core.model.entity.BasInspectItem;
import com.sm.open.core.model.entity.BasItemResult;
import com.sm.open.core.service.service.pf.biz.exam.PfExamService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfExamFacade")
public class PfExamFacadeImpl implements PfExamFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfExamFacadeImpl.class);

    @Resource
    private PfExamService pfExamService;

    @Override
    public CommonResult<List<PfCommonZtreeResult>> listQuestionClassifyTree() {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfExamService.listQuestionClassifyTree(), PfCommonZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfExamFacadeImpl-listQuestionClassifyTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-listQuestionClassifyTree】查询题库分类tree失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfExamConstant.LIST_QUESTION_CLASSIFY_TREE_ERROR,
                    PfExamConstant.LIST_QUESTION_CLASSIFY_TREE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> addQuestionClassify(BasInspectCaParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfExamService.addQuestionClassify(BeanUtil.convert(param, BasInspectCa.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfExamFacadeImpl-addQuestionClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-addQuestionClassify】新增题库分类信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfExamConstant.ADD_QUESTION_CLASSIFY_ERROR, PfExamConstant.ADD_QUESTION_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editQuestionClassify(BasInspectCaParam param) {
        try {
            Assert.isTrue(param.getIdInspect() != null, "idInspect");
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfExamService.editQuestionClassify(BeanUtil.convert(param, BasInspectCa.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfExamFacadeImpl-editQuestionClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-editQuestionClassify】编辑题库分类失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfExamConstant.EDIT_QUESTION_CLASSIFY_ERROR, PfExamConstant.EDIT_QUESTION_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delQuestionClassify(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfExamService.delQuestionClassify(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfExamFacadeImpl-delQuestionClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-delQuestionClassify】删除题库分类失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfExamConstant.DEL_QUESTION_CLASSIFY_ERROR, PfExamConstant.DEL_QUESTION_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listQuestion(PfExamQuestionParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfExamQuestionDto dto = BeanUtil.convert(param, PfExamQuestionDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfExamService.countQuestion(dto),
                    BeanUtil.convertList(pfExamService.listQuestion(dto), BasInspectItemResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-listQuestion-error】获取问题列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfExamConstant.PAGE_QUESTION_LIST_ERROR, PfExamConstant.PAGE_QUESTION_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addQuestion(BasInspectItemParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getNaItem()), "naItem");
            Assert.isTrue(param.getIdInspect() != null, "idInspect");
            return ResultFactory.initCommonResultWithSuccess(
                    pfExamService.addQuestion(BeanUtil.convert(param, BasInspectItem.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfExamFacadeImpl-addQuestion】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-addQuestion】新增问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfExamConstant.ADD_QUESTION_ERROR, PfExamConstant.ADD_QUESTION_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editQuestion(BasInspectItemParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(param.getIdInspectItem() != null, "idInspectItem");
            Assert.isTrue(param.getIdInspect() != null, "idInspect");
            Assert.isTrue(StringUtils.isNotBlank(param.getNaItem()), "naItem");
            return ResultFactory.initCommonResultWithSuccess(
                    pfExamService.editQuestion(BeanUtil.convert(param, BasInspectItem.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfExamFacadeImpl-editQuestion】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-editQuestion】编辑问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfExamConstant.EDIT_QUESTION_ERROR, PfExamConstant.EDIT_QUESTION_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delQuestion(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfExamService.delQuestion(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfExamFacadeImpl-delQuestion】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-delQuestion】删除问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfExamConstant.DEL_QUESTION_ERROR, PfExamConstant.DEL_QUESTION_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult<BasItemResultResult> listAnswer(PfExamQuestionParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfExamQuestionDto dto = BeanUtil.convert(param, PfExamQuestionDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfExamService.listAnswer(dto), BasItemResultResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-listAnswer-error】获取问题答案列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfExamConstant.PAGE_ANSWRE_LIST_ERROR, PfExamConstant.PAGE_ANSWRE_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> delAnswer(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfExamService.delAnswer(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfExamFacadeImpl-delAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-delAnswer】删除答案失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfExamConstant.DEL_ANSWER_ERROR, PfExamConstant.DEL_ANSWER_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveAnswer(BasItemResultParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getValResult()), "valResult");
            Assert.isTrue(param.getIdInspectItem() != null, "idInspectItem");
            return ResultFactory.initCommonResultWithSuccess(
                    pfExamService.saveAnswer(BeanUtil.convert(param, BasItemResult.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfExamFacadeImpl-saveAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-saveAnswer】保存答案失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfExamConstant.SAVE_ANSWER_ERROR, PfExamConstant.SAVE_ANSWER_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult<BasExamSearchResult> searchExam(PfCommonSearchParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfCommonSearchDto dto = BeanUtil.convert(param, PfCommonSearchDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfExamService.countSearchExam(dto),
                    PfExamBeanUtil.convertList(pfExamService.searchExam(dto)));
        } catch (Exception e) {
            LOGGER.error("【PfExamFacadeImpl-searchExam-error】搜索检验项目列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfExamConstant.SEARCH_EXAM_LIST_ERROR, PfExamConstant.SEARCH_EXAM_LIST_ERROR_MSG);
        }
    }


}
