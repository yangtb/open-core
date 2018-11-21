package com.sm.open.core.model.vo.pf.biz.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PfDiagnosisVo implements Serializable {

    private static final long serialVersionUID = 6543291457815381484L;
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
    List<PfWaitingRoomDieReasonVo> ideReasonList;

}
