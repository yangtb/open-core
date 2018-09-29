package com.sm.open.core.dal.pf.biz.disease;

import com.sm.open.core.model.dto.pf.biz.disease.PfDiseaseInfoDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.BasDie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfDiseaseDao {

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
    Integer addDiseaseInfo(BasDie dto);

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
