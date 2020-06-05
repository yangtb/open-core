package com.sm.open.core.service.service.pf.biz.exam.impl;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.core.dal.pf.biz.exam.PfExamDao;
import com.sm.open.core.dal.pf.common.upload.PfUploadDao;
import com.sm.open.core.model.dto.pf.biz.exam.PfExamQuestionDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonSearchDto;
import com.sm.open.core.model.entity.BasInspectCa;
import com.sm.open.core.model.entity.BasInspectItem;
import com.sm.open.core.model.entity.BasItemResult;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.model.vo.pf.biz.PfTreeSelectVo;
import com.sm.open.core.model.vo.pf.biz.exam.BasExamSearchVo;
import com.sm.open.core.service.service.pf.biz.exam.PfExamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PfExamServiceImpl implements PfExamService {

    @Resource
    private PfExamDao pfExamDao;

    @Resource
    private PfUploadDao pfUploadDao;

    @Override
    public List<PfCommonZtreeVo> listQuestionClassifyTree() {
        return pfExamDao.listQuestionClassifyTree();
    }

    @Override
    public List<PfTreeSelectVo> listQuestionClassifyTreeSelect() {
        return pfExamDao.listQuestionClassifyTreeSelect();
    }

    @Override
    public Long addQuestionClassify(BasInspectCa dto) {
        pfExamDao.addQuestionClassify(dto);
        return dto.getIdInspect();
    }

    @Override
    public boolean editQuestionClassify(BasInspectCa dto) {
        return pfExamDao.editQuestionClassify(dto) == 1 ? true : false;
    }

    @Override
    public boolean delQuestionClassify(PfBachChangeStatusDto dto) {
        dto.setStatus(YesOrNoNum.YES.getCode());
        return pfExamDao.delQuestionClassify(dto) >= 1 ? true : false;
    }

    @Override
    public Long countQuestion(PfExamQuestionDto dto) {
        return pfExamDao.countQuestion(dto);
    }

    @Override
    public List<BasInspectItem> listQuestion(PfExamQuestionDto dto) {
        return pfExamDao.listQuestion(dto);
    }

    @Override
    public boolean addQuestion(BasInspectItem dto) {
        return pfExamDao.addQuestion(dto) == 1 ? true : false;
    }

    @Override
    public boolean editQuestion(BasInspectItem dto) {
        return pfExamDao.editQuestion(dto) == 1 ? true : false;
    }

    @Override
    public boolean delQuestion(PfBachChangeStatusDto dto) {
        return pfExamDao.delQuestion(dto) >= 1 ? true : false;
    }

    @Override
    public List<BasItemResult> listAnswer(PfExamQuestionDto dto) {
        List<BasItemResult> list = pfExamDao.listAnswer(dto);
        List<Long> ids;
        for (BasItemResult basItemResult : list) {
            ids = new ArrayList<>();
            if (StringUtils.isNotBlank(basItemResult.getIdMedia())) {
                List<String> idsStr = Arrays.asList(basItemResult.getIdMedia().split(","));
                for (String str : idsStr) {
                    ids.add(Long.valueOf(str));
                }
                basItemResult.setMediaList(pfUploadDao.selectBaseMediaByIds(ids));
            }
        }
        return list;
    }

    @Override
    public boolean delAnswer(PfBachChangeStatusDto dto) {
        pfExamDao.delAnswer(dto);
        if (!pfExamDao.isExistDefaultAnswer(dto.getExtId())) {
            // 设置默认答案
            pfExamDao.setDefaultAnswer(dto.getExtId());
        }
        return true;
    }

    @Override
    public Long saveAnswer(BasItemResult dto) {
        if (StringUtils.isBlank(dto.getFgDefault())) {
            dto.setFgDefault(YesOrNoNum.NO.getCode());
        }
        boolean isExistDefaultAnswer = pfExamDao.isExistDefaultAnswer(dto.getIdInspectItem());
        // 设置默认答案
        if (dto.getFgDefault().equals(YesOrNoNum.YES.getCode()) && isExistDefaultAnswer) {
            // 更新
            pfExamDao.updateDefaultAnswer(dto.getIdInspectItem());
        } else {
            dto.setFgDefault(isExistDefaultAnswer ? YesOrNoNum.NO.getCode() : YesOrNoNum.YES.getCode());
        }
        Integer num;
        if (dto.getIdResult() == null) {
            num = pfExamDao.saveAnswer(dto);
        } else {
            num = pfExamDao.editAnswer(dto);
        }
        return num == 1 ? dto.getIdResult() : null;
    }

    @Override
    public Long countSearchExam(PfCommonSearchDto dto) {
        return pfExamDao.countSearchExam(dto);
    }

    @Override
    public List<BasExamSearchVo> searchExam(PfCommonSearchDto dto) {
        return pfExamDao.searchExam(dto);
    }
}
