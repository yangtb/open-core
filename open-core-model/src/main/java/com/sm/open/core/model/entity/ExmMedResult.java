package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 临床模拟_测试执行_病例结果
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class ExmMedResult implements Serializable {

    private static final long serialVersionUID = 1541852283826L;

    /**
     * 主键
     */
    private Long idTestexecResult;

    /**
     *
     */
    private Long idTestexec;

    /**
     * 结果序号
     */
    private Integer serial;

    /**
     * 最终结果标志
     */
    private String fgFinalResult;

    /**
     * 评估标志
     */
    private String fgAsses;

}
