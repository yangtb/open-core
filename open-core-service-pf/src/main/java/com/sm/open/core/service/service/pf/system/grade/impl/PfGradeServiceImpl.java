package com.sm.open.core.service.service.pf.system.grade.impl;

import com.sm.open.core.dal.pf.system.grade.PfGradeDao;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.dto.pf.system.grade.PfGradeDto;
import com.sm.open.core.model.dto.pf.user.PfUserDto;
import com.sm.open.core.model.entity.IdClassMemo;
import com.sm.open.core.model.entity.SysClass;
import com.sm.open.core.model.vo.pf.user.login.PfUsersVo;
import com.sm.open.core.service.service.pf.system.grade.PfGradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfGradeServiceImpl implements PfGradeService {

    @Resource
    private PfGradeDao pfGradeDao;

    @Override
    public Long countGrades(PfGradeDto dto) {
        return pfGradeDao.countGrades(dto);
    }

    @Override
    public List<SysClass> listAllGrades(PfGradeDto dto) {
        return pfGradeDao.listAllGrades(dto);
    }

    @Override
    public List<SysClass> listGrades(PfGradeDto dto) {
        return pfGradeDao.listGrades(dto);
    }

    @Override
    public boolean saveGrade(SysClass dto) {
        Integer num;
        if (dto.getIdClass() == null) {
            num = pfGradeDao.addGrade(dto);
        } else {
            num = pfGradeDao.editGrade(dto);
        }
        return num == 1 ? true : false;
    }

    @Override
    public boolean delGrade(PfBachChangeStatusDto dto) {
        return pfGradeDao.delGrade(dto) >= 1 ? true : false;
    }

    @Override
    public Long countStudents(PfGradeDto dto) {
        return pfGradeDao.countStudents(dto);
    }

    @Override
    public List<IdClassMemo> listStudents(PfGradeDto dto) {
        return pfGradeDao.listStudents(dto);
    }

    @Override
    public boolean saveStudent(PfCommonListDto dto) {
        return pfGradeDao.addStudent(dto.getList(), dto.getExtId()) >= 1 ? true : false;
    }

    @Override
    public boolean delStudent(PfBachChangeStatusDto dto) {
        return pfGradeDao.delStudent(dto) >= 1 ? true : false;
    }

    @Override
    public List<PfUsersVo> listUsStudents(PfUserDto dto) {
        return pfGradeDao.listUsStudents(dto);
    }

    @Override
    public Long countUsStudents(PfUserDto dto) {
        return pfGradeDao.countUsStudents(dto);
    }
}
