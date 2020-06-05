package com.sm.open.core.model.dto.pf.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfCommonDto implements Serializable {

    private static final long serialVersionUID = -7400624365266677449L;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 当前页
     */
    private Integer pageNum;


}
