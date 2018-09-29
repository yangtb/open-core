package com.sm.open.core.service.service.pf.biz.drug;

import com.sm.open.core.model.dto.pf.biz.drug.PfDrugInfoDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasDrugs;

import java.util.List;

public interface PfDrugService {

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
    boolean addDrugInfo(BasDrugs dto);

    /**
     * 编辑药品信息
     *
     * @param dto
     * @return
     */
    boolean editDrugInfo(BasDrugs dto);

    /**
     * 删除药品信息
     *
     * @param dto
     * @return
     */
    boolean delDrugInfo(PfBachChangeStatusDto dto);
}
