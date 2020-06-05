package com.sm.open.core.model.vo.pf.biz.test;

import com.sm.open.core.model.entity.FaqMedCasePatient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfWaitingRoomPatVo extends FaqMedCasePatient implements Serializable {

    /**
     * 照片_正面
     */
    private String frontPhotoUrl;

    /**
     * 照片_背面
     */
    private String backPhotoUrl;

    /**
     * 扩汉字段
     */
    private Long ext;

}
