package com.sm.open.core.service.facade.pf.biz.tests;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.PfDiagnosisResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.PfWaitingRoomDieReasonResult;
import com.sm.open.core.model.vo.pf.biz.test.PfDiagnosisVo;

import java.util.ArrayList;
import java.util.List;

public class PfTestPaperBeanUtil {


    public static List<PfDiagnosisResult> convertZdList(List<PfDiagnosisVo> reqList) {
        if (reqList == null) {
            return null;
        }
        List<PfDiagnosisResult> pfDiagnosisResults = new ArrayList<>(reqList.size());
        PfDiagnosisResult pfDiagnosisResult;
        for (PfDiagnosisVo pfDiagnosisVo : reqList) {
            pfDiagnosisResult = BeanUtil.convert(pfDiagnosisVo, PfDiagnosisResult.class);
            pfDiagnosisResult.setIdeReasonList(BeanUtil.convertList(pfDiagnosisVo.getIdeReasonList(), PfWaitingRoomDieReasonResult.class));
            pfDiagnosisResults.add(pfDiagnosisResult);
        }
        return pfDiagnosisResults;
    }


}
