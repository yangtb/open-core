package com.sm.open.core.service.service.pf.biz.check.impl;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.core.dal.pf.biz.check.PfCheckDao;
import com.sm.open.core.model.dto.pf.biz.check.PfCheckQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonSearchDto;
import com.sm.open.core.model.entity.BasBody;
import com.sm.open.core.model.entity.BasBodyCa;
import com.sm.open.core.model.entity.BasBodyResult;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.check.BasCheckSearchVo;
import com.sm.open.core.service.service.pf.biz.check.PfCheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfCheckServiceImpl implements PfCheckService {

    @Resource
    private PfCheckDao pfCheckDao;

    @Override
    public List<PfCommonZtreeVo> listQuestionClassifyTree() {
        return pfCheckDao.listQuestionClassifyTree();
    }

    @Override
    public Long addQuestionClassify(BasBodyCa dto) {
        pfCheckDao.addQuestionClassify(dto);
        return dto.getIdBodyCa();
    }

    @Override
    public boolean editQuestionClassify(BasBodyCa dto) {
        return pfCheckDao.editQuestionClassify(dto) == 1 ? true : false;
    }

    @Override
    public boolean delQuestionClassify(PfBachChangeStatusDto dto) {
        dto.setStatus(YesOrNoNum.YES.getCode());
        return pfCheckDao.delQuestionClassify(dto) >= 1 ? true : false;
    }

    @Override
    public Long countQuestion(PfCheckQuestionDto dto) {
        return pfCheckDao.countQuestion(dto);
    }

    @Override
    public List<BasBody> listQuestion(PfCheckQuestionDto dto) {
        return pfCheckDao.listQuestion(dto);
    }

    @Override
    public boolean addQuestion(BasBody dto) {
        return pfCheckDao.addQuestion(dto) == 1 ? true : false;
    }

    @Override
    public boolean editQuestion(BasBody dto) {
        return pfCheckDao.editQuestion(dto) == 1 ? true : false;
    }

    @Override
    public boolean delQuestion(PfBachChangeStatusDto dto) {
        return pfCheckDao.delQuestion(dto) >= 1 ? true : false;
    }

    @Override
    public List<BasBodyResult> listAnswer(PfCheckQuestionDto dto) {
        return pfCheckDao.listAnswer(dto);
    }

    @Override
    public boolean delAnswer(PfBachChangeStatusDto dto) {
        return pfCheckDao.delAnswer(dto) == 1 ? true : false;
    }

    @Override
    public Long saveAnswer(BasBodyResult dto) {
        Integer num;
        if (dto.getIdResult() == null) {
            num = pfCheckDao.saveAnswer(dto);
        } else {
            num = pfCheckDao.editAnswer(dto);
        }
        return num == 1 ? dto.getIdResult() : null;
    }

    @Override
    public Long countSearchCheck(PfCommonSearchDto dto) {
        return pfCheckDao.countSearchCheck(dto);
    }

    @Override
    public List<BasCheckSearchVo> searchCheck(PfCommonSearchDto dto) {
        return pfCheckDao.searchCheck(dto);
    }
}
