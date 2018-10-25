package com.sm.open.core.dal.pf.biz.kb;

import com.sm.open.core.model.dto.pf.biz.kb.assess.PfEvaCaseDto;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfMedCaseDto;
import com.sm.open.core.model.dto.pf.biz.kb.part.PfPartCommonDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PfKbAssessDao
 * @Description: 评估组件用例
 * @Author yangtongbin
 * @Date 2018/10/22
 */
@Repository
public interface PfKbAssessDao {

    /**
     * 评估组件用例总数
     *
     * @param dto
     * @return
     */
    Long countKbAssess(PfEvaCaseDto dto);

    /**
     * 评估组件用例列表
     *
     * @param dto
     * @return
     */
    List<FaqEvaCase> listKbAssess(PfEvaCaseDto dto);

    /**
     * 新增评估组件用例
     *
     * @param dto
     * @return
     */
    Integer addKbAssess(FaqEvaCase dto);

    /**
     * 新增评估组件用例
     *
     * @param dto
     * @return
     */
    Integer editKbAssess(FaqEvaCase dto);

    /**
     * 删除评估组件用例
     *
     * @param dto
     * @return
     */
    Integer delKbAssess(PfBachChangeStatusDto dto);

}
