package com.sm.open.core.model.vo.pf.biz.inquisition;

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
public class BasInquesSearchVo implements Serializable {

    private static final long serialVersionUID = 1538569588154L;

    /**
     * 问题目录
     */
    private Long idInquesCa;
    /**
     * 问题ID
     */
    private Long idInques;

    /**
     * 问题内容
     */
    private String desInques;

    /**
     * 问诊分类
     */
    private String idInquesCaText;

    /**
     * 问题答案
     */
    private List<BasInquesSearchAnswerVo> answerList;

}
