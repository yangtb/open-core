package com.sm.open.core.facade.model.param.pf.biz.kb.assess;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfAssessEffciencyParam extends FaqEvaCaseItemParam implements Serializable {


    private static final long serialVersionUID = 5866052701892123619L;

    /**
     * 明细id
     */
    private Long idEvaCaseItemList;

    /**
     * 1. 问诊 2. 检查 3. 检验
     */
    private String sdEvaEffciency;


    /**
     * 数量上限
     */
    private Integer quaUpper;

}
