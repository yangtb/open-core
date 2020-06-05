package com.sm.open.core.service.service.pf.biz.disease;

import com.sm.open.core.model.dto.pf.biz.disease.PfDiseaseInfoDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.dto.pf.common.PfChangeStatusDto;
import com.sm.open.core.model.entity.BasDie;
import com.sm.open.core.model.entity.BasDieClass;
import com.sm.open.core.model.vo.pf.biz.disease.PfDiseaseZtreeVo;

import java.util.List;

public interface PfDiseaseService {

    /**
     * 疾病目录树
     *
     * @param dto
     * @return
     */
    List<PfDiseaseZtreeVo> listDiseaseCatalogueTree(PfCatalogueTreeDto dto);

    /**
     * 疾病目录信息
     *
     * @param idDieClass 基本目录id
     * @return
     */
    BasDieClass selectDiseaseDetail(Long idDieClass);

    /**
     * 是否已存在该目录编码
     *
     * @param cd
     * @return
     */
    boolean isExistDiseaseCatalogue(String cd);

    /**
     * 保存疾病目录
     *
     * @param dto
     * @return
     */
    Long saveDiseaseCatalogue(BasDieClass dto);

    /**
     * 删除疾病目录
     *
     * @param dto
     * @return
     */
    boolean delDiseaseCatalogue(PfChangeStatusDto dto);

    /**
     * 查询子目录
     *
     * @param catalogueId
     * @return
     */
    List<String> getChildCatalogueByCatalogueId(String catalogueId);

    /**
     * 疾病信息总数
     *
     * @param dto
     * @return
     */
    Long countDiseaseInfo(PfDiseaseInfoDto dto);

    /**
     * 疾病信息列表
     *
     * @param dto
     * @return
     */
    List<BasDie> listDiseaseInfo(PfDiseaseInfoDto dto);

    Long getIdEvaCaseByIdMedicalrec(Long idMedicalrec);

    Long countIdeReason(PfDiseaseInfoDto dto);


    List<BasDie> listIdeReason(PfDiseaseInfoDto dto);

    /**
     * 疾病总数
     *
     * @param dto
     * @return
     */
    Long countDiseaseByCatalogueId(PfDiseaseInfoDto dto);

    /**
     * 疾病信息列表
     *
     * @param dto
     * @return
     */
    List<BasDie> listDiseaseByCatalogueId(PfDiseaseInfoDto dto);

    /**
     * 新增疾病信息
     *
     * @param dto
     * @return
     */
    boolean addDiseaseInfo(BasDie dto);

    /**
     * 编辑疾病信息
     *
     * @param dto
     * @return
     */
    boolean editDiseaseInfo(BasDie dto);

    /**
     * 删除疾病信息
     *
     * @param dto
     * @return
     */
    boolean delDiseaseInfo(PfBachChangeStatusDto dto);
}
