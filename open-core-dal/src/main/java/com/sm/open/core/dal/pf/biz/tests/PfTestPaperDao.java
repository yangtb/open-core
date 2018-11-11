package com.sm.open.core.dal.pf.biz.tests;

import com.sm.open.core.model.dto.pf.biz.tests.PfTestPaperDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.entity.ExmTestpaper;
import com.sm.open.core.model.entity.ExmTestpaperCa;
import com.sm.open.core.model.entity.ExmTestpaperMedicalrec;
import com.sm.open.core.model.vo.pf.biz.PfCommonZtreeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfTestPaperDao {

    /**
     * paper分类树
     *
     * @param idOrg 机构id
     * @return
     */
    List<PfCommonZtreeVo> listPaperClassifyTree(@Param("idOrg") Long idOrg);

    /**
     * 新增paper分类信息
     *
     * @param dto
     * @return
     */
    Long addPaperClassify(ExmTestpaperCa dto);

    /**
     * 编辑paper分类信息
     *
     * @param dto
     * @return
     */
    Integer editPaperClassify(ExmTestpaperCa dto);

    /**
     * 删除paper分类信息
     *
     * @param dto
     * @return
     */
    Integer delPaperClassify(PfBachChangeStatusDto dto);

    /**
     * 获取所有测试试卷
     *
     * @param dto
     * @return
     */
    List<ExmTestpaper> listAllPaper(PfTestPaperDto dto);

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
    Integer addPaper(ExmTestpaper dto);

    /**
     * 新增试卷信息
     *
     * @param dto
     * @return
     */
    Integer editPaper(ExmTestpaper dto);

    /**
     * 删除试卷信息
     *
     * @param dto
     * @return
     */
    Integer delPaper(PfBachChangeStatusDto dto);

    /**
     * 病例树
     *
     * @param dto
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
     * 获取以保存清单
     *
     * @param idTestpaper
     * @return
     */
    List<ExmTestpaperMedicalrec> listAllPaperItem(@Param("idTestpaper") Long idTestpaper);

    /**
     * 清空试卷
     *
     * @param idTestpaper
     * @return
     */
    Integer delAllPaperItem(@Param("idTestpaper") Long idTestpaper);

    /**
     * 添加试题清单
     *
     * @param dto
     * @return
     */
    boolean addPaperItem(ExmTestpaperMedicalrec dto);

    /**
     * 删除试题清单
     *
     * @param dto
     * @return
     */
    Integer delPaperItem(PfBachChangeStatusDto dto);

    /**
     * 修改试题清单排序
     *
     * @param dto
     * @return
     */
    Integer updatePaperItemSort(ExmTestpaperMedicalrec dto);

    /**
     * 根据id查询试卷信息
     *
     * @param idTestpaper 试卷id
     * @return
     */
    ExmTestpaper selectTestPaperById(@Param("idTestpaper") Long idTestpaper);
}
