package com.sm.open.core.model.dto.pf.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class PfCommonListDto implements Serializable {

    private static final long serialVersionUID = 6005656188483189531L;

    private List<Long> list;
}
