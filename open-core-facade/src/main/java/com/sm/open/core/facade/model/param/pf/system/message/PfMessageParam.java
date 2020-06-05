package com.sm.open.core.facade.model.param.pf.system.message;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfMessageParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = 5654966115931979418L;

    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板类型
     */
    private String templateType;
    /**
     * 删除标识
     */
    private String isDeleted;

}
