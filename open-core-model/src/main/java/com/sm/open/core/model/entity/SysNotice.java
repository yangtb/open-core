package com.sm.open.core.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class SysNotice implements Serializable {

    private static final long serialVersionUID = -3457933153204854464L;

    private Long        id;                 // 主键
    private String      noticeTitle;        // 公告标题
    private String      noticeType;         // 公告类型 1=系统公告2=活动公告
    private String      noticeContent;      // 公告内容
    private String      operator;            // 创建人ID
    private Date        gmtCreate;          // 创建时间
    private Date        gmtUpdate;          // 更新时间

}
