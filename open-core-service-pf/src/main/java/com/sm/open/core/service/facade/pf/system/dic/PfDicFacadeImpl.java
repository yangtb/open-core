package com.sm.open.core.service.facade.pf.system.dic;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.system.dic.PfDicParam;
import com.sm.open.core.facade.model.param.pf.system.dic.SysDictionaryParam;
import com.sm.open.core.facade.model.result.pf.system.dic.SysDictionaryResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.system.dic.PfDicFacade;
import com.sm.open.core.model.dto.pf.system.dic.PfDicDto;
import com.sm.open.core.model.entity.SysDictionary;
import com.sm.open.core.service.service.pf.system.dic.PfDicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfDicFacade")
public class PfDicFacadeImpl implements PfDicFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfDicFacadeImpl.class);

    @Resource
    private PfDicService pfDicService;

    @Override
    public PfPageResult<SysDictionaryResult> listDicGroups(PfDicParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfDicDto pfDicDto = BeanUtil.convert(param, PfDicDto.class);

            return PfResultFactory.initPagePfResultWithSuccess(0L,
                    BeanUtil.convertList(pfDicService.listDicGroups(pfDicDto), SysDictionaryResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfDicFacadeImpl-listDicGroups-error】获取字典分组列表失败，param={}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfDicConstant.SELECT_DIC_ERROR, PfDicConstant.SELECT_DIC_ERROR_MSG);
        }
    }

    @Override
    public PfPageResult<SysDictionaryResult> listEnums(PfDicParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfDicDto pfDicDto = BeanUtil.convert(param, PfDicDto.class);

            return PfResultFactory.initPagePfResultWithSuccess(pfDicService.countEnum(pfDicDto),
                    BeanUtil.convertList(pfDicService.listEnums(pfDicDto), SysDictionaryResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfDicFacadeImpl-listEnums-error】获取字典枚举失败，param={}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfDicConstant.SELECT_PAGE_DIC_ENUMS_ERROR, PfDicConstant.SELECT_PAGE_DIC_ENUMS_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> addDic(SysDictionaryParam param) {
        try {
            if (pfDicService.isExistDic(param.getDictCode())) {
                throw new BizRuntimeException(PfDicConstant.ADD_DIC_ISEXIST, PfDicConstant.ADD_DIC_ISEXIST_MSG);
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfDicService.addDic(BeanUtil.convert(param, SysDictionary.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDicFacadeImpl-addDic】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDicFacadeImpl-addDic-error】新增字典失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDicConstant.ADD_DIC_ERROR, PfDicConstant.ADD_DIC_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editDic(SysDictionaryParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfDicService.editDic(BeanUtil.convert(param, SysDictionary.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDicFacadeImpl-editDic】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDicFacadeImpl-editDic-error】修改字典失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDicConstant.EDIT_DIC_ERROR, PfDicConstant.EDIT_DIC_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delDic(List<Long> list) {
        try {
            return ResultFactory.initCommonResultWithSuccess(pfDicService.delDic(list));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDicFacadeImpl-delDic】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDicFacadeImpl-delDic-error】删除字典枚举失败, list=" + list.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDicConstant.DEL_DIC_ERROR, PfDicConstant.DEL_DIC_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> addEnum(SysDictionaryParam param) {
        try {
            if (pfDicService.isExistEnum(param.getDictCode(), param.getGroupCode())) {
                throw new BizRuntimeException(PfDicConstant.ADD_ENUM_ISEXIST, PfDicConstant.ADD_ENUM_ISEXIST_MSG);
            }
            return ResultFactory.initCommonResultWithSuccess(
                    pfDicService.addEnum(BeanUtil.convert(param, SysDictionary.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDicFacadeImpl-addEnum】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDicFacadeImpl-addEnum-error】新增枚举失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDicConstant.ADD_ENUM_ERROR, PfDicConstant.ADD_ENUM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> editEnum(SysDictionaryParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfDicService.editDic(BeanUtil.convert(param, SysDictionary.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDicFacadeImpl-editEnum】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDicFacadeImpl-editEnum-error】修改枚举失败, param=" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDicConstant.EDIT_ENUM_ERROR, PfDicConstant.EDIT_ENUM_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<List<SysDictionaryResult>> listAllEnums() {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfDicService.listAllEnums(), SysDictionaryResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfDicFacadeImpl-listAllEnums】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfDicFacadeImpl-listAllEnums-error】获取所有枚举失败", e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfDicConstant.LIST_ALL_ENUM_ERROR, PfDicConstant.LIST_ALL_ENUM_ERROR_MSG));
        }
    }

}
