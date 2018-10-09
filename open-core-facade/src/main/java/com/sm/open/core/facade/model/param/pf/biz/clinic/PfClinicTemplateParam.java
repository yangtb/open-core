package com.sm.open.core.facade.model.param.pf.biz.clinic;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfClinicTemplateParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -7600891889017067299L;

    /**
     * 部位ID
     */
    private Long idDemo;

    /**
     * 部位分类ID
     */
    private Long idDemoCa;

    /**
     * 模板名称
     */
    private String name;

}
