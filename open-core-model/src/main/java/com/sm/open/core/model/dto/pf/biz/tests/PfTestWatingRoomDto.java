package com.sm.open.core.model.dto.pf.biz.tests;

import com.sm.open.core.model.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfTestWatingRoomDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -7600891889017067299L;

    /**
     * 测试计划名称
     */
    private String naTestplan;

    /**
     * 病例名称
     */
    private String medicalrecName;

    /**
     * 试卷名称
     */
    private String naTestpaper;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 搜索关键字
     */
    private String keywords;

    /**
     * 评估状态
     */
    private String fgAsses;

    /**
     * 当前用户ID
     */
    private Long currentUserId;

    /**
     * 所属分类
     */
    private Long idMedicalrecCa;

    /**
     * 获取用户id
     */
    private Long userId;

    /**
     * 超级管理员
     */
    private boolean isSuper;

    /**
     * 用户角色级别
     */
    private int level;

    /**
     * 超级管理员 1 是 0否
     */
    private String fgSuper;

    /**
     * 学生用户
     */
    private boolean student;
}
