package com.sm.open.core.model.dto.pf.biz.clinic.parts;

import com.sm.open.core.model.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfClinicPartsDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -7600891889017067299L;

    /**
     * 组件名称
     */
    private String name;

}
