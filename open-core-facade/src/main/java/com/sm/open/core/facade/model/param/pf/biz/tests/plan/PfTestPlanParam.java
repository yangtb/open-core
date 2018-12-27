package com.sm.open.core.facade.model.param.pf.biz.tests.plan;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfTestPlanParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -7600891889017067299L;

    /**
     * 测试计划名称
     */
    private String naTestplan;

    /**
     * 测试计划ID
     */
    private Long idTestplan;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 搜索关键字
     */
    private String keywords;

}
