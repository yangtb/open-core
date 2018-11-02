package com.sm.open.core.model.dto.pf.biz.kb.assess;

import com.sm.open.core.model.entity.FaqEvaCaseItem;
import com.sm.open.core.model.entity.FaqEvaCaseItemReferral;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PfAssessReferralDto extends FaqEvaCaseItem implements Serializable {


    private static final long serialVersionUID = 5866052701892123619L;

    /**
     * 评估阶段
     */
    private String sdEva;
    /**
     * 等效答案
     */
    private List<FaqEvaCaseItemReferral> list;

}