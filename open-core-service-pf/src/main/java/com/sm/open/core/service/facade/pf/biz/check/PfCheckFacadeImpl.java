package com.sm.open.core.service.facade.pf.biz.check;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.Assert;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.biz.check.BasBodyCaParam;
import com.sm.open.core.facade.model.param.pf.biz.check.BasBodyParam;
import com.sm.open.core.facade.model.param.pf.biz.check.BasBodyResultParam;
import com.sm.open.core.facade.model.param.pf.biz.check.PfCheckQuestionParam;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.result.pf.biz.PfCommonZtreeResult;
import com.sm.open.core.facade.model.result.pf.biz.check.BasBodyCheckResult;
import com.sm.open.core.facade.model.result.pf.biz.check.BasBodyResultResult;
import com.sm.open.core.facade.model.result.pf.biz.inquisition.BasInquesResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.biz.check.PfCheckFacade;
import com.sm.open.core.model.dto.pf.biz.check.PfCheckQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasBody;
import com.sm.open.core.model.entity.BasBodyCa;
import com.sm.open.core.model.entity.BasBodyResult;
import com.sm.open.core.service.service.pf.biz.check.PfCheckService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfCheckFacade")
public class PfCheckFacadeImpl implements PfCheckFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfCheckFacadeImpl.class);

    @Resource
    private PfCheckService pfCheckService;

    @Override
    public CommonResult<List<PfCommonZtreeResult>> listQuestionClassifyTree() {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfCheckService.listQuestionClassifyTree(), PfCommonZtreeResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCheckFacadeImpl-listQuestionClassifyTree】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCheckFacadeImpl-listQuestionClassifyTree】查询题库分类tree失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCheckConstant.LIST_QUESTION_CLASSIFY_TREE_ERROR,
                    PfCheckConstant.LIST_QUESTION_CLASSIFY_TREE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> addQuestionClassify(BasBodyCaParam param) {
        try {
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCheckService.addQuestionClassify(BeanUtil.convert(param, BasBodyCa.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCheckFacadeImpl-addQuestionClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCheckFacadeImpl-addQuestionClassify】新增题库分类信息失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCheckConstant.ADD_QUESTION_CLASSIFY_ERROR, PfCheckConstant.ADD_QUESTION_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editQuestionClassify(BasBodyCaParam param) {
        try {
            Assert.isTrue(param.getIdBodyCa() != null, "idBodyCa");
            Assert.isTrue(StringUtils.isNotBlank(param.getName()), "name");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCheckService.editQuestionClassify(BeanUtil.convert(param, BasBodyCa.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCheckFacadeImpl-editQuestionClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCheckFacadeImpl-editQuestionClassify】编辑题库分类失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCheckConstant.EDIT_QUESTION_CLASSIFY_ERROR, PfCheckConstant.EDIT_QUESTION_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delQuestionClassify(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCheckService.delQuestionClassify(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCheckFacadeImpl-delQuestionClassify】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCheckFacadeImpl-delQuestionClassify】删除题库分类失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCheckConstant.DEL_QUESTION_CLASSIFY_ERROR, PfCheckConstant.DEL_QUESTION_CLASSIFY_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listQuestion(PfCheckQuestionParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfCheckQuestionDto dto = BeanUtil.convert(param, PfCheckQuestionDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfCheckService.countQuestion(dto),
                    BeanUtil.convertList(pfCheckService.listQuestion(dto), BasBodyCheckResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfCheckFacadeImpl-listQuestion-error】获取问题列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfCheckConstant.PAGE_QUESTION_LIST_ERROR, PfCheckConstant.PAGE_QUESTION_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addQuestion(BasBodyParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getDesBody()), "des_body");
            Assert.isTrue(param.getIdBodyCa() != null, "idBodyCa");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCheckService.addQuestion(BeanUtil.convert(param, BasBody.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCheckFacadeImpl-addQuestion】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCheckFacadeImpl-addQuestion】新增问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCheckConstant.ADD_QUESTION_ERROR, PfCheckConstant.ADD_QUESTION_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editQuestion(BasBodyParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(param.getIdBody() != null, "idBody");
            Assert.isTrue(StringUtils.isNotBlank(param.getDesBody()), "des_body");
            Assert.isTrue(param.getIdBodyCa() != null, "idBodyCa");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCheckService.editQuestion(BeanUtil.convert(param, BasBody.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCheckFacadeImpl-editQuestion】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCheckFacadeImpl-editQuestion】编辑问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCheckConstant.EDIT_QUESTION_ERROR, PfCheckConstant.EDIT_QUESTION_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delQuestion(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCheckService.delQuestion(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCheckFacadeImpl-delQuestion】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCheckFacadeImpl-delQuestion】删除问题失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCheckConstant.DEL_QUESTION_ERROR, PfCheckConstant.DEL_QUESTION_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult<BasBodyResultResult> listAnswer(PfCheckQuestionParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfCheckQuestionDto dto = BeanUtil.convert(param, PfCheckQuestionDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfCheckService.listAnswer(dto), BasBodyResultResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfCheckFacadeImpl-listAnswer-error】获取问题答案列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfCheckConstant.PAGE_ANSWRE_LIST_ERROR, PfCheckConstant.PAGE_ANSWRE_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> delAnswer(PfBachChangeStatusParam param) {
        try {
            Assert.isTrue(CollectionUtils.isNotEmpty(param.getList()), "入参不能为空");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCheckService.delAnswer(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCheckFacadeImpl-delAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCheckFacadeImpl-delAnswer】删除答案失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCheckConstant.DEL_ANSWER_ERROR, PfCheckConstant.DEL_ANSWER_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Long> saveAnswer(BasBodyResultParam param) {
        try {
            /* 参数校验 */
            Assert.isTrue(StringUtils.isNotBlank(param.getDesResult()), "desResult");
            Assert.isTrue(param.getIdBody() != null, "idBody");
            return ResultFactory.initCommonResultWithSuccess(
                    pfCheckService.saveAnswer(BeanUtil.convert(param, BasBodyResult.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfCheckFacadeImpl-saveAnswer】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfCheckFacadeImpl-saveAnswer】保存答案失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfCheckConstant.SAVE_ANSWER_ERROR, PfCheckConstant.SAVE_ANSWER_ERROR_MSG));
        }
    }
}
