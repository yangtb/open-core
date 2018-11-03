package com.sm.open.core.facade.pf.system.grade;

import com.sm.open.core.facade.model.param.pf.common.PfBachChangeStatusParam;
import com.sm.open.core.facade.model.param.pf.common.PfCommonListParam;
import com.sm.open.core.facade.model.param.pf.system.grade.PfGradeParam;
import com.sm.open.core.facade.model.param.pf.system.grade.SysClassParam;
import com.sm.open.core.facade.model.param.pf.system.org.*;
import com.sm.open.core.facade.model.param.pf.user.PfUserParam;
import com.sm.open.core.facade.model.result.pf.system.org.SysOrgResult;
import com.sm.open.core.facade.model.result.pf.user.login.PfUsersResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfGradeFacade
 * @Description: 班级服务
 * @Author yangtongbin
 * @Date 2018/11/3
 */
public interface PfGradeFacade {

    /**
     * 班级列表
     *
     * @param param
     * @return
     */
    PfPageResult listGrades(PfGradeParam param);

    /**
     * 新增班级
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> saveGrade(SysClassParam param);

    /**
     * 删除班级
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delGrade(PfBachChangeStatusParam param);

    /**
     * 学生列表
     *
     * @param param
     * @return
     */
    PfPageResult listStudents(PfGradeParam param);

    /**
     * 添加学生
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> saveStudent(PfCommonListParam param);

    /**
     * 删除学生
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> delStudent(PfBachChangeStatusParam param);

    /**
     * 学生列表
     *
     * @param param
     * @return
     */
    PfPageResult<PfUsersResult> listUsStudents(PfUserParam param);
}
