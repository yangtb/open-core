package com.sm.open.core.model.vo.pf.biz;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PfTreeSelectVo
 * @Description: TreeSelect
 * @Author yangtongbin
 * @Date 2019-05-09
 */
public class PfTreeSelectVo implements Serializable {

    private static final long serialVersionUID = 5059716510419324747L;

    private String id;

    private String name;

    private boolean open;

    private List<PfTreeSelectVo> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<PfTreeSelectVo> getChildren() {
        if (children.size() == 0) {
            this.children = null;
        }
        return children;
    }

    public void setChildren(List<PfTreeSelectVo> children) {
        this.children = children;
    }
}
