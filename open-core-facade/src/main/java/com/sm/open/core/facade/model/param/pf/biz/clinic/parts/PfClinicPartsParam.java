package com.sm.open.core.facade.model.param.pf.biz.clinic.parts;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfClinicPartsParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -7600891889017067299L;

    /**
     * 组件名称
     */
    private String name;

}
