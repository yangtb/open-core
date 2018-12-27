package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 临床模拟_测试计划_明细
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class ExmTestplanDetail implements Serializable {

    private static final long serialVersionUID = 1541156585760L;

    /**
     * 主键
     * 测试明细ID
     */
    private Long idTestplanDetail;

    /**
     * 测试计划ID
     */
    private Long idTestplan;

    /**
     * 学生ID
     */
    private Long idStudent;

    /**
     * 病历ID
     */
    private Long idMedicalrec;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 0 计划 1 正在执行 2 执行完成
     */
    private String sdTestplanDetail;

    /**
     * 病例名称
     */
    private String caseName;

    /**
     * 病例级别
     */
    private String sdLevel;

    /**
     * 联系电话
     */
    private String phoneNo;

    /**
     * 真实姓名
     */
    private String realName;


}
