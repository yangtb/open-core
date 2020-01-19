package com.sm.open.core.facade.model.result.pf.biz.tests.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfWaitingRoomDiagnosisResult implements Serializable {

    /**
     * 诊断id
     */
    private Long idTestexecResultDiagnosis;

    /**
     * 病例结果ID
     */
    private Long idTestexecResult;

    /**
     * 疾病ID
     */
    private Long idDie;

    /**
     * 疾病text
     */
    private String idDieText;

    /**
     * 病史小结id
     */
    private Long idTestexecResultSumary;

    /**
     * 病史小结
     */
    private String dieSumary;

    /**
     * 诊断理由
     */
    private String desDieReason;

    /**
     * 主诊断：0=否，1=是
     */
    private Integer mainFlag;
}
