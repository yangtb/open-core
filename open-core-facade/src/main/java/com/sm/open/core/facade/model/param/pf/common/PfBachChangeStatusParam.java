package com.sm.open.core.facade.model.param.pf.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PfCommonListDto
 * @Description: 通用参数:批量修改状态
 * @Author yangtongbin
 * @Date 2018/9/17 16:52
 */
@Setter
@Getter
@ToString
public class PfBachChangeStatusParam implements Serializable {

    private static final long serialVersionUID = 6005656188483189531L;

    private List<Long> list;

    private String status;
}
