package com.sm.open.core.service.service.pf.biz.drug;

import com.sm.open.core.model.dto.pf.biz.drug.PfDrugInfoDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.dto.pf.common.PfCatalogueTreeDto;
import com.sm.open.core.model.dto.pf.common.PfChangeStatusDto;
import com.sm.open.core.model.entity.BasDrugs;
import com.sm.open.core.model.entity.BasDrugsClass;
import com.sm.open.core.model.vo.pf.biz.drug.PfDrugZtreeVo;

import java.util.List;

public interface PfDrugService {

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
     * @param idDrugsclass 药品目录id
     * @return
     */
    BasDrugsClass selectDrugDetail(Long idDrugsclass);


    /**
     * 是否已存在该目录编码
     *
     * @param cd
     * @return
     */
    boolean isExistDrugCatalogue(String cd);

    /**
     * 保存药品目录
     *
     * @param dto
     * @return
     */
    Long saveDrugCatalogue(BasDrugsClass dto);

    /**
     * 删除药品目录
     *
     * @param dto
     * @return
     */
    boolean delDrugCatalogue(PfChangeStatusDto dto);

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

    /**
     * 处理拼音
     *
     * @return
     */
    boolean dealPinyin();
}
