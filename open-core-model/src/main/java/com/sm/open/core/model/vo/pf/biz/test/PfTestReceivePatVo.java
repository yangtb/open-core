package com.sm.open.core.model.vo.pf.biz.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class PfTestReceivePatVo implements Serializable {

    private static final long serialVersionUID = 7805306448357333809L;

    /**
     * 执行结果id
     */
    private Long idTestexecResult;

    /**
     * 测试明细ID
     */
    private Long idTestplanDetail;

    /**
     * 测试计划id
     */
    private Long idTestplan;

    /**
     * 学生id
     */
    private Long idStudent;

    /**
     * 病例模板id
     */
    private Long idDemo;

    /**
     * 病例id
     */
    private Long idMedicalrec;

    /**
     * 病历组件案例id
     */
    private Long idMedCase;

    /**
     * 试卷id
     */
    private Long idTestpaper;

    /**
     * 接诊状态
     */
    private String status;

    /**
     * 接诊时间
     */
    private Date receiveDate;

    /**
     * 接诊耗时(m)
     */
    private Integer receiveConsumingTime;

    /**
     * 患者姓名
     */
    private String patName;
    /**
     * 性别
     */
    private String patSex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 病例
     */
    private String receiveDoc;

    /**
     * 接诊医师
     */
    private String medicalrecName;

    /**
     * 测试计划
     */
    private String naTestplan;

    /**
     * 试卷名称
     */
    private String naTestpaper;

    /**
     * 评分
     */
    private String score;

    /**
     * 称号
     */
    private String ch;

    /**
     * 评估老师
     */
    private String AssessTeacher;

    /**
     * 评估日期
     */
    private Date AssessDate;


}
