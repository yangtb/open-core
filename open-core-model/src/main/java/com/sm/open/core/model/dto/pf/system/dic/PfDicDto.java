package com.sm.open.core.model.dto.pf.system.dic;

import com.sm.open.core.model.param.PageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfDicDto extends PageParam implements Serializable {

    private static final long serialVersionUID = -3709105679554437642L;

    private String      dicName;        // 字典名称
    private String      enumName;       // 枚举名称
    private String      groupCode;      // 分组编码

}
