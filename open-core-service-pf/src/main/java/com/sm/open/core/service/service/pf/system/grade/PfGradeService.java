package com.sm.open.core.service.service.pf.system.grade;

import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.dto.pf.system.grade.PfGradeDto;
import com.sm.open.core.model.dto.pf.user.PfUserDto;
import com.sm.open.core.model.entity.IdClassMemo;
import com.sm.open.core.model.entity.SysClass;
import com.sm.open.core.model.vo.pf.user.login.PfUsersVo;

import java.util.List;

public interface PfGradeService {

    /**
     * 班级总数
     *
     * @param dto
     * @return
     */
    Long countGrades(PfGradeDto dto);

    /**
     * 班级列表
     *
     * @param dto
     * @return
     */
    List<SysClass> listGrades(PfGradeDto dto);

    /**
     * 新增班级
     *
     * @param dto
     * @return
     */
    boolean saveGrade(SysClass dto);

    /**
     * 删除班级
     *
     * @param dto
     * @return
     */
    boolean delGrade(PfBachChangeStatusDto dto);

    /**
     * 学生总数
     *
     * @param dto
     * @return
     */
    Long countStudents(PfGradeDto dto);

    /**
     * 学生列表
     *
     * @param dto
     * @return
     */
    List<IdClassMemo> listStudents(PfGradeDto dto);

    /**
     * 添加学生
     *
     * @param dto
     * @return
     */
    boolean saveStudent(PfCommonListDto dto);

    /**
     * 删除学生
     *
     * @param dto
     * @return
     */
    boolean delStudent(PfBachChangeStatusDto dto);

    /**
     * 获取学生列表
     *
     * @param dto
     * @return
     */
    List<PfUsersVo> listUsStudents(PfUserDto dto);

    /**
     * 学生总数
     *
     * @param dto
     * @return
     */
    Long countUsStudents(PfUserDto dto);
}
