package com.sm.open.core.service.facade.pf.biz.inquisition;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.result.pf.biz.inquisition.BasInquesAnswerResult;
import com.sm.open.core.facade.model.result.pf.biz.inquisition.BasInquesSearchAnswerResult;
import com.sm.open.core.facade.model.result.pf.biz.inquisition.BasInquesSearchResult;
import com.sm.open.core.facade.model.result.pf.common.media.BasMediaResult;
import com.sm.open.core.model.entity.BasInquesAnswer;
import com.sm.open.core.model.vo.pf.biz.inquisition.BasInquesSearchVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PfInquisitionBeanUtil {

    public static List<BasInquesSearchResult> convertList(List<BasInquesSearchVo> reqList) {
        List<BasInquesSearchResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        BasInquesSearchResult res;
        for (BasInquesSearchVo basInquesSearchVo : reqList) {
            res = new BasInquesSearchResult();
            BeanUtils.copyProperties(basInquesSearchVo, res);
            res.setAnswerList(BeanUtil.convertList(basInquesSearchVo.getAnswerList(), BasInquesSearchAnswerResult.class));
            resList.add(res);
        }
        return resList;
    }

    public static List<BasInquesAnswerResult> convertAnswerList(List<BasInquesAnswer> reqList) {
        List<BasInquesAnswerResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        BasInquesAnswerResult res;
        for (BasInquesAnswer basInquesAnswer : reqList) {
            res = new BasInquesAnswerResult();
            BeanUtils.copyProperties(basInquesAnswer, res);
            res.setMediaList(BeanUtil.convertList(basInquesAnswer.getMediaList(), BasMediaResult.class));
            resList.add(res);
        }
        return resList;
    }
}
