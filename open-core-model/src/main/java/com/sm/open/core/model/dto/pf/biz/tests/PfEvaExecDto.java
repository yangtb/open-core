package com.sm.open.core.model.dto.pf.biz.tests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfEvaExecDto implements Serializable {

    private static final long serialVersionUID = -1707262746906227797L;


    /**
     * 病例结果ID
     */
    private Long idTestexecResult;

    /**
     * 错误编码
     */
    private Integer parCode;

    /**
     * 错误信息
     */
    private String parMsg;

}
