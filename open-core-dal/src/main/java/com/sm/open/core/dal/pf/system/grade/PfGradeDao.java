package com.sm.open.core.dal.pf.system.grade;

import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.dto.pf.system.grade.PfGradeDto;
import com.sm.open.core.model.dto.pf.user.PfUserDto;
import com.sm.open.core.model.entity.IdClassMemo;
import com.sm.open.core.model.entity.SysClass;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.user.login.PfUsersVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfGradeDao {

    /**
     * 班级总数
     *
     * @param dto
     * @return
     */
    Long countGrades(PfGradeDto dto);

    /**
     * 查询全部班级
     *
     * @param dto
     * @return
     */
    List<SysClass> listAllGrades(PfGradeDto dto);

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
    Integer addGrade(SysClass dto);

    /**
     * edit班级
     *
     * @param dto
     * @return
     */
    Integer editGrade(SysClass dto);

    /**
     * 删除班级
     *
     * @param dto
     * @return
     */
    Integer delGrade(PfBachChangeStatusDto dto);

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
     * @param list    学生id
     * @param idClass 班级ID
     * @return
     */
    Integer addStudent(@Param("list") List<Long> list,
                       @Param("idClass") Long idClass);

    /**
     * 删除学生
     *
     * @param dto
     * @return
     */
    Integer delStudent(PfBachChangeStatusDto dto);

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

    /**
     * 班级tree
     *
     * @param idOrg
     * @return
     */
    List<PfCommonZtreeVo> listGradeTree(@Param("idOrg") Long idOrg);
}
