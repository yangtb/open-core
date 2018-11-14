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
     * 病历结果ID
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

}
