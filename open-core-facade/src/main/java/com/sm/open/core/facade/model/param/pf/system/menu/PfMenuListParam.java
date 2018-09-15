package com.sm.open.core.facade.model.param.pf.system.menu;


import com.sm.open.core.facade.model.param.pf.common.PfCommonListParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfMenuListParam extends PfCommonListParam implements Serializable {

    private static final long serialVersionUID = 321752553115657904L;

    private String status;
}
