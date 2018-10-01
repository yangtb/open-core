package com.sm.open.core.model.vo.pf.biz;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfCommonZtreeVo implements Serializable {

    private static final long serialVersionUID = -4901737093643963963L;

    /**
     * 目录编码
     */
    private String      id;
    /**
     * 上级编码
     */
    private String      pId;
    /**
     * 疾病目录名称
     */
    private String      name;
    /**
     * 节点打开状态
     */
    private boolean     open;
    /**
     * 是否是父节点
     */
    @JSONField(name = "isParent")
    private boolean     isParent;

}
