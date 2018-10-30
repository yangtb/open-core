package com.sm.open.core.facade.model.param.pf.biz.kb.assess;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PfAssessCoverParam extends FaqEvaCaseItemParam implements Serializable {


    private static final long serialVersionUID = 5866052701892123619L;

    /**
     * 等效答案
     */
    private List<FaqEvaCaseItemCoverParam> list;

}
