package com.sm.open.core.model.vo.pf.biz.inquisition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 问题答案
 *
 * @author author
 */
@Setter
@Getter
@ToString
public class BasInquesSearchAnswerVo implements Serializable {

    private static final long serialVersionUID = -3026532879375262456L;

    /**
     * 答案ID
     */
    private Long idAnswer;

    /**
     * 答案内容
     */
    private String desAnswer;

}
