package com.sm.open.core.model.vo.pf.biz.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class ChartVo implements Serializable {

    private static final long serialVersionUID = 6047393168884559897L;

    /**
     * 主键
     */
    private Long idTestexecResultReferral;

    /**
     * 疾病id
     */
    private Long idDie;

    /**
     * 疾病名称
     */
    private String idDieText;

    /**
     * 排除标志
     */
    private String fgExclude;

    /**
     * 主诊断：0=否，1=是
     */
    private Integer mainFlag;
}
