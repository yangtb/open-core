package com.sm.open.core.facade.model.param.pf.system.org;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfOrgAuthParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -8065165575922718762L;

    /**
     * 机构名称
     */
    private String name;
    /**
     * 状态
     */
    private String sdReg;
    /**
     * 申请开始时间
     */
    private String gmtApplyStart;
    /**
     * 申请结束
     */
    private String gmtApplyEnd;
}
