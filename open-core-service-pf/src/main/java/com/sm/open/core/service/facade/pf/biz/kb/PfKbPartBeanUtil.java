package com.sm.open.core.service.facade.pf.biz.kb;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseBodyListResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseInquesListResult;
import com.sm.open.core.facade.model.result.pf.biz.kb.part.FaqMedCaseInspectListResult;
import com.sm.open.core.facade.model.result.pf.common.media.BasMediaResult;
import com.sm.open.core.model.entity.FaqMedCaseBodyList;
import com.sm.open.core.model.entity.FaqMedCaseInquesList;
import com.sm.open.core.model.entity.FaqMedCaseInspectList;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PfKbPartBeanUtil {

    public static List<FaqMedCaseInquesListResult> convertConsList(List<FaqMedCaseInquesList> reqList) {
        List<FaqMedCaseInquesListResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        FaqMedCaseInquesListResult res;
        for (FaqMedCaseInquesList item : reqList) {
            res = new FaqMedCaseInquesListResult();
            BeanUtils.copyProperties(item, res);
            res.setMediaList(BeanUtil.convertList(item.getMediaList(), BasMediaResult.class));
            resList.add(res);
        }
        return resList;
    }

    public static List<FaqMedCaseBodyListResult> convertCheckList(List<FaqMedCaseBodyList> reqList) {
        List<FaqMedCaseBodyListResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        FaqMedCaseBodyListResult res;
        for (FaqMedCaseBodyList item : reqList) {
            res = new FaqMedCaseBodyListResult();
            BeanUtils.copyProperties(item, res);
            res.setMediaList(BeanUtil.convertList(item.getMediaList(), BasMediaResult.class));
            resList.add(res);
        }
        return resList;
    }

    public static List<FaqMedCaseInspectListResult> convertExamList(List<FaqMedCaseInspectList> reqList) {
        List<FaqMedCaseInspectListResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        FaqMedCaseInspectListResult res;
        for (FaqMedCaseInspectList item : reqList) {
            res = new FaqMedCaseInspectListResult();
            BeanUtils.copyProperties(item, res);
            res.setMediaList(BeanUtil.convertList(item.getMediaList(), BasMediaResult.class));
            resList.add(res);
        }
        return resList;
    }

}
