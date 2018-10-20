package com.sm.open.core.service.service.pf.biz.inquisition.impl;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.core.dal.pf.biz.inquisition.PfInquisitionDao;
import com.sm.open.core.model.dto.pf.biz.inquisition.PfInquisitionQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonSearchDto;
import com.sm.open.core.model.entity.BasInques;
import com.sm.open.core.model.entity.BasInquesAnswer;
import com.sm.open.core.model.entity.BasInquesCa;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.inquisition.BasInquesSearchVo;
import com.sm.open.core.service.service.pf.biz.inquisition.PfInquisitionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfInquisitionServiceImpl implements PfInquisitionService {

    @Resource
    private PfInquisitionDao pfInquisitionDao;

    @Override
    public List<PfCommonZtreeVo> listQuestionClassifyTree() {
        return pfInquisitionDao.listQuestionClassifyTree();
    }

    @Override
    public Long addQuestionClassify(BasInquesCa dto) {
        pfInquisitionDao.addQuestionClassify(dto);
        return dto.getIdInquesCa();
    }

    @Override
    public boolean editQuestionClassify(BasInquesCa dto) {
        return pfInquisitionDao.editQuestionClassify(dto) == 1 ? true : false;
    }

    @Override
    public boolean delQuestionClassify(PfBachChangeStatusDto dto) {
        dto.setStatus(YesOrNoNum.YES.getCode());
        return pfInquisitionDao.delQuestionClassify(dto) >= 1 ? true : false;
    }

    @Override
    public Long countQuestion(PfInquisitionQuestionDto dto) {
        return pfInquisitionDao.countQuestion(dto);
    }

    @Override
    public List<BasInques> listQuestion(PfInquisitionQuestionDto dto) {
        return pfInquisitionDao.listQuestion(dto);
    }

    @Override
    public boolean addQuestion(BasInques dto) {
        return pfInquisitionDao.addQuestion(dto) == 1 ? true : false;
    }

    @Override
    public boolean editQuestion(BasInques dto) {
        return pfInquisitionDao.editQuestion(dto) == 1 ? true : false;
    }

    @Override
    public boolean delQuestion(PfBachChangeStatusDto dto) {
        return pfInquisitionDao.delQuestion(dto) >= 1 ? true : false;
    }

    @Override
    public List<BasInquesAnswer> listAnswer(PfInquisitionQuestionDto dto) {
        return pfInquisitionDao.listAnswer(dto);
    }

    @Override
    public boolean delAnswer(PfBachChangeStatusDto dto) {
        return pfInquisitionDao.delAnswer(dto) == 1 ? true : false;
    }

    @Override
    public Long saveAnswer(BasInquesAnswer dto) {
        Integer num;
        if (dto.getIdAnswer() == null) {
            num = pfInquisitionDao.saveAnswer(dto);
        } else {
            num = pfInquisitionDao.editAnswer(dto);
        }
        return num == 1 ? dto.getIdAnswer() : null;
    }

    @Override
    public Long countSearchQuestion(PfCommonSearchDto dto) {
        return pfInquisitionDao.countSearchQuestion(dto);
    }

    @Override
    public List<BasInquesSearchVo> searchQuestion(PfCommonSearchDto dto) {
        return pfInquisitionDao.searchQuestion(dto);
    }
}
