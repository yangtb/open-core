package com.sm.open.core.facade.model.param.pf.biz.media;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfMediaDto
 * @Description: 咨媒
 * @Author yangtongbin
 * @Date 2018/8/28 10:13
 */
@Setter
@Getter
@ToString
public class PfMediaParam extends PfPageParam implements Serializable {

    /**
     * 标题
     */
    private String title;
    /**
     * 状态
     */
    private String status;
    /**
     * banner位
     */
    private String noticeType;

}
