package com.sm.open.core.facade.model.param.pf.biz.tests.paper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 临床模拟_试卷定义_关联病历
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class ExmTestpaperMedicalrecParam implements Serializable {

    private static final long serialVersionUID = 1540994874561L;

    /**
     * 主键
     * 关联病历ID
     */
    private Long idTestpaperMedicalrec;

    /**
     * 试卷ID
     */
    private Long idTestpaper;

    /**
     * 病历ID
     */
    private Long idMedicalrec;

    /**
     * 排序
     */
    private Integer sort;

}
