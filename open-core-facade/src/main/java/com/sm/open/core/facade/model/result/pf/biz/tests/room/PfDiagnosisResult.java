package com.sm.open.core.facade.model.result.pf.biz.tests.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PfDiagnosisResult implements Serializable {

    private static final long serialVersionUID = -7116097368955088634L;

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
     * 确诊理由
     */
    List<PfWaitingRoomDieReasonResult> ideReasonList;

}
