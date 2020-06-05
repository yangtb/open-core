package com.sm.open.core.facade.model.param.pf.biz.kb.casehistory;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfCaseHistoryParam extends PfPageParam implements Serializable {

    /**
     * 病例id
     */
    private Long idMedicalrec;

    /**
     * 病例名称
     */
    private String name;

    /**
     * 模板id
     */
    private Long idDemo;

    /**
     * 所属分类
     */
    private Long idMedicalrecCa;


}
