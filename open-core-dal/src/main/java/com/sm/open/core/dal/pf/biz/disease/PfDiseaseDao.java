package com.sm.open.core.dal.pf.biz.disease;

import com.sm.open.core.model.dto.pf.biz.disease.PfDiseaseInfoDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.entity.BasDie;
import com.sm.open.core.model.entity.BasDieClass;
import com.sm.open.core.model.vo.pf.biz.disease.PfDiseaseZtreeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfDiseaseDao {

    /**
     * 疾病目录树
     *
     * @param dto
     * @return
     */
    List<PfDiseaseZtreeVo> listDiseaseCatalogueTree(PfCatalogueTreeDto dto);

    /**
     * 疾病目录树
     *
     * @param dto
     * @return
     */
    List<PfDiseaseZtreeVo> listDieCatalogueTree(PfCatalogueTreeDto dto);

    /**
     * 疾病
     *
     * @return
     */
    List<PfDiseaseZtreeVo> listDiseaseTree();

    /**
     * 目录下疾病
     *
     * @return
     */
    List<PfDiseaseZtreeVo> listDiseaseTreeDetail(PfCatalogueTreeDto dto);

    /**
     * 疾病
     *
     * @param dto 关键字
     * @param list
     * @return
     */
    List<PfDiseaseZtreeVo> listDiseaseTreeByCondition(@Param("dto") PfCatalogueTreeDto dto,
                                                      @Param("list") List<PfDiseaseZtreeVo> list);

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
    Integer isExistDiseaseCatalogue(String cd);

    /**
     * 保存疾病目录
     *
     * @param dto
     * @return
     */
    Long saveDiseaseCatalogue(BasDieClass dto);

    /**
     * 编辑疾病目录
     *
     * @param dto
     * @return
     */
    Integer editDiseaseCatalogue(BasDieClass dto);

    /**
     * 删除疾病目录
     *
     * @param list     idDieclass集合
     * @param status   状态
     * @param operator 修改人
     * @return
     */
    Integer delDiseaseCatalogue(@Param("list") List<Long> list,
                                @Param("status") String status,
                                @Param("operator") String operator);

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


    /**
     * 查询子目录
     *
     * @param list
     * @return
     */
    List<String> getChildCatalogueByCatalogueId(@Param("list") List<String> list);

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
    Long addDiseaseInfo(BasDie dto);

    /**
     * 编辑疾病信息
     *
     * @param dto
     * @return
     */
    Integer editDiseaseInfo(BasDie dto);

    /**
     * 删除疾病信息
     *
     * @param dto
     * @return
     */
    Integer delDiseaseInfo(PfBachChangeStatusDto dto);

    /**
     * 疾病名称
     *
     * @param list
     * @return
     */
    List<BasDie> listDieNameByIds(@Param("list") List<Long> list);

    Long countIdeReason(PfDiseaseInfoDto dto);

    List<BasDie> listIdeReason(PfDiseaseInfoDto dto);

    Long getIdEvaCaseByIdMedicalrec(@Param("idMedicalrec") Long idMedicalrec);

}
