package com.sm.open.core.model.vo.pf.biz.clinic;

import com.sm.open.core.model.entity.BasEvaTag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础_模板_评估标签
 *
 * @author yangtongbin
 */
@Setter
@Getter
@ToString
public class BasEvaTagVo extends BasEvaTag implements Serializable {

    private static final long serialVersionUID = 1539434496473L;

    /**
     * 标签logo路径
     */
    private String path;

}
