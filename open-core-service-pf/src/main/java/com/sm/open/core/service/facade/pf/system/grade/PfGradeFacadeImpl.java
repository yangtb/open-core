package com.sm.open.core.service.facade.pf.system.grade;

import com.sm.open.care.core.exception.BizRuntimeException;
import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCommonListParam;
import com.sm.open.core.facade.model.param.pf.system.grade.PfGradeParam;
import com.sm.open.core.facade.model.param.pf.system.grade.SysClassParam;
import com.sm.open.core.facade.model.param.pf.user.PfUserParam;
import com.sm.open.core.facade.model.result.pf.system.grade.IdClassMemoResult;
import com.sm.open.core.facade.model.result.pf.system.grade.SysClassResult;
import com.sm.open.core.facade.model.result.pf.user.login.PfUsersResult;
import com.sm.open.core.facade.model.rpc.*;
import com.sm.open.core.facade.pf.system.grade.PfGradeFacade;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.dto.pf.system.grade.PfGradeDto;
import com.sm.open.core.model.dto.pf.user.PfUserDto;
import com.sm.open.core.model.entity.SysClass;
import com.sm.open.core.service.service.pf.system.grade.PfGradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("pfGradeFacade")
public class PfGradeFacadeImpl implements PfGradeFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(PfGradeFacadeImpl.class);

    @Resource
    private PfGradeService pfGradeService;

    @Override
    public PfPageResult listGrades(PfGradeParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfGradeDto dto = BeanUtil.convert(param, PfGradeDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfGradeService.countGrades(dto),
                    BeanUtil.convertList(pfGradeService.listGrades(dto), SysClassResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfGradeFacadeImpl-listGrades-error】分页查询班级列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfGradeConstant.SELECT_PAGE_GRADE_LIST_ERROR, PfGradeConstant.SELECT_PAGE_GRADE_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<List<SysClassResult>> listAllGrades(PfGradeParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    BeanUtil.convertList(pfGradeService.listAllGrades(BeanUtil.convert(param, PfGradeDto.class)),
                            SysClassResult.class));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfGradeFacadeImpl-listAllGrades】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfGradeFacadeImpl-listAllGrades-error】保存班级失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfGradeConstant.LIST_ALL_GRADES_ERROR, PfGradeConstant.LIST_ALL_GRADES_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> saveGrade(SysClassParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfGradeService.saveGrade(BeanUtil.convert(param, SysClass.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfGradeFacadeImpl-saveGrade】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfGradeFacadeImpl-saveGrade-error】保存班级失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfGradeConstant.SAVE_GRADE_ERROR, PfGradeConstant.SAVE_GRADE_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delGrade(PfBachChangeStatusParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfGradeService.delGrade(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfGradeFacadeImpl-delGrade】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfGradeFacadeImpl-delGrade-error】删除班级失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfGradeConstant.DEL_GRADE_ERROR, PfGradeConstant.DEL_GRADE_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult listStudents(PfGradeParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfGradeDto dto = BeanUtil.convert(param, PfGradeDto.class);
            return PfResultFactory.initPagePfResultWithSuccess(pfGradeService.countStudents(dto),
                    BeanUtil.convertList(pfGradeService.listStudents(dto), IdClassMemoResult.class));
        } catch (Exception e) {
            LOGGER.error("【PfGradeFacadeImpl-listGrades-error】分页查询学生列表失败，param:{}", param.toString(), e);
            return PfResultFactory.initPageResultWithError(
                    PfGradeConstant.SELECT_PAGE_STUDENT_LIST_ERROR, PfGradeConstant.SELECT_PAGE_STUDENT_LIST_ERROR_MSG);
        }
    }

    @Override
    public CommonResult<Boolean> saveStudent(PfCommonListParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfGradeService.saveStudent(BeanUtil.convert(param, PfCommonListDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfGradeFacadeImpl-saveStudent】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfGradeFacadeImpl-saveStudent-error】保存学生失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfGradeConstant.SAVE_STUDENT_ERROR, PfGradeConstant.SAVE_STUDENT_ERROR_MSG));
        }
    }

    @Override
    public CommonResult<Boolean> delStudent(PfBachChangeStatusParam param) {
        try {
            return ResultFactory.initCommonResultWithSuccess(
                    pfGradeService.delStudent(BeanUtil.convert(param, PfBachChangeStatusDto.class)));
        } catch (BizRuntimeException e) {
            LOGGER.warn("【PfGradeFacadeImpl-delStudent】, 校验警告:{}", e.getMessage());
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(e.getErrorCode(), e.getMessage()));
        } catch (Exception e) {
            LOGGER.error("【PfGradeFacadeImpl-delStudent-error】删除学生失败, param:" + param.toString(), e);
            return CommonResult.toCommonResult(ResultFactory.initResultWithError(
                    PfGradeConstant.DEL_STUDENT_ERROR, PfGradeConstant.DEL_STUDENT_ERROR_MSG));
        }
    }

    @Override
    public PfPageResult<PfUsersResult> listUsStudents(PfUserParam param) {
        try {
            PfPageParam.initPageDto(param);
            PfUserDto pfUserDto = BeanUtil.convert(param, PfUserDto.class);

            return PfResultFactory.initPagePfResultWithSuccess(pfGradeService.countUsStudents(pfUserDto),
                    BeanUtil.convertList(pfGradeService.listUsStudents(pfUserDto), PfUsersResult.class));
        } catch (Exception e) {
            return PfResultFactory.initPageResultWithError(
                    PfGradeConstant.SELECT_PAGE_USER_LIST_ERROR, PfGradeConstant.SELECT_PAGE_USER_LIST_ERROR_MSG);
        }
    }
}
