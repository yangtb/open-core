package com.sm.open.core.facade.model.param.pf.biz.tests.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 临床模拟_测试执行_病例结果_问诊
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class PfExmMedResultParam implements Serializable {

    private static final long serialVersionUID = 1541867770936L;


    /**
     * 主键
     * <p>
     */
    private Long id;

    /**
     * 1 问诊 2 体检 3辅助检查
     */
    private Integer type;

    /**
     * 说明理由
     */
    private String desReason;

    /**
     * 解释患者的回复
     */
    private String desReply;

}
