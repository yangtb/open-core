package com.sm.open.core.facade.model.param.pf.system.notice;

import com.sm.open.core.facade.model.rpc.PfPageParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfNoticeParam extends PfPageParam implements Serializable {

    private static final long serialVersionUID = -8065165575922718762L;

    private String publishTimeBegin;
    private String publishTimeEnd;

}
