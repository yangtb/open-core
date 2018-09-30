package com.sm.open.core.dal.pf.biz.drug;

import com.sm.open.core.model.dto.pf.biz.drug.PfDrugInfoDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.entity.BasDrugs;
import com.sm.open.core.model.entity.BasDrugsClass;
import com.sm.open.core.model.vo.pf.biz.drug.PfDrugZtreeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfDrugDao {

    /**
     * 药品目录树
     *
     * @param dto
     * @return
     */
    List<PfDrugZtreeVo> listDrugCatalogueTree(PfCatalogueTreeDto dto);

    /**
     * 药品目录信息
     *
     * @param idDrugsclass 疾病目录id
     * @return
     */
    BasDrugsClass selectDrugDetail(@Param("idDrugsclass") Long idDrugsclass);

    /**
     * 是否已存在该目录编码
     *
     * @param cd
     * @return
     */
    Integer isExistDrugCatalogue(String cd);

    /**
     * 保存药品目录
     *
     * @param dto
     * @return
     */
    Long saveDrugCatalogue(BasDrugsClass dto);

    /**
     * 编辑药品目录
     *
     * @param dto
     * @return
     */
    Integer editDrugCatalogue(BasDrugsClass dto);


    /**
     * 删除药品目录
     *
     * @param list     id集合
     * @param status   状态
     * @param operator 修改人
     * @return
     */
    Integer delDrugCatalogue(@Param("list") List<Long> list,
                             @Param("status") String status,
                             @Param("operator") String operator);

    /**
     * 药品信息总数
     *
     * @param dto
     * @return
     */
    Long countDrugInfo(PfDrugInfoDto dto);

    /**
     * 药品信息列表
     *
     * @param dto
     * @return
     */
    List<BasDrugs> listDrugInfo(PfDrugInfoDto dto);

    /**
     * 新增药品信息
     *
     * @param dto
     * @return
     */
    int addDrugInfo(BasDrugs dto);

    /**
     * 编辑药品信息
     *
     * @param dto
     * @return
     */
    int editDrugInfo(BasDrugs dto);

    /**
     * 删除药品信息
     *
     * @param dto
     * @return
     */
    int delDrugInfo(PfBachChangeStatusDto dto);
}
