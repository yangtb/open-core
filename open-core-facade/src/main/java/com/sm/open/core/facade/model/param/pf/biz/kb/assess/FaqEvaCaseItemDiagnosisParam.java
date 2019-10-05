package com.sm.open.core.facade.model.param.pf.biz.kb.assess;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class FaqEvaCaseItemDiagnosisParam implements Serializable {

    private static final long serialVersionUID = 1540812319916L;


    /**
     * 主键
     * 明细id
     */
    private Long idEvaCaseItemList;

    /**
     * 病例评估项id
     */
    private Long idEvaCaseItem;

    /**
     * 疾病id
     */
    private Long idDie;

    /**
     * 主机板标识
     */
    private String fgDieMain;

}
