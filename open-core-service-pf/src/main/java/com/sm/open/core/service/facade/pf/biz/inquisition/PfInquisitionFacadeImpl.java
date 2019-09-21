package com.sm.open.core.service.facade.pf.biz.inquisition;

import com.alibaba.fastjson.JSON;
import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesAnswerParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesCaParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.BasInquesParam;
import com.sm.open.core.facade.model.param.pf.biz.inquisition.PfInquisitionQuestionParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCommonSearchParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.inquisition.BasInquesAnswerResult;
import com.sm.open.core.facade.model.result.pf.biz.inquisition.BasInquesResult;
import com.sm.open.core.facade.model.result.pf.biz.inquisition.BasInquesSearchResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.inquisition.PfInquisitionFacade;
import com.sm.open.core.model.dto.pf.biz.inquisition.PfInquisitionQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonSearchDto;
import com.sm.open.core.model.entity.BasInques;
import com.sm.open.core.model.entity.BasInquesAnswer;
import com.sm.open.core.model.entity.BasInquesCa;
import com.sm.open.core.service.service.pf.biz.inquisition.PfInquisitionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfInquisitionFacade")
public class PfInquisitionFacadeImpl implements PfInquisitionFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfInquisitionFacadeImpl.class);

    @Resource
    private PfInquisitionService pfInquisitionService;

    @Override
    public CommonResult<List<PfCommonZtreeResult>> listQuestionClassifyTree() {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfInquisitionService.listQuestionClassifyTree(), PfCommonZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfInquisitionFacadeImpl-listQuestionClassifyTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-listQuestionClassifyTree】查询题库分类tree失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfInquisitionConstant.LIST_QUESTION_CLASSIFY_TREE_ERROR,
                    PfInquisitionConstant.LIST_QUESTION_CLASSIFY_TREE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<String> listQuestionClassifyTreeSelect() {
        try {
            return ResultFactory.initCommonResultWithSuccess(JSON.toJSONString(pfInquisitionService.listQuestionClassifyTreeSelect()));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfInquisitionFacadeImpl-listQuestionClassifyTreeSelect】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-listQuestionClassifyTreeSelect】查询题库分类tree失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfInquisitionConstant.LIST_QUESTION_CLASSIFY_TREE_ERROR,
                    PfInquisitionConstant.LIST_QUESTION_CLASSIFY_TREE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> addQuestionClassify(BasInquesCaParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfInquisitionService.addQuestionClassify(BeanUtil.convert(param, BasInquesCa.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfInquisitionFacadeImpl-addQuestionClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-addQuestionClassify】新增题库分类信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfInquisitionConstant.ADD_QUESTION_CLASSIFY_ERROR, PfInquisitionConstant.ADD_QUESTION_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editQuestionClassify(BasInquesCaParam param) {
        try {
            Assert.isTrue(param.getIdInquesCa() != null, "idInquesCa");
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfInquisitionService.editQuestionClassify(BeanUtil.convert(param, BasInquesCa.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfInquisitionFacadeImpl-editQuestionClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-editQuestionClassify】编辑题库分类失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfInquisitionConstant.EDIT_QUESTION_CLASSIFY_ERROR, PfInquisitionConstant.EDIT_QUESTION_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delQuestionClassify(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfInquisitionService.delQuestionClassify(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfInquisitionFacadeImpl-delQuestionClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-delQuestionClassify】删除题库分类失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfInquisitionConstant.DEL_QUESTION_CLASSIFY_ERROR, PfInquisitionConstant.DEL_QUESTION_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listQuestion(PfInquisitionQuestionParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfInquisitionQuestionDto dto = BeanUtil.convert(param, PfInquisitionQuestionDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfInquisitionService.countQuestion(dto),
                    BeanUtil.convertList(pfInquisitionService.listQuestion(dto), BasInquesResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-listQuestion-error】获取问题列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfInquisitionConstant.PAGE_QUESTION_LIST_ERROR, PfInquisitionConstant.PAGE_QUESTION_LIST_ERROR_MSG);
        }
    }

    @Override
    public PfPageResult listPreQuestion(PfInquisitionQuestionParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfInquisitionQuestionDto dto = BeanUtil.convert(param, PfInquisitionQuestionDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfInquisitionService.listPreQuestion(dto), BasInquesResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-listPreQuestion-error】获取预设问题列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfInquisitionConstant.PAGE_QUESTION_LIST_ERROR, PfInquisitionConstant.PAGE_QUESTION_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addQuestion(BasInquesParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getDesInques()), "desInques");
            Assert.isTrue(param.getIdInquesCa() != null, "idInquesCa");
            return ResultFactory.initCommonResultWithSuccess(
                    pfInquisitionService.addQuestion(BeanUtil.convert(param, BasInques.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfInquisitionFacadeImpl-addQuestion】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-addQuestion】新增问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfInquisitionConstant.ADD_QUESTION_ERROR, PfInquisitionConstant.ADD_QUESTION_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editQuestion(BasInquesParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(param.getIdInques() != null, "idInques");
            Assert.isTrue(StringUtils.isNotBlank(param.getDesInques()), "desInques");
            Assert.isTrue(param.getIdInquesCa() != null, "idInquesCa");
            return ResultFactory.initCommonResultWithSuccess(
                    pfInquisitionService.editQuestion(BeanUtil.convert(param, BasInques.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfInquisitionFacadeImpl-editQuestion】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-editQuestion】编辑问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfInquisitionConstant.EDIT_QUESTION_ERROR, PfInquisitionConstant.EDIT_QUESTION_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delQuestion(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfInquisitionService.delQuestion(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfInquisitionFacadeImpl-delQuestion】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-delQuestion】删除问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfInquisitionConstant.DEL_QUESTION_ERROR, PfInquisitionConstant.DEL_QUESTION_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult<BasInquesAnswerResult> listAnswer(PfInquisitionQuestionParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfInquisitionQuestionDto dto = BeanUtil.convert(param, PfInquisitionQuestionDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfInquisitionService.listAnswer(dto), BasInquesAnswerResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-listAnswer-error】获取问题答案列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfInquisitionConstant.PAGE_ANSWRE_LIST_ERROR, PfInquisitionConstant.PAGE_ANSWRE_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> delAnswer(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfInquisitionService.delAnswer(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfInquisitionFacadeImpl-delAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-delAnswer】删除答案失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfInquisitionConstant.DEL_ANSWER_ERROR, PfInquisitionConstant.DEL_ANSWER_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveAnswer(BasInquesAnswerParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getDesAnswer()), "desAnswer");
            Assert.isTrue(param.getIdInques() != null, "idInques");
            return ResultFactory.initCommonResultWithSuccess(
                    pfInquisitionService.saveAnswer(BeanUtil.convert(param, BasInquesAnswer.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfInquisitionFacadeImpl-saveAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-saveAnswer】保存答案失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfInquisitionConstant.SAVE_ANSWER_ERROR, PfInquisitionConstant.SAVE_ANSWER_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult<BasInquesSearchResult> searchQuestion(PfCommonSearchParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfCommonSearchDto dto = BeanUtil.convert(param, PfCommonSearchDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfInquisitionService.countSearchQuestion(dto),
                    PfInquisitionBeanUtil.convertList(pfInquisitionService.searchQuestion(dto)));
        } catch (Exception e) {
            LOGGER.error("【PfInquisitionFacadeImpl-searchQuestion-error】搜索问题列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfInquisitionConstant.SEARCH_QUESTION_LIST_ERROR, PfInquisitionConstant.SEARCH_QUESTION_LIST_ERROR_MSG);
        }
    }
}
