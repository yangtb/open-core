package com.sm.open.core.facade.model.result.pf.biz.tests.room;

import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCasePatientResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class PfWaitingRoomPatResult extends FaqMedCasePatientResult implements Serializable {

    /**
     * 照片_正面
     */
    private String frontPhotoUrl;

    /**
     * 照片_背面
     */
    private String backPhotoUrl;

}
