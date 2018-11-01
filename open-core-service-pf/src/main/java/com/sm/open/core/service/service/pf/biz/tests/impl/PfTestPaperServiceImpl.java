package com.sm.open.core.service.service.pf.biz.tests.impl;

import com.sm.open.core.dal.pf.biz.tests.PfTestPaperDao;
import com.sm.open.core.model.dto.pf.biz.tests.PfAddCaseDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestPaperDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.entity.ExmTestpaper;
import com.sm.open.core.model.entity.ExmTestpaperCa;
import com.sm.open.core.model.entity.ExmTestpaperMedicalrec;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import com.sm.open.core.service.service.pf.biz.tests.PfTestPaperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PfTestPaperServiceImpl implements PfTestPaperService {

    @Resource
    private PfTestPaperDao pfTestPaperDao;

    @Override
    public List<PfCommonZtreeVo> listPaperClassifyTree(Long idOrg) {
        return pfTestPaperDao.listPaperClassifyTree(idOrg);
    }

    @Override
    public Long savePaperClassify(ExmTestpaperCa dto) {
        if (dto.getIdTestpaperca() == null) {
            pfTestPaperDao.addPaperClassify(dto);
        } else {
            pfTestPaperDao.editPaperClassify(dto);
        }
        return dto.getIdTestpaperca();
    }

    @Override
    public boolean delPaperClassify(PfBachChangeStatusDto dto) {
        return pfTestPaperDao.delPaperClassify(dto) >= 1 ? true : false;
    }

    @Override
    public Long countPaper(PfTestPaperDto dto) {
        return pfTestPaperDao.countPaper(dto);
    }

    @Override
    public List<ExmTestpaper> listPaper(PfTestPaperDto dto) {
        return pfTestPaperDao.listPaper(dto);
    }

    @Override
    public Long savePaper(ExmTestpaper dto) {
        if (dto.getIdTestpaper() == null) {
            pfTestPaperDao.addPaper(dto);
        } else {
            pfTestPaperDao.editPaper(dto);
        }
        return dto.getIdTestpaper();
    }

    @Override
    public boolean delPaper(PfBachChangeStatusDto dto) {
        return pfTestPaperDao.delPaper(dto) >= 1 ? true : false;
    }


    @Override
    public List<PfCommonZtreeVo> listCaseTree(PfCatalogueTreeDto dto) {
        return pfTestPaperDao.listCaseTree(dto);
    }

    @Override
    public List<ExmTestpaperMedicalrec> listPaperItem(PfTestPaperDto dto) {
        return pfTestPaperDao.listPaperItem(dto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addPaperItem(PfAddCaseDto dto) {
        List<Long> list = dto.getList();
        List<ExmTestpaperMedicalrec> oldPapers = pfTestPaperDao.listAllPaperItem(dto.getIdTestpaper());
        pfTestPaperDao.delAllPaperItem(dto.getIdTestpaper());
        for (Long id : list) {
            ExmTestpaperMedicalrec paperItem = new ExmTestpaperMedicalrec();
            paperItem.setIdTestpaper(dto.getIdTestpaper());
            paperItem.setIdMedicalrec(id);
            for (ExmTestpaperMedicalrec oldPaper : oldPapers) {
                if (oldPaper.getIdMedicalrec().equals(id)) {
                    paperItem.setSort(oldPaper.getSort());
                }
            }
            pfTestPaperDao.addPaperItem(paperItem);
        }
        return true;
    }

    @Override
    public boolean delPaperItem(PfBachChangeStatusDto dto) {
        return pfTestPaperDao.delPaperItem(dto) >= 1 ? true : false;
    }

    @Override
    public boolean updatePaperItemSort(ExmTestpaperMedicalrec dto) {
        return pfTestPaperDao.updatePaperItemSort(dto) >= 1 ? true : false;
    }
}
