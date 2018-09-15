package com.sm.open.core.facade.model.param.pf.system.param;

import com.sm.open.core.facade.model.param.pf.common.PfCommonListParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfParamListParam extends PfCommonListParam implements Serializable {

    private static final long serialVersionUID = -6476365369471501402L;

    private String status;

}
