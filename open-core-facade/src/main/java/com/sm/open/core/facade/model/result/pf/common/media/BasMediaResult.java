package com.sm.open.core.facade.model.result.pf.common.media;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 基础_多媒体
 *
 * @author author
 */
@Setter
@Getter
@ToString
public class BasMediaResult implements Serializable {

    private static final long serialVersionUID = 1538900072127L;


    /**
     * 1 图片 2 音频 3 视频 4 其它
     */
    private String sdType;

    /**
     * 路径
     */
    private String path;

}
