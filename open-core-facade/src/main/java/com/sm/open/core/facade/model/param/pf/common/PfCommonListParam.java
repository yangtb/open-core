package com.sm.open.core.facade.model.param.pf.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PfCommonListParam implements Serializable {

    private static final long serialVersionUID = 6005656188483189531L;

    private List<Long> list;
}
