package com.sm.open.core.facade.model.result.pf.biz.tests.room;

import java.io.Serializable;

public class PfWaitingRoomChartDetailResult implements Serializable {

    private static final long serialVersionUID = 2610254702784470062L;

    /**
     * id
     */
    private String id;

    /**
     * 线索标记
     */
    private boolean fgClue;

    /**
     * 检查项
     */
    private String checkItem;

    /**
     * 检查结果
     */
    private String desResult;

    /**
     * layui table复选框选中
     */
    private boolean LAY_CHECKED;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFgClue() {
        return fgClue;
    }

    public void setFgClue(boolean fgClue) {
        this.fgClue = fgClue;
    }

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }

    public String getDesResult() {
        return desResult;
    }

    public void setDesResult(String desResult) {
        this.desResult = desResult;
    }

    public boolean isLAY_CHECKED() {
        return this.fgClue;
    }

    public void setLAY_CHECKED(boolean LAY_CHECKED) {
        this.LAY_CHECKED = this.fgClue;
    }
}
