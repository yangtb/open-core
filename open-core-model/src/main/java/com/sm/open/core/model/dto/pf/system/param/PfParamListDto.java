package com.sm.open.core.model.dto.pf.system.param;

import com.sm.open.core.model.dto.pf.common.PfCommonListDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfParamListDto extends PfCommonListDto implements Serializable {

    private static final long serialVersionUID = -6476365369471501402L;

    private String status;

}
