package com.sm.open.core.model.dto.pf.biz.casehistory;

import com.sm.open.core.model.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfCaseHistoryDto extends PageParam implements Serializable {

    /**
     * 病历id
     */
    private Long idMedicalrec;

    /**
     * 病历名称
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
