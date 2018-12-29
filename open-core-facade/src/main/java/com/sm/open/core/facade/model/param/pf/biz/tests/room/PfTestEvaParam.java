package com.sm.open.core.facade.model.param.pf.biz.tests.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfTestEvaParam implements Serializable {

    private static final long serialVersionUID = -1707262746906227797L;


    /**
     * 病例结果ID
     */
    private Long idTestexecResult;

    /**
     * 病例评估维度ID
     */
    private Long idTestexecResultDimension;

}
