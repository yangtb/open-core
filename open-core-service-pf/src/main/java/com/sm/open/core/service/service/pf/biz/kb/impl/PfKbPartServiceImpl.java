package com.sm.open.core.service.service.pf.biz.kb.impl;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.core.dal.pf.biz.check.PfCheckDao;
import com.sm.open.core.dal.pf.biz.exam.PfExamDao;
import com.sm.open.core.dal.pf.biz.inquisition.PfInquisitionDao;
import com.sm.open.core.dal.pf.biz.kb.PfKbPartDao;
import com.sm.open.core.dal.pf.common.upload.PfUploadDao;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfMedCaseDto;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfPartCommonDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.service.service.pf.biz.kb.PfKbPartService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PfKbPartServiceImpl implements PfKbPartService {

    @Resource
    private PfKbPartDao pfKbPartDao;

    @Resource
    private PfExamDao pfExamDao;

    @Resource
    private PfInquisitionDao pfInquisitionDao;

    @Resource
    private PfCheckDao pfCheckDao;

    @Resource
    private PfUploadDao pfUploadDao;

    @Override
    public Long countKbPart(PfMedCaseDto dto) {
        return pfKbPartDao.countKbPart(dto);
    }

    @Override
    public List<FaqMedCase> listKbPart(PfMedCaseDto dto) {
        return pfKbPartDao.listKbPart(dto);
    }

    @Override
    public Long addKbPart(FaqMedCase dto) {
        return pfKbPartDao.addKbPart(dto) == 1 ? dto.getIdMedCase() : null;
    }

    @Override
    public boolean editKbPart(FaqMedCase dto) {
        return pfKbPartDao.editKbPart(dto) == 1 ? true : false;
    }

    @Override
    public boolean delKbPart(PfBachChangeStatusDto dto) {
        return pfKbPartDao.delKbPart(dto) >= 1 ? true : false;
    }

    @Override
    public List<FaqMedCaseInquesList> listFaqMedCaseInques(PfPartCommonDto dto) {
        return pfKbPartDao.listFaqMedCaseInques(dto);
    }

    @Override
    public Long saveFaqMedCaseInques(FaqMedCaseInquesList dto) {
        Integer num;
        if (dto.getIdMedCaseList() == null) {
            num = pfKbPartDao.saveFaqMedCaseInques(dto);
        } else {
            num = pfKbPartDao.editFaqMedCaseInques(dto);
        }
        return num == 1 ? dto.getIdMedCaseList() : null;
    }

    @Override
    public boolean delFaqMedCaseInques(PfBachChangeStatusDto dto) {
        return pfKbPartDao.delFaqMedCaseInques(dto) >= 1 ? true : false;
    }

    @Override
    public FaqMedCaseInquesList resetKbCons(FaqMedCaseInquesList dto) {
        BasInques basInques = pfInquisitionDao.selectBasInquesById(dto.getIdInques());
        BeanUtils.copyProperties(basInques, dto);
        BasInquesAnswer basInquesAnswer = pfInquisitionDao.selectBasInquesAnswerById(dto.getIdAnswer());
        BeanUtils.copyProperties(basInquesAnswer, dto);

        dto.setFgCarried(YesOrNoNum.NO.getCode());
        pfKbPartDao.editFaqMedCaseInques(dto);
        return pfKbPartDao.selectConsById(dto);
    }

    @Override
    public boolean saveKbText(FaqMedCaseText dto) {
        return pfKbPartDao.saveKbText(dto) == 1 ? true : false;
    }

    @Override
    public FaqMedCaseText selectKbText(Long idMedCase) {
        return pfKbPartDao.selectKbText(idMedCase);
    }

    @Override
    public boolean saveKbPic(FaqMedCasePic dto) {
        return pfKbPartDao.saveKbPic(dto) == 1 ? true : false;
    }

    @Override
    public FaqMedCasePic selectKbPic(Long idMedCase) {
        return pfKbPartDao.selectKbPic(idMedCase);
    }

    @Override
    public boolean saveKbPat(FaqMedCasePatient dto) {
        return pfKbPartDao.saveKbPat(dto) == 1 ? true : false;
    }

    @Override
    public FaqMedCasePatient selectKbPat(Long idMedCase) {
        return pfKbPartDao.selectKbPat(idMedCase);
    }

    @Override
    public List<FaqMedCaseInspectList> listExams(PfPartCommonDto dto) {
        return pfKbPartDao.listExams(dto);
    }

    @Override
    public Long saveExam(FaqMedCaseInspectList dto) {
        Integer num;
        if (dto.getIdMedCaseList() == null) {
            num = pfKbPartDao.addExam(dto);
        } else {
            num = pfKbPartDao.editExam(dto);
        }
        return num == 1 ? dto.getIdMedCaseList() : null;
    }

    @Override
    public boolean delKbExam(PfBachChangeStatusDto dto) {
        return pfKbPartDao.delKbExam(dto) >= 1 ? true : false;
    }

    @Override
    public FaqMedCaseInspectList resetKbExam(FaqMedCaseInspectList dto) {
        BasInspectItem basInspectItem = pfExamDao.selectInspectItemById(dto.getIdInspectItem());
        BeanUtils.copyProperties(basInspectItem, dto);
        BasItemResult basItemResult = pfExamDao.selectItemResultById(dto.getIdResult());
        BeanUtils.copyProperties(basItemResult, dto);
        dto.setFgCarried(YesOrNoNum.NO.getCode());
        pfKbPartDao.editExam(dto);
        return pfKbPartDao.selectExamById(dto);
    }

    @Override
    public List<FaqMedCaseBodyList> listChecks(PfPartCommonDto dto) {
        return pfKbPartDao.listChecks(dto);
    }

    @Override
    public Long saveCheck(FaqMedCaseBodyList dto) {
        Integer num;
        if (dto.getIdMedCaseList() == null) {
            num = pfKbPartDao.addCheck(dto);
        } else {
            num = pfKbPartDao.editCheck(dto);
        }
        return num == 1 ? dto.getIdMedCaseList() : null;
    }

    @Override
    public boolean delKbCheck(PfBachChangeStatusDto dto) {
        return pfKbPartDao.delKbCheck(dto) >= 1 ? true : false;
    }

    @Override
    public FaqMedCaseBodyList resetKbCheck(FaqMedCaseBodyList dto) {
        BasBody basBody = pfCheckDao.selectBasBodyById(dto.getIdBody());
        BeanUtils.copyProperties(basBody, dto);
        BasBodyResult basBodyResult = pfCheckDao.selectBasBodyResultById(dto.getIdResult());
        BeanUtils.copyProperties(basBodyResult, dto);

        dto.setFgCarried(YesOrNoNum.NO.getCode());
        pfKbPartDao.editCheck(dto);
        return pfKbPartDao.selectCheckById(dto);
    }

    @Override
    public boolean saveFaqMedCaseBody(FaqMedCaseBody dto) {
        return pfKbPartDao.saveFaqMedCaseBody(dto) == 1 ? true : false;
    }

    @Override
    public FaqMedCaseBody selectFaqMedCaseBody(Long idMedCase) {
        FaqMedCaseBody faqMedCaseBody = pfKbPartDao.selectFaqMedCaseBody(idMedCase);
        if (faqMedCaseBody == null) {
            return null;
        }
        List<Long> list = new ArrayList<>();
        if (faqMedCaseBody.getIdMediaFront() != null) {
            list.add(faqMedCaseBody.getIdMediaFront());
        }
        if (faqMedCaseBody.getIdMediaBack() != null) {
            list.add(faqMedCaseBody.getIdMediaBack());
        }
        if (CollectionUtils.isEmpty(list)) {
            return faqMedCaseBody;
        }
        List<BasMedia> mediaList = pfUploadDao.selectBaseMediaByIds(list);
        for (BasMedia basMedia : mediaList) {
            if (basMedia.getIdMedia().equals(faqMedCaseBody.getIdMediaFront())) {
                faqMedCaseBody.setFrontPath(basMedia.getPath());
            }
            if (basMedia.getIdMedia().equals(faqMedCaseBody.getIdMediaBack())) {
                faqMedCaseBody.setBackPath(basMedia.getPath());
            }
        }
        return faqMedCaseBody;
    }

    @Override
    public boolean bachAddCons(PfCommonListDto dto) {
        return pfKbPartDao.bachAddCons(dto) >= 1 ? true : false;
    }

    @Override
    public boolean bachAddCheck(PfCommonListDto dto) {
        return pfKbPartDao.bachAddCheck(dto) >= 1 ? true : false;
    }

    @Override
    public boolean bachAddExam(PfCommonListDto dto) {
        return pfKbPartDao.bachAddExam(dto) >= 1 ? true : false;
    }
}
