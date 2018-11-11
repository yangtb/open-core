package com.sm.open.core.facade.model.result.pf.biz.tests.room.paper;

import com.sm.open.core.facade.model.result.pf.biz.clinic.PfCaseHistoryTagResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PfTestPaperResult implements Serializable {

    private static final long serialVersionUID = -8593324346365792342L;

    /**
     * 学生信息
     */
    private PfTestPaperStudentResult studentInfo;

    /**
     * 试卷信息
     */
    private PfTestPaperInfoResult paperInfo;

    /**
     * 标签
     */
    List<PfCaseHistoryTagResult> tags;
}
