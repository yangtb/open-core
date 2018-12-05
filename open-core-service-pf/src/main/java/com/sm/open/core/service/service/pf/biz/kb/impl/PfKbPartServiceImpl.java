package com.sm.open.core.service.service.pf.biz.kb.impl;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.core.dal.pf.biz.check.PfCheckDao;
import com.sm.open.core.dal.pf.biz.exam.PfExamDao;
import com.sm.open.core.dal.pf.biz.inquisition.PfInquisitionDao;
import com.sm.open.core.dal.pf.biz.kb.PfCaseHistoryDao;
import com.sm.open.core.dal.pf.biz.kb.PfKbPartDao;
import com.sm.open.core.dal.pf.common.upload.PfUploadDao;
import com.sm.open.core.model.dto.pf.biz.kb.PfSaveAsMedDto;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfMedCaseDto;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfPartCommonDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.service.service.pf.biz.kb.PfKbPartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Resource
    private PfCaseHistoryDao pfCaseHistoryDao;

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
        dto.setFgPublic(YesOrNoNum.NO.getCode());
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
    public Long countFaqMedCaseInques(PfPartCommonDto dto) {
        return pfKbPartDao.countFaqMedCaseInques(dto);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveKbText(FaqMedCaseText dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdMedCase() == null) {
            if (dto.getOldIdMedCase() == null) {
                // 直接新增
                FaqMedCase faqMedCase = new FaqMedCase();
                faqMedCase.setIdMedCase(dto.getOldIdMedCase());
                faqMedCase.setCreator(dto.getCreator());
                faqMedCase.setOperator(dto.getCreator());
                faqMedCase.setName(dto.getCaseName());
                faqMedCase.setIdOrg(dto.getIdOrg());
                faqMedCase.setFgActive(YesOrNoNum.YES.getCode());
                faqMedCase.setCdMedAsse("001");
                faqMedCase.setFgPublic(YesOrNoNum.NO.getCode());
                pfKbPartDao.addKbPart(faqMedCase);

                FaqMedCaseText faqMedCaseText = new FaqMedCaseText();
                faqMedCaseText.setIdMedCase(faqMedCase.getIdMedCase());
                faqMedCaseText.setContent(dto.getContent());
                pfKbPartDao.saveKbText(faqMedCaseText);

                // 3 保存病例标签
                dto.setIdMedCase(faqMedCase.getIdMedCase());
                pfCaseHistoryDao.saveMedTag(dto);
                return true;
            }
            // 病例维护 1-生成主表记录
            FaqMedCase faqMedCase = new FaqMedCase();
            faqMedCase.setIdMedCase(dto.getOldIdMedCase());
            faqMedCase.setCreator(dto.getCreator());
            faqMedCase.setName(dto.getCaseName());
            faqMedCase.setIdOrg(dto.getIdOrg());
            pfKbPartDao.copyKbPart(faqMedCase);
            // 2-复制子表
            pfKbPartDao.copyKbText(dto.getOldIdMedCase(), faqMedCase.getIdMedCase());
            // 3 保存病例标签
            dto.setIdMedCase(faqMedCase.getIdMedCase());
            pfCaseHistoryDao.saveMedTag(dto);
            return true;
        }
        return pfKbPartDao.saveKbText(dto) == 1 ? true : false;
    }

    @Override
    public FaqMedCaseText selectKbText(Long idMedCase) {
        return pfKbPartDao.selectKbText(idMedCase);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveKbPic(FaqMedCasePic dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdMedCase() == null) {
            if (dto.getOldIdMedCase() == null) {
                // 直接新增
                FaqMedCase faqMedCase = new FaqMedCase();
                faqMedCase.setIdMedCase(dto.getOldIdMedCase());
                faqMedCase.setCreator(dto.getCreator());
                faqMedCase.setOperator(dto.getCreator());
                faqMedCase.setName(dto.getCaseName());
                faqMedCase.setIdOrg(dto.getIdOrg());
                faqMedCase.setFgActive(YesOrNoNum.YES.getCode());
                faqMedCase.setCdMedAsse("002");
                faqMedCase.setFgPublic(YesOrNoNum.NO.getCode());
                pfKbPartDao.addKbPart(faqMedCase);

                FaqMedCasePic faqMedCasePic = new FaqMedCasePic();
                faqMedCasePic.setIdMedCase(faqMedCase.getIdMedCase());
                faqMedCasePic.setIdMedia(dto.getIdMedia());
                pfKbPartDao.saveKbPic(faqMedCasePic);

                // 3 保存病例标签
                dto.setIdMedCase(faqMedCase.getIdMedCase());
                pfCaseHistoryDao.saveMedTag(dto);
                return true;
            }
            // 病例维护 1-生成主表记录
            FaqMedCase faqMedCase = new FaqMedCase();
            faqMedCase.setIdMedCase(dto.getOldIdMedCase());
            faqMedCase.setCreator(dto.getCreator());
            faqMedCase.setName(dto.getCaseName());
            faqMedCase.setIdOrg(dto.getIdOrg());
            pfKbPartDao.copyKbPart(faqMedCase);
            // 2-复制子表
            pfKbPartDao.copyKbPic(dto.getOldIdMedCase(), faqMedCase.getIdMedCase());
            // 3 保存病例标签
            dto.setIdMedCase(faqMedCase.getIdMedCase());
            pfCaseHistoryDao.saveMedTag(dto);
            return true;
        }
        return pfKbPartDao.saveKbPic(dto) == 1 ? true : false;
    }

    @Override
    public FaqMedCasePic selectKbPic(Long idMedCase) {
        return pfKbPartDao.selectKbPic(idMedCase);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveKbPat(FaqMedCasePatient dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdMedCase() == null) {
            if (dto.getOldIdMedCase() == null) {
                // 直接新增
                FaqMedCase faqMedCase = new FaqMedCase();
                faqMedCase.setIdMedCase(dto.getOldIdMedCase());
                faqMedCase.setCreator(dto.getCreator());
                faqMedCase.setOperator(dto.getCreator());
                faqMedCase.setName(dto.getCaseName());
                faqMedCase.setIdOrg(dto.getIdOrg());
                faqMedCase.setFgActive(YesOrNoNum.YES.getCode());
                faqMedCase.setCdMedAsse("003");
                faqMedCase.setFgPublic(YesOrNoNum.NO.getCode());
                pfKbPartDao.addKbPart(faqMedCase);

                FaqMedCasePatient faqMedCasePatient = new FaqMedCasePatient();
                faqMedCasePatient.setIdMedCase(faqMedCase.getIdMedCase());
                faqMedCasePatient.setName(dto.getName());
                faqMedCasePatient.setSex(dto.getSex());
                faqMedCasePatient.setAge(dto.getAge());
                faqMedCasePatient.setComplaint(dto.getComplaint());
                pfKbPartDao.saveKbPat(faqMedCasePatient);

                // 3 保存病例标签
                dto.setIdMedCase(faqMedCase.getIdMedCase());
                pfCaseHistoryDao.saveMedTag(dto);
                return true;
            }
            // 病例维护 1-生成主表记录
            FaqMedCase faqMedCase = new FaqMedCase();
            faqMedCase.setIdMedCase(dto.getOldIdMedCase());
            faqMedCase.setCreator(dto.getCreator());
            faqMedCase.setName(dto.getCaseName());
            faqMedCase.setIdOrg(dto.getIdOrg());
            pfKbPartDao.copyKbPart(faqMedCase);
            // 2-复制子表
            pfKbPartDao.copyKbPat(dto.getOldIdMedCase(), faqMedCase.getIdMedCase());
            // 3 保存病例标签
            dto.setIdMedCase(faqMedCase.getIdMedCase());
            pfCaseHistoryDao.saveMedTag(dto);
            return true;
        }
        return pfKbPartDao.saveKbPat(dto) == 1 ? true : false;
    }

    @Override
    public FaqMedCasePatient selectKbPat(Long idMedCase) {
        return pfKbPartDao.selectKbPat(idMedCase);
    }

    @Override
    public Long countExams(PfPartCommonDto dto) {
        return pfKbPartDao.countExams(dto);
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
    public Long countChecks(PfPartCommonDto dto) {
        return pfKbPartDao.countChecks(dto);
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
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdMedCase() == null) {
            FaqMedTag tagDto = new FaqMedTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqMedTag tagVo = pfCaseHistoryDao.selectMedTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqMedCase faqMedCase = new FaqMedCase();
                faqMedCase.setIdMedCase(dto.getOldIdMedCase());
                faqMedCase.setCreator(dto.getCreator());
                faqMedCase.setOperator(dto.getCreator());
                faqMedCase.setName(dto.getCaseName());
                faqMedCase.setIdOrg(dto.getIdOrg());
                faqMedCase.setFgActive(YesOrNoNum.YES.getCode());
                faqMedCase.setCdMedAsse("005");
                faqMedCase.setFgPublic(YesOrNoNum.NO.getCode());
                pfKbPartDao.addKbPart(faqMedCase);

                dto.setIdMedCase(faqMedCase.getIdMedCase());

                // 3 保存病例标签
                dto.setIdMedCase(faqMedCase.getIdMedCase());
                pfCaseHistoryDao.saveMedTag(dto);
            } else {
                dto.setIdMedCase(tagVo.getIdMedCase());
            }
        }
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean bachAddCons(PfSaveAsMedDto dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdMedCase() == null) {
            FaqMedTag tagDto = new FaqMedTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqMedTag tagVo = pfCaseHistoryDao.selectMedTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqMedCase faqMedCase = new FaqMedCase();
                faqMedCase.setIdMedCase(dto.getOldIdMedCase());
                faqMedCase.setCreator(dto.getCreator());
                faqMedCase.setOperator(dto.getCreator());
                faqMedCase.setName(dto.getCaseName());
                faqMedCase.setIdOrg(dto.getIdOrg());
                faqMedCase.setFgActive(YesOrNoNum.YES.getCode());
                faqMedCase.setCdMedAsse("004");
                faqMedCase.setFgPublic(YesOrNoNum.NO.getCode());
                pfKbPartDao.addKbPart(faqMedCase);

                dto.setExtId(faqMedCase.getIdMedCase());

                // 3 保存病例标签
                dto.setIdMedCase(faqMedCase.getIdMedCase());
                pfCaseHistoryDao.saveMedTag(dto);
            } else {
                dto.setExtId(tagVo.getIdMedCase());
            }
        }
        if (dto.getExtType().equals(YesOrNoNum.YES.getCode())) {
            // 全部引入
            List<FaqMedCaseInquesList> oldDatas = pfKbPartDao.selectOldConsRecord(dto.getExtId());
            List<FaqMedCaseInquesList> allDatas = pfKbPartDao.selectAllConsRecord(dto.getExtId());
            allDatas.removeIf(allData -> {
                boolean flag = false;
                for (FaqMedCaseInquesList oldData : oldDatas) {
                    if (oldData.getIdInques().equals(allData.getIdInques())) {
                        flag = true;
                    }
                }
                return flag;
            });
            if (CollectionUtils.isEmpty(allDatas)) {
                return true;
            }
            return pfKbPartDao.bachAddAllCons(allDatas) >= 1 ? true : false;
        }
        return pfKbPartDao.bachAddCons(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean bachAddCheck(PfSaveAsMedDto dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdMedCase() == null) {
            FaqMedTag tagDto = new FaqMedTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqMedTag tagVo = pfCaseHistoryDao.selectMedTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqMedCase faqMedCase = new FaqMedCase();
                faqMedCase.setIdMedCase(dto.getOldIdMedCase());
                faqMedCase.setCreator(dto.getCreator());
                faqMedCase.setOperator(dto.getCreator());
                faqMedCase.setName(dto.getCaseName());
                faqMedCase.setIdOrg(dto.getIdOrg());
                faqMedCase.setFgActive(YesOrNoNum.YES.getCode());
                faqMedCase.setCdMedAsse("005");
                faqMedCase.setFgPublic(YesOrNoNum.NO.getCode());
                pfKbPartDao.addKbPart(faqMedCase);

                dto.setExtId(faqMedCase.getIdMedCase());

                // 3 保存病例标签
                dto.setIdMedCase(faqMedCase.getIdMedCase());
                pfCaseHistoryDao.saveMedTag(dto);
            } else {
                dto.setExtId(tagVo.getIdMedCase());
            }
        }
        if (dto.getExtType().equals(YesOrNoNum.YES.getCode())) {
            // 全部引入
            List<FaqMedCaseBodyList> oldDatas = pfKbPartDao.selectOldCheckRecord(dto.getExtId());
            List<FaqMedCaseBodyList> allDatas = pfKbPartDao.selectAllCheckRecord(dto.getExtId());
            allDatas.removeIf(allData -> {
                boolean flag = false;
                for (FaqMedCaseBodyList oldData : oldDatas) {
                    if (oldData.getIdBody().equals(allData.getIdBody())) {
                        flag = true;
                    }
                }
                return flag;
            });
            if (CollectionUtils.isEmpty(allDatas)) {
                return true;
            }
            return pfKbPartDao.bachAddAllCheck(allDatas) >= 1 ? true : false;
        }
        return pfKbPartDao.bachAddCheck(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean bachAddExam(PfSaveAsMedDto dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdMedCase() == null) {
            FaqMedTag tagDto = new FaqMedTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqMedTag tagVo = pfCaseHistoryDao.selectMedTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqMedCase faqMedCase = new FaqMedCase();
                faqMedCase.setIdMedCase(dto.getOldIdMedCase());
                faqMedCase.setCreator(dto.getCreator());
                faqMedCase.setOperator(dto.getCreator());
                faqMedCase.setName(dto.getCaseName());
                faqMedCase.setIdOrg(dto.getIdOrg());
                faqMedCase.setFgActive(YesOrNoNum.YES.getCode());
                faqMedCase.setCdMedAsse("006");
                faqMedCase.setFgPublic(YesOrNoNum.NO.getCode());
                pfKbPartDao.addKbPart(faqMedCase);

                dto.setExtId(faqMedCase.getIdMedCase());

                // 3 保存病例标签
                dto.setIdMedCase(faqMedCase.getIdMedCase());
                pfCaseHistoryDao.saveMedTag(dto);
            } else {
                dto.setExtId(tagVo.getIdMedCase());
            }
        }
        if (dto.getExtType().equals(YesOrNoNum.YES.getCode())) {
            // 全部引入
            List<FaqMedCaseInspectList> oldDatas = pfKbPartDao.selectOldExamRecord(dto.getExtId());
            List<FaqMedCaseInspectList> allDatas = pfKbPartDao.selectAllExamRecord(dto.getExtId());
            allDatas.removeIf(allData -> {
                boolean flag = false;
                for (FaqMedCaseInspectList oldData : oldDatas) {
                    if (oldData.getIdInspectItem().equals(allData.getIdInspectItem())) {
                        flag = true;
                    }
                }
                return flag;
            });
            if (CollectionUtils.isEmpty(allDatas)) {
                return true;
            }
            return pfKbPartDao.bachAddAllExam(allDatas) >= 1 ? true : false;
        }
        return pfKbPartDao.bachAddExam(dto) >= 1 ? true : false;
    }
}
