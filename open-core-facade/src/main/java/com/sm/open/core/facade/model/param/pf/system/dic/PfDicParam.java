package com.sm.open.core.facade.model.param.pf.system.dic;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfDicParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -3709105679554437642L;

    private String      dicName;        // 字典名称
    private String      enumName;       // 枚举名称
    private String      groupCode;      // 分组编码

}
