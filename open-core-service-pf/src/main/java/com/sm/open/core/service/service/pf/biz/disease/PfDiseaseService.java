package com.sm.open.core.service.service.pf.biz.disease;

import com.sm.open.core.model.dto.pf.biz.disease.PfDiseaseInfoDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasDie;

import java.util.List;

public interface PfDiseaseService {

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
