package com.sm.open.core.facade.model.result.pf.biz.tests.room.eva;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class PfExecLogResult implements Serializable {

    private static final long serialVersionUID = -3658629749787443176L;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 操作
     */
    private String operation;

    /**
     * 答案
     */
    private String answer;

    /**
     * 时间
     */
    private Date logDate;
}
