package com.sm.open.core.model.vo.pf.biz.test.paper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: PfTestPaperStudentVo
 * @Description: 试卷信息
 * @Author yangtongbin
 * @Date 2018/11/9
 */
@Setter
@Getter
@ToString
public class PfTestPaperInfoVo implements Serializable {

    private static final long serialVersionUID = -8593324346365792342L;

    /**
     * 执行id
     */
    private Long idTestexec;

    /**
     * 执行结果id
     */
    private Long idTestexecResult;

    /**
     * 试卷id
     */
    private Long idTestpaper;

    /**
     * 试卷名称
     */
    private String testPaperName;

    /**
     * 出题机构
     */
    private String orgName;

    /**
     * 出题人
     */
    private String paperCreator;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;


}
