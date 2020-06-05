package com.sm.open.core.facade.model.result.pf.biz.exam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 问诊问题
 *
 * @author author
 */
@Setter
@Getter
@ToString
public class BasExamSearchResult implements Serializable {

    private static final long serialVersionUID = 1538569588154L;

    /**
     * 主键
     * 项目ID
     */
    private Long idInspectItem;

    /**
     * 项目名称
     */
    private String naItem;

    /**
     * 检验分类ID
     */
    private Long idInspect;

    private String idInspectText;

    /**
     * 英文缩写
     */
    private String naShort;

    /**
     * 标准值
     */
    private String desStand;

    /**
     * 问题答案
     */
    private List<BasExamSearchItemResult> examList;

}
