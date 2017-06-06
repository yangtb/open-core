package com.sm.open.core.facade.model.param.mb.test;

import java.io.Serializable;

public class MbTestParam implements Serializable {

    private static final long serialVersionUID = -310053372064720675L;

    private Long    id;
    private String  name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MbTestParam{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
