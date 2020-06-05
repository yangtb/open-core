package com.sm.open.core.facade.model.result.pf.biz.tests.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfWaitingRoomStartResult implements Serializable {

    /**
     * 病例执行id
     */
    private Long idTestexec;

    /**
     * 病例结果ID
     */
    private Long idTestexecResult;

}
