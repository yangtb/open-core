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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<BasInques> listPreQuestion(PfInquisitionQuestionDto dto) {
        BasInques basInques = pfInquisitionDao.selectPreIds(dto.getIdInques());
        if (basInques == null) {
            return null;
        }
        List<Long> ids = new ArrayList<>();
        if (basInques.getIdInquesPre() != null) {
            ids.add(basInques.getIdInquesPre());
        }
        if (basInques.getIdInquesPre2() != null) {
            ids.add(basInques.getIdInquesPre2());
        }
        if (basInques.getIdInquesPre3() != null) {
            ids.add(basInques.getIdInquesPre3());
        }
        if (basInques.getIdInquesPre4() != null) {
            ids.add(basInques.getIdInquesPre4());
        }
        if (basInques.getIdInquesPre5() != null) {
            ids.add(basInques.getIdInquesPre5());
        }
        List<BasInques> list= pfInquisitionDao.listPreQuestion(ids);
        return list;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delAnswer(PfBachChangeStatusDto dto) {
        pfInquisitionDao.delAnswer(dto);
        if (!pfInquisitionDao.isExistDefaultAnswer(dto.getExtId())) {
            // 设置默认答案
            pfInquisitionDao.setDefaultAnswer(dto.getExtId());
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveAnswer(BasInquesAnswer dto) {
        if (StringUtils.isBlank(dto.getFgDefault())) {
            dto.setFgDefault(YesOrNoNum.NO.getCode());
        }
        boolean isExistDefaultAnswer = pfInquisitionDao.isExistDefaultAnswer(dto.getIdInques());
        // 设置默认答案
        if (dto.getFgDefault().equals(YesOrNoNum.YES.getCode()) && isExistDefaultAnswer) {
            // 更新
            pfInquisitionDao.updateDefaultAnswer(dto.getIdInques());
        } else {
            dto.setFgDefault(isExistDefaultAnswer ? YesOrNoNum.NO.getCode() : YesOrNoNum.YES.getCode());
        }
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
