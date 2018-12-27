package com.sm.open.core.facade.model.param.pf.biz.kb.assess;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfAssessCommonParam extends PfPageParam implements Serializable {

    /**
     * 评估组件案例id
     */
    private Long idEvaCase;

    /**
     * 组件id
     */
    private String cdEvaAsse;

    /**
     * 病历评估项id
     */
    private Long idEvaCaseItem;

    /**
     * 评估阶段
     */
    private String sdType;

}
