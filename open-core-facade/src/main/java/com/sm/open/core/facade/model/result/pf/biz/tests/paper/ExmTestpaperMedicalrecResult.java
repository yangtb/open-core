package com.sm.open.core.facade.model.result.pf.biz.tests.paper;

import com.sm.open.core.facade.model.result.pf.biz.kb.casehistory.FaqMedicalrecResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 临床模拟_试卷定义_关联病例
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class ExmTestpaperMedicalrecResult extends FaqMedicalrecResult implements Serializable {

    private static final long serialVersionUID = 1540994874561L;

    /**
     * 主键
     * 关联病例ID
     */
    private Long idTestpaperMedicalrec;

    /**
     * 试卷ID
     */
    private Long idTestpaper;

    /**
     * 病例ID
     */
    private Long idMedicalrec;

    /**
     * 排序
     */
    private Integer sort;

}
