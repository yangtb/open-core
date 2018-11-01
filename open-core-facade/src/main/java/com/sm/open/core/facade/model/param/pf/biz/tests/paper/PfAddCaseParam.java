package com.sm.open.core.facade.model.param.pf.biz.tests.paper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PfAddCaseParam implements Serializable {

    private static final long serialVersionUID = -4994775831301732444L;

    /**
     * 病例id集合
     */
    private List<Long> list;

    /**
     * 关联病历ID
     */
    private Long idTestpaperMedicalrec;

    /**
     * 试卷ID
     */
    private Long idTestpaper;
}
