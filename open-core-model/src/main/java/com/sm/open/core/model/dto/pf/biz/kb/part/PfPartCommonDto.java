package com.sm.open.core.model.dto.pf.biz.kb.part;

import com.sm.open.core.model.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfPartCommonDto extends PageParam implements Serializable {

    /**
     * 病例组件案例id
     */
    private Long idMedCase;

    /**
     * 搜索关键字
     */
    private String keyword;

}
