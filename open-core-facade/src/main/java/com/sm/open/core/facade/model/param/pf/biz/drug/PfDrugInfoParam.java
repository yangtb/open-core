package com.sm.open.core.facade.model.param.pf.biz.drug;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfDrugInfoParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -7008573359759429447L;

    /**
     * 激活状态
     */
    private String fgActive;
    /**
     * 疾病名称
     */
    private String name;

    /**
     * 拼音助记符
     */
    private String pinyin;

    /**
     * 搜索关键字
     */
    private String keywords;
}
