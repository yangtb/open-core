package com.sm.open.core.facade.model.result.pf.biz.check;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 问题答案
 *
 * @author author
 */
@Setter
@Getter
@ToString
public class BasCheckSearchItemResult implements Serializable {

    private static final long serialVersionUID = -3026532879375262456L;

    /**
     * 主键
     * 结果ID
     */
    private Long idResult;

    /**
     * 结果描述
     */
    private String desResult;

    /**
     * 部位ID
     */
    private Long idBody;

    /**
     * 多媒体ID
     */
    private Long idMedia;

    /**
     * 多媒体类型
     */
    private String sdType;

    /**
     * 多媒体地址
     */
    private String path;

    /**
     * 是否需要说明理由
     */
    private String fgReason;

    /**
     * 是否根据病人回答反馈
     */
    private String fgBack;

    /**
     * 专家解读
     */
    private String desExpert;

}
