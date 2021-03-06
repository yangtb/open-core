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
}
