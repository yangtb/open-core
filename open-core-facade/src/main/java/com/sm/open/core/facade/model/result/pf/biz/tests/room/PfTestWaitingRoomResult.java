package com.sm.open.core.facade.model.result.pf.biz.tests.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfTestWaitingRoomResult implements Serializable {

    private static final long serialVersionUID = 7805306448357333809L;

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
     * 患者姓名
     */
    private String patName;
    /**
     * 性别
     */
    private String patSex;
    /**
     * 分配医师
     */
    private String distributeDoc;

    /**
     * 测试计划
     */
    private String naTestplan;

    /**
     * 试卷名称
     */
    private String naTestpaper;

}
