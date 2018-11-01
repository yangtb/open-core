package com.sm.open.core.service.service.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.PfAddCaseDto;
import com.sm.open.core.model.dto.pf.biz.tests.PfTestPaperDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.entity.ExmTestpaper;
import com.sm.open.core.model.entity.ExmTestpaperCa;
import com.sm.open.core.model.entity.ExmTestpaperMedicalrec;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;

import java.util.List;

/**
 * @ClassName: PfTestPaperService
 * @Description: 试卷定义service
 * @Author yangtongbin
 * @Date 2018/10/31
 */
public interface PfTestPaperService {

    /**
     * Paper分类树
     *
     * @return
     */
    List<PfCommonZtreeVo> listPaperClassifyTree(Long idOrg);


    /**
     * 保存Paper分类信息
     *
     * @param dto
     * @return
     */
    Long savePaperClassify(ExmTestpaperCa dto);

    /**
     * 删除Paper分类信息
     *
     * @param dto
     * @return
     */
    boolean delPaperClassify(PfBachChangeStatusDto dto);

    /**
     * 试卷总数
     *
     * @param dto
     * @return
     */
    Long countPaper(PfTestPaperDto dto);

    /**
     * 试卷列表
     *
     * @param dto
     * @return
     */
    List<ExmTestpaper> listPaper(PfTestPaperDto dto);

    /**
     * 新增试卷信息
     *
     * @param dto
     * @return
     */
    Long savePaper(ExmTestpaper dto);

    /**
     * 删除试卷信息
     *
     * @param dto
     * @return
     */
    boolean delPaper(PfBachChangeStatusDto dto);

    /**
     * 病例树
     *
     * @return
     */
    List<PfCommonZtreeVo> listCaseTree(PfCatalogueTreeDto dto);

    /**
     * 试题清单列表
     *
     * @param dto
     * @return
     */
    List<ExmTestpaperMedicalrec> listPaperItem(PfTestPaperDto dto);

    /**
     * 添加试题清单
     *
     * @param dto
     * @return
     */
    boolean addPaperItem(PfAddCaseDto dto);

    /**
     * 删除试题清单
     *
     * @param dto
     * @return
     */
    boolean delPaperItem(PfBachChangeStatusDto dto);

    /**
     * 修改试题清单排序
     *
     * @param dto
     * @return
     */
    boolean updatePaperItemSort(ExmTestpaperMedicalrec dto);

}
