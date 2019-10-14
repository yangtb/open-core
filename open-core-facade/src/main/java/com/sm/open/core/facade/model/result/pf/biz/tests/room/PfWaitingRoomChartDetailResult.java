package com.sm.open.core.facade.model.result.pf.biz.tests.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfWaitingRoomChartDetailResult implements Serializable {

    private static final long serialVersionUID = 2610254702784470062L;

    /**
     * id
     */
    private String id;

    /**
     * 线索标记
     */
    private boolean fgClue;

    /**
     * 检查项
     */
    private String checkItem;

    /**
     * 检查结果
     */
    private String desResult;

}
