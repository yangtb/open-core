package com.sm.open.core.model.vo.pf.biz.exam;

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
public class BasExamSearchItemVo implements Serializable {

    private static final long serialVersionUID = -3026532879375262456L;

    /**
     * 主键
     * 结果ID
     */
    private Long idResult;

    /**
     * 结果值
     */
    private String valResult;

    /**
     * 结果说明
     */
    private String desResult;

    /**
     * 项目ID
     */
    private Long idInspectItem;

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
     * 费用
     */
    private BigDecimal costMoney;

    /**
     * 消耗时间
     */
    private BigDecimal costTime;

    /**
     * 结果是否在医嘱前展示
     */
    private String fgShow;

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
