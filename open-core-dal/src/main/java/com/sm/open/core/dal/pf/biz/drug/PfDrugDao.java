package com.sm.open.core.dal.pf.biz.drug;

import com.sm.open.core.model.dto.pf.biz.drug.PfDrugInfoDto;
import com.sm.open.core.model.entity.BasDrugs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfDrugDao {

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

}
