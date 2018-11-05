package com.sm.open.core.model.dto.pf.biz.tests;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfTestWatingRoomDto extends PfPageParam implements Serializable {

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
    private String na_testpaper;

    /**
     * 机构ID
     */
    private Long idOrg;

    /**
     * 搜索关键字
     */
    private String keywords;

}
