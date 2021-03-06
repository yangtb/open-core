package com.sm.open.core.facade.model.param.pf.biz.drug;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
 * @ClassName: BasDrugs
 * @Description: 药品信息
 * @Author yangtongbin
 * @Date 2018/9/28
 */
@Setter
@Getter
@ToString
public class BasDrugsParam implements Serializable {

    private static final long serialVersionUID = 1538133649055L;

    /**
    * 主键
    * 药品ID
    */
    private Long idDrugs;

    /**
    * 药品名称
    */
    private String name;

    /**
    * 药品目录编码
    */
    private String cdDrugsclass;

    /**
    * 药品规格
    */
    private String spec;

    /**
    * 药品单位
    */
    private String unit;

    /**
    * 拼音助记符
    */
    private String pinyin;

    /**
    * 激活标志
    */
    private String fgActive;

    /**
    * 删除标志
    */
    private String fgValid;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 创建人员
    */
    private String creator;

    /**
    * 创建时间
    */
    private Date gmtCreate;

    /**
    * 修改人员
    */
    private String operator;

    /**
    * 修改时间
    */
    private Date gmtModify;

}
