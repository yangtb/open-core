package com.sm.open.core.model.dto.pf.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: PfDiseaseCatalogueTreeDto
 * @Description: 目录树入参
 * @Author yangtongbin
 * @Date 2018/9/29
 */
@Setter
@Getter
@ToString
public class PfCatalogueTreeDto implements Serializable {

    private static final long serialVersionUID = -6975428826879877283L;

    /**
     * 异步加载
     */
    private boolean async;

    /**
     * 操作id
     */
    private Long id;
}