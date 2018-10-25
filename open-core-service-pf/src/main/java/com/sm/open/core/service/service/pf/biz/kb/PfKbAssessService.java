package com.sm.open.core.service.service.pf.biz.kb;

import com.sm.open.core.model.dto.pf.biz.kb.assess.PfEvaCaseDto;
import com.sm.open.core.model.dto.pf.common.PfBachChangeStatusDto;
import com.sm.open.core.model.entity.FaqEvaCase;
import com.sm.open.core.model.entity.FaqMedCase;

import java.util.List;

/**
 * @ClassName: PfKbAssessService
 * @Description: 评估组件用例
 * @Author yangtongbin
 * @Date 2018/10/22
 */
public interface PfKbAssessService {

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
    Long saveKbAssess(FaqEvaCase dto);

    /**
     * 删除评估组件用例
     *
     * @param dto
     * @return
     */
    boolean delKbAssess(PfBachChangeStatusDto dto);


}
