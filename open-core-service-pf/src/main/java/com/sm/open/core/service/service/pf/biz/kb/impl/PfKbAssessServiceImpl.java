package com.sm.open.core.service.service.pf.biz.kb.impl;

import com.sm.open.care.core.enums.YesOrNoNum;
import com.sm.open.core.dal.pf.biz.kb.PfCaseHistoryDao;
import com.sm.open.core.dal.pf.biz.kb.PfKbAssessDao;
import com.sm.open.core.model.dto.pf.biz.kb.assess.*;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import com.sm.open.core.service.service.pf.biz.kb.PfKbAssessService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PfKbAssessServiceImpl implements PfKbAssessService {

    @Resource
    private PfKbAssessDao pfKbAssessDao;

    @Resource
    private PfCaseHistoryDao pfCaseHistoryDao;

    @Override
    public Long countKbAssess(PfEvaCaseDto dto) {
        return pfKbAssessDao.countKbAssess(dto);
    }

    @Override
    public List<FaqEvaCase> listKbAssess(PfEvaCaseDto dto) {
        return pfKbAssessDao.listKbAssess(dto);
    }

    @Override
    public Long saveKbAssess(FaqEvaCase dto) {
        Integer num;
        if (dto.getIdEvaCase() == null) {
            dto.setFgPublic(YesOrNoNum.YES.getCode());
            num = pfKbAssessDao.addKbAssess(dto);
        } else {
            num = pfKbAssessDao.editKbAssess(dto);
        }
        return num == 1 ? dto.getIdEvaCase() : null;
    }

    @Override
    public boolean delKbAssess(PfBachChangeStatusDto dto) {
        return pfKbAssessDao.delKbAssess(dto) >= 1 ? true : false;
    }

    @Override
    public List<FaqEvaCaseItem> listKbReferral(PfAssessCommonDto dto) {
        return pfKbAssessDao.listKbReferral(dto);
    }

    @Override
    public List<FaqEvaCaseItemReferral> listReferral(PfAssessCommonDto dto) {
        return pfKbAssessDao.listReferral(dto);
    }

    @Override
    public boolean delReferral(PfBachChangeStatusDto dto) {
        return pfKbAssessDao.delReferral(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveReferral(PfAssessReferralDto dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdEvaCase() == null) {

            FaqEvaTag tagDto = new FaqEvaTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqEvaTag tagVo = pfCaseHistoryDao.selectEvaTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqEvaCase faqEvaCase = new FaqEvaCase();
                faqEvaCase.setIdEvaCase(dto.getOldIdEvaCase());
                faqEvaCase.setCreator(dto.getCreator());
                faqEvaCase.setOperator(dto.getCreator());
                faqEvaCase.setName(dto.getCaseName());
                faqEvaCase.setIdOrg(dto.getIdOrg());
                faqEvaCase.setCdEvaAsse("001");
                faqEvaCase.setFgPublic(YesOrNoNum.NO.getCode());
                faqEvaCase.setFgActive(YesOrNoNum.YES.getCode());
                faqEvaCase.setFgGroup(YesOrNoNum.YES.getCode());
                pfKbAssessDao.addKbAssess(faqEvaCase);

                dto.setIdEvaCase(faqEvaCase.getIdEvaCase());

                // 3 保存病例标签
                FaqEvaTag faqEvaTag = new FaqEvaTag();
                faqEvaTag.setIdMedicalrec(dto.getIdMedicalrec());
                faqEvaTag.setIdTag(dto.getIdTag());
                faqEvaTag.setIdEvaCase(faqEvaCase.getIdEvaCase());
                pfCaseHistoryDao.saveEvaTag(faqEvaTag);
            } else {
                dto.setIdEvaCase(tagVo.getIdEvaCase());
            }
        }

        if (dto.getIdEvaCaseItem() == null) {
            pfKbAssessDao.addItem(dto);
        } else {
            pfKbAssessDao.editItem(dto);
        }
        List<FaqEvaCaseItemReferral> list = dto.getList();
        if (!CollectionUtils.isEmpty(list)) {
            List<String> list2 = new ArrayList<>();
            list2.add(null);
            list.removeAll(list2);

            for (FaqEvaCaseItemReferral faqEvaCaseItemReferral : list) {
                faqEvaCaseItemReferral.setSdEvaReferral(dto.getSdEva());
                faqEvaCaseItemReferral.setIdEvaCaseItem(dto.getIdEvaCaseItem());
                if (faqEvaCaseItemReferral.getIdEvaCaseItemList() == null) {
                    pfKbAssessDao.addReferral(faqEvaCaseItemReferral);
                } else {
                    pfKbAssessDao.editReferral(faqEvaCaseItemReferral);
                }
            }
        }
        return dto.getIdEvaCaseItem();
    }

    @Override
    public List<FaqEvaCaseItem> listKbDiagnosis(PfAssessCommonDto dto) {
        return pfKbAssessDao.listKbDiagnosis(dto);
    }

    @Override
    public List<FaqEvaCaseItemDiagnosis> listDiagnosisAnswer(PfAssessCommonDto dto) {
        return pfKbAssessDao.listDiagnosisAnswer(dto);
    }

    @Override
    public boolean delDiagnosis(PfBachChangeStatusDto dto) {
        return pfKbAssessDao.delDiagnosis(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveDiagnosis(PfAssessDiagnosisDto dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdEvaCase() == null) {
            FaqEvaTag tagDto = new FaqEvaTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqEvaTag tagVo = pfCaseHistoryDao.selectEvaTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqEvaCase faqEvaCase = new FaqEvaCase();
                faqEvaCase.setIdEvaCase(dto.getOldIdEvaCase());
                faqEvaCase.setCreator(dto.getCreator());
                faqEvaCase.setOperator(dto.getCreator());
                faqEvaCase.setName(dto.getCaseName());
                faqEvaCase.setIdOrg(dto.getIdOrg());
                faqEvaCase.setCdEvaAsse("002");
                faqEvaCase.setFgPublic(YesOrNoNum.NO.getCode());
                faqEvaCase.setFgActive(YesOrNoNum.YES.getCode());
                faqEvaCase.setFgGroup(YesOrNoNum.YES.getCode());
                pfKbAssessDao.addKbAssess(faqEvaCase);

                dto.setIdEvaCase(faqEvaCase.getIdEvaCase());

                // 3 保存病例标签
                FaqEvaTag faqEvaTag = new FaqEvaTag();
                faqEvaTag.setIdMedicalrec(dto.getIdMedicalrec());
                faqEvaTag.setIdTag(dto.getIdTag());
                faqEvaTag.setIdEvaCase(faqEvaCase.getIdEvaCase());
                pfCaseHistoryDao.saveEvaTag(faqEvaTag);
            } else {
                dto.setIdEvaCase(tagVo.getIdEvaCase());
            }
        }

        if (dto.getIdEvaCaseItem() == null) {
            pfKbAssessDao.addItem(dto);
        } else {
            pfKbAssessDao.editItem(dto);
        }
        List<FaqEvaCaseItemDiagnosis> list = dto.getList();
        if (!CollectionUtils.isEmpty(list)) {
            List<String> list2 = new ArrayList<>();
            list2.add(null);
            list.removeAll(list2);

            for (FaqEvaCaseItemDiagnosis faqEvaCaseItemDiagnosis : list) {
                faqEvaCaseItemDiagnosis.setIdEvaCaseItem(dto.getIdEvaCaseItem());
                String fgDieMain = "0";
                if (StringUtils.isNotBlank(faqEvaCaseItemDiagnosis.getFgDieMain())) {
                    fgDieMain = faqEvaCaseItemDiagnosis.getFgDieMain();
                }
                faqEvaCaseItemDiagnosis.setFgDieMain(fgDieMain);
                if (faqEvaCaseItemDiagnosis.getIdEvaCaseItemList() == null) {
                    pfKbAssessDao.addDiagnosis(faqEvaCaseItemDiagnosis);
                } else {
                    pfKbAssessDao.editDiagnosis(faqEvaCaseItemDiagnosis);
                }
            }
        }
        return dto.getIdEvaCaseItem();
    }

    @Override
    public List<FaqEvaCaseItem> listKbReason(PfAssessCommonDto dto) {
        return pfKbAssessDao.listKbReason(dto);
    }

    @Override
    public List<FaqEvaCaseItemReason> listReasonAnswer(PfAssessCommonDto dto) {
        return pfKbAssessDao.listReasonAnswer(dto);
    }

    @Override
    public boolean delReason(PfBachChangeStatusDto dto) {
        return pfKbAssessDao.delReason(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveReason(PfAssessReasonDto dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdEvaCase() == null) {

            FaqEvaTag tagDto = new FaqEvaTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqEvaTag tagVo = pfCaseHistoryDao.selectEvaTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqEvaCase faqEvaCase = new FaqEvaCase();
                faqEvaCase.setIdEvaCase(dto.getOldIdEvaCase());
                faqEvaCase.setCreator(dto.getCreator());
                faqEvaCase.setOperator(dto.getCreator());
                faqEvaCase.setName(dto.getCaseName());
                faqEvaCase.setIdOrg(dto.getIdOrg());
                faqEvaCase.setCdEvaAsse("003");
                faqEvaCase.setFgPublic(YesOrNoNum.NO.getCode());
                faqEvaCase.setFgActive(YesOrNoNum.YES.getCode());
                faqEvaCase.setFgGroup(YesOrNoNum.YES.getCode());
                pfKbAssessDao.addKbAssess(faqEvaCase);

                dto.setIdEvaCase(faqEvaCase.getIdEvaCase());

                // 3 保存病例标签
                FaqEvaTag faqEvaTag = new FaqEvaTag();
                faqEvaTag.setIdMedicalrec(dto.getIdMedicalrec());
                faqEvaTag.setIdTag(dto.getIdTag());
                faqEvaTag.setIdEvaCase(faqEvaCase.getIdEvaCase());
                pfCaseHistoryDao.saveEvaTag(faqEvaTag);
            } else {
                dto.setIdEvaCase(tagVo.getIdEvaCase());
            }
        }
        if (dto.getIdEvaCaseItem() == null) {
            pfKbAssessDao.addItem(dto);
        } else {
            pfKbAssessDao.editItem(dto);
        }
        List<FaqEvaCaseItemReason> list = dto.getList();
        if (!CollectionUtils.isEmpty(list)) {
            List<String> list2 = new ArrayList<>();
            list2.add(null);
            list.removeAll(list2);

            for (FaqEvaCaseItemReason faqEvaCaseItemReason : list) {
                faqEvaCaseItemReason.setSdEvaEffciency(dto.getSdEva());
                faqEvaCaseItemReason.setIdEvaCaseItem(dto.getIdEvaCaseItem());
                faqEvaCaseItemReason.setIdDie(dto.getIdDie());
                if (faqEvaCaseItemReason.getIdEvaCaseItemList() == null) {
                    pfKbAssessDao.saveReason(faqEvaCaseItemReason);
                } else {
                    pfKbAssessDao.editReason(faqEvaCaseItemReason);
                }
            }
        }
        // 删除无效的等效答案数据（从病例引入的数据）
        if (dto.getFromCaseFlag() != 1) {
            pfKbAssessDao.delFromCaseDefaultReason(dto.getIdEvaCaseItem());
        }
        return dto.getIdEvaCaseItem();
    }

    @Override
    public List<FaqEvaCaseItem> listKbCover(PfAssessCommonDto dto) {
        return pfKbAssessDao.listKbCover(dto);
    }

    @Override
    public List<FaqEvaCaseItemCover> listCoverAnswer(PfAssessCommonDto dto) {
        return pfKbAssessDao.listCoverAnswer(dto);
    }

    @Override
    public boolean delCover(PfBachChangeStatusDto dto) {
        return pfKbAssessDao.delCover(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveCover(PfAssessCoverDto dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdEvaCase() == null) {

            FaqEvaTag tagDto = new FaqEvaTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqEvaTag tagVo = pfCaseHistoryDao.selectEvaTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqEvaCase faqEvaCase = new FaqEvaCase();
                faqEvaCase.setIdEvaCase(dto.getOldIdEvaCase());
                faqEvaCase.setCreator(dto.getCreator());
                faqEvaCase.setOperator(dto.getCreator());
                faqEvaCase.setName(dto.getCaseName());
                faqEvaCase.setIdOrg(dto.getIdOrg());
                faqEvaCase.setCdEvaAsse("004");
                faqEvaCase.setFgPublic(YesOrNoNum.NO.getCode());
                faqEvaCase.setFgActive(YesOrNoNum.YES.getCode());
                faqEvaCase.setFgGroup(YesOrNoNum.YES.getCode());
                pfKbAssessDao.addKbAssess(faqEvaCase);

                dto.setIdEvaCase(faqEvaCase.getIdEvaCase());

                // 3 保存病例标签
                FaqEvaTag faqEvaTag = new FaqEvaTag();
                faqEvaTag.setIdMedicalrec(dto.getIdMedicalrec());
                faqEvaTag.setIdTag(dto.getIdTag());
                faqEvaTag.setIdEvaCase(faqEvaCase.getIdEvaCase());
                pfCaseHistoryDao.saveEvaTag(faqEvaTag);
            } else {
                dto.setIdEvaCase(tagVo.getIdEvaCase());
            }
        }

        if (dto.getIdEvaCaseItem() == null) {
            pfKbAssessDao.addItem(dto);
        } else {
            pfKbAssessDao.editItem(dto);
        }
        List<FaqEvaCaseItemCover> list = dto.getList();
        if (!CollectionUtils.isEmpty(list)) {
            List<String> list2 = new ArrayList<>();
            list2.add(null);
            list.removeAll(list2);

            for (FaqEvaCaseItemCover faqEvaCaseItemCover : list) {
                faqEvaCaseItemCover.setSdEvaCover(dto.getSdEva());
                faqEvaCaseItemCover.setIdDie(dto.getIdDie());
                faqEvaCaseItemCover.setIdEvaCaseItem(dto.getIdEvaCaseItem());
                if (faqEvaCaseItemCover.getIdEvaCaseItemList() == null) {
                    pfKbAssessDao.saveCover(faqEvaCaseItemCover);
                } else {
                    pfKbAssessDao.editCover(faqEvaCaseItemCover);
                }
            }
        }
        // 删除无效的等效答案数据（从病例引入的数据）
        if (dto.getFromCaseFlag() != 1) {
            pfKbAssessDao.delFromCaseDefaultCover(dto.getIdEvaCaseItem());
        }
        return dto.getIdEvaCaseItem();
    }

    @Override
    public List<FaqEvaCaseItem> listKbMust(PfAssessCommonDto dto) {
        return pfKbAssessDao.listKbMust(dto);
    }

    @Override
    public List<FaqEvaCaseItemMust> listMustAnswer(PfAssessCommonDto dto) {
        return pfKbAssessDao.listMustAnswer(dto);
    }

    @Override
    public boolean delMust(PfBachChangeStatusDto dto) {
        return pfKbAssessDao.delMust(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveMust(PfAssessMustDto dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdEvaCase() == null) {

            FaqEvaTag tagDto = new FaqEvaTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqEvaTag tagVo = pfCaseHistoryDao.selectEvaTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqEvaCase faqEvaCase = new FaqEvaCase();
                faqEvaCase.setIdEvaCase(dto.getOldIdEvaCase());
                faqEvaCase.setCreator(dto.getCreator());
                faqEvaCase.setOperator(dto.getCreator());
                faqEvaCase.setName(dto.getCaseName());
                faqEvaCase.setIdOrg(dto.getIdOrg());
                faqEvaCase.setCdEvaAsse("005");
                faqEvaCase.setFgPublic(YesOrNoNum.NO.getCode());
                faqEvaCase.setFgActive(YesOrNoNum.YES.getCode());
                faqEvaCase.setFgGroup(YesOrNoNum.YES.getCode());
                pfKbAssessDao.addKbAssess(faqEvaCase);

                dto.setIdEvaCase(faqEvaCase.getIdEvaCase());

                // 3 保存病例标签
                FaqEvaTag faqEvaTag = new FaqEvaTag();
                faqEvaTag.setIdMedicalrec(dto.getIdMedicalrec());
                faqEvaTag.setIdTag(dto.getIdTag());
                faqEvaTag.setIdEvaCase(faqEvaCase.getIdEvaCase());
                pfCaseHistoryDao.saveEvaTag(faqEvaTag);
            } else {
                dto.setIdEvaCase(tagVo.getIdEvaCase());
            }
        }

        if (dto.getIdEvaCaseItem() == null) {
            pfKbAssessDao.addItem(dto);
        } else {
            pfKbAssessDao.editItem(dto);
        }
        List<FaqEvaCaseItemMust> list = dto.getList();
        if (!CollectionUtils.isEmpty(list)) {
            List<String> list2 = new ArrayList<>();
            list2.add(null);
            list.removeAll(list2);

            for (FaqEvaCaseItemMust faqEvaCaseItemMust : list) {
                faqEvaCaseItemMust.setSdEvaMust(dto.getSdEva());
                faqEvaCaseItemMust.setIdDie(dto.getIdDie());
                faqEvaCaseItemMust.setIdEvaCaseItem(dto.getIdEvaCaseItem());
                if (faqEvaCaseItemMust.getIdEvaCaseItemList() == null) {
                    pfKbAssessDao.saveMust(faqEvaCaseItemMust);
                } else {
                    pfKbAssessDao.editMust(faqEvaCaseItemMust);
                }
            }
        }
        // 删除无效的等效答案数据（从病例引入的数据）
        if (dto.getFromCaseFlag() != 1) {
            pfKbAssessDao.delFromCaseDefaultMust(dto.getIdEvaCaseItem());
        }
        return dto.getIdEvaCaseItem();
    }

    @Override
    public List<FaqEvaCaseItem> listKbThorough(PfAssessCommonDto dto) {
        return pfKbAssessDao.listKbThorough(dto);
    }

    @Override
    public List<FaqEvaCaseItemThorough> listThoroughAnswer(PfAssessCommonDto dto) {
        return pfKbAssessDao.listThoroughAnswer(dto);
    }

    @Override
    public boolean delThorough(PfBachChangeStatusDto dto) {
        return pfKbAssessDao.delThorough(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveThorough(PfAssessThoroughDto dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdEvaCase() == null) {

            FaqEvaTag tagDto = new FaqEvaTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqEvaTag tagVo = pfCaseHistoryDao.selectEvaTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqEvaCase faqEvaCase = new FaqEvaCase();
                faqEvaCase.setIdEvaCase(dto.getOldIdEvaCase());
                faqEvaCase.setCreator(dto.getCreator());
                faqEvaCase.setOperator(dto.getCreator());
                faqEvaCase.setName(dto.getCaseName());
                faqEvaCase.setIdOrg(dto.getIdOrg());
                faqEvaCase.setCdEvaAsse("010");
                faqEvaCase.setFgPublic(YesOrNoNum.NO.getCode());
                faqEvaCase.setFgActive(YesOrNoNum.YES.getCode());
                faqEvaCase.setFgGroup(YesOrNoNum.YES.getCode());
                pfKbAssessDao.addKbAssess(faqEvaCase);

                dto.setIdEvaCase(faqEvaCase.getIdEvaCase());

                // 3 保存病例标签
                FaqEvaTag faqEvaTag = new FaqEvaTag();
                faqEvaTag.setIdMedicalrec(dto.getIdMedicalrec());
                faqEvaTag.setIdTag(dto.getIdTag());
                faqEvaTag.setIdEvaCase(faqEvaCase.getIdEvaCase());
                pfCaseHistoryDao.saveEvaTag(faqEvaTag);
            } else {
                dto.setIdEvaCase(tagVo.getIdEvaCase());
            }
        }

        if (dto.getIdEvaCaseItem() == null) {
            pfKbAssessDao.addItem(dto);
        } else {
            pfKbAssessDao.editItem(dto);
        }
        List<FaqEvaCaseItemThorough> list = dto.getList();
        if (!CollectionUtils.isEmpty(list)) {
            List<String> list2 = new ArrayList<>();
            list2.add(null);
            list.removeAll(list2);

            for (FaqEvaCaseItemThorough faqEvaCaseItemThorough : list) {
                faqEvaCaseItemThorough.setSdEvaMust(dto.getSdEva());
                faqEvaCaseItemThorough.setIdEvaCaseItem(dto.getIdEvaCaseItem());
                if (faqEvaCaseItemThorough.getIdEvaCaseItemList() == null) {
                    pfKbAssessDao.saveThorough(faqEvaCaseItemThorough);
                } else {
                    pfKbAssessDao.editThorough(faqEvaCaseItemThorough);
                }
            }
        }
        // 删除无效的等效答案数据（从病例引入的数据）
        if (dto.getFromCaseFlag() != 1) {
            pfKbAssessDao.delFromCaseDefaultThorough(dto.getIdEvaCaseItem());
        }
        return dto.getIdEvaCaseItem();
    }

    @Override
    public List<FaqEvaCaseItem> listKbEffciency(PfAssessCommonDto dto) {
        return pfKbAssessDao.listKbEffciency(dto);
    }

    @Override
    public boolean delEffciency(PfBachChangeStatusDto dto) {
        return pfKbAssessDao.delEffciency(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveEffciency(PfAssessEffciencyDto dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdEvaCase() == null) {

            FaqEvaTag tagDto = new FaqEvaTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqEvaTag tagVo = pfCaseHistoryDao.selectEvaTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqEvaCase faqEvaCase = new FaqEvaCase();
                faqEvaCase.setIdEvaCase(dto.getOldIdEvaCase());
                faqEvaCase.setCreator(dto.getCreator());
                faqEvaCase.setOperator(dto.getCreator());
                faqEvaCase.setName(dto.getCaseName());
                faqEvaCase.setIdOrg(dto.getIdOrg());
                faqEvaCase.setCdEvaAsse("006");
                faqEvaCase.setFgPublic(YesOrNoNum.NO.getCode());
                faqEvaCase.setFgActive(YesOrNoNum.YES.getCode());
                faqEvaCase.setFgGroup(YesOrNoNum.YES.getCode());
                pfKbAssessDao.addKbAssess(faqEvaCase);

                dto.setIdEvaCase(faqEvaCase.getIdEvaCase());

                // 3 保存病例标签
                FaqEvaTag faqEvaTag = new FaqEvaTag();
                faqEvaTag.setIdMedicalrec(dto.getIdMedicalrec());
                faqEvaTag.setIdTag(dto.getIdTag());
                faqEvaTag.setIdEvaCase(faqEvaCase.getIdEvaCase());
                pfCaseHistoryDao.saveEvaTag(faqEvaTag);
            } else {
                dto.setIdEvaCase(tagVo.getIdEvaCase());
            }
        }

        if (dto.getIdEvaCaseItem() == null) {
            pfKbAssessDao.addItem(dto);
        } else {
            pfKbAssessDao.editItem(dto);
        }

        if (dto.getIdEvaCaseItemList() == null) {
            pfKbAssessDao.saveEffciency(dto);
        } else {
            pfKbAssessDao.editEffciency(dto);
        }

        return dto.getIdEvaCaseItem();
    }

    @Override
    public List<FaqEvaCaseItem> listKbOrder(PfAssessCommonDto dto) {
        return pfKbAssessDao.listKbOrder(dto);
    }

    @Override
    public List<FaqEvaCaseItemOrder> listOrderAnswer(PfAssessCommonDto dto) {
        return pfKbAssessDao.listOrderAnswer(dto);
    }

    @Override
    public boolean delOrder(PfBachChangeStatusDto dto) {
        return pfKbAssessDao.delOrder(dto) >= 1 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveOrder(PfAssessOrderDto dto) {
        if (StringUtils.isNotBlank(dto.getTagFlag())
                && dto.getTagFlag().equals(YesOrNoNum.YES.getCode()) && dto.getIdEvaCase() == null) {

            FaqEvaTag tagDto = new FaqEvaTag();
            tagDto.setIdMedicalrec(dto.getIdMedicalrec());
            tagDto.setIdTag(dto.getIdTag());
            FaqEvaTag tagVo = pfCaseHistoryDao.selectEvaTag(tagDto);
            if (tagVo == null) {
                // 直接新增
                FaqEvaCase faqEvaCase = new FaqEvaCase();
                faqEvaCase.setIdEvaCase(dto.getOldIdEvaCase());
                faqEvaCase.setCreator(dto.getCreator());
                faqEvaCase.setOperator(dto.getCreator());
                faqEvaCase.setName(dto.getCaseName());
                faqEvaCase.setIdOrg(dto.getIdOrg());
                faqEvaCase.setCdEvaAsse("007");
                faqEvaCase.setFgPublic(YesOrNoNum.NO.getCode());
                faqEvaCase.setFgActive(YesOrNoNum.YES.getCode());
                faqEvaCase.setFgGroup(YesOrNoNum.YES.getCode());
                pfKbAssessDao.addKbAssess(faqEvaCase);

                dto.setIdEvaCase(faqEvaCase.getIdEvaCase());

                // 3 保存病例标签
                FaqEvaTag faqEvaTag = new FaqEvaTag();
                faqEvaTag.setIdMedicalrec(dto.getIdMedicalrec());
                faqEvaTag.setIdTag(dto.getIdTag());
                faqEvaTag.setIdEvaCase(faqEvaCase.getIdEvaCase());
                pfCaseHistoryDao.saveEvaTag(faqEvaTag);
            } else {
                dto.setIdEvaCase(tagVo.getIdEvaCase());
            }
        }

        if (dto.getIdEvaCaseItem() == null) {
            pfKbAssessDao.addItem(dto);
        } else {
            pfKbAssessDao.editItem(dto);
        }
        List<FaqEvaCaseItemOrder> list = dto.getList();
        if (!CollectionUtils.isEmpty(list)) {
            List<String> list2 = new ArrayList<>();
            list2.add(null);
            list.removeAll(list2);

            for (FaqEvaCaseItemOrder itemOrder : list) {
                itemOrder.setSdEvaOrder(dto.getSdEva());
                itemOrder.setIdEvaCaseItem(dto.getIdEvaCaseItem());
                if (itemOrder.getIdEvaCaseItemList() == null) {
                    pfKbAssessDao.saveOrder(itemOrder);
                } else {
                    pfKbAssessDao.editOrder(itemOrder);
                }
            }
        }
        return dto.getIdEvaCaseItem();
    }

    @Override
    public boolean delCommonAssess(PfBachChangeStatusDto dto) {
        pfKbAssessDao.delCommonAssess(dto);
        return true;
    }
}
