package com.sm.open.core.model.vo.pf.biz.test;

import java.io.Serializable;

public class PfWaitingRoomDimensionVo implements Serializable {

    private static final long serialVersionUID = 2610254702784470062L;

    /**
     * id
     */
    private Long idTestexecResultDimension;

    /**
     * 标识
     */
    private boolean flag;

    /**
     * 评估项名称
     */
    private String nameEva;

    public Long getIdTestexecResultDimension() {
        return idTestexecResultDimension;
    }

    public void setIdTestexecResultDimension(Long idTestexecResultDimension) {
        this.idTestexecResultDimension = idTestexecResultDimension;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getNameEva() {
        return nameEva;
    }

    public void setNameEva(String nameEva) {
        this.nameEva = nameEva;
    }

}
