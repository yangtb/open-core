package com.sm.open.core.service.facade.pf.biz.exam;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.result.pf.biz.exam.BasExamSearchItemResult;
import com.sm.open.core.facade.model.result.pf.biz.exam.BasExamSearchResult;
import com.sm.open.core.facade.model.result.pf.biz.exam.BasItemResultResult;
import com.sm.open.core.facade.model.result.pf.common.media.BasMediaResult;
import com.sm.open.core.model.entity.BasItemResult;
import com.sm.open.core.model.vo.pf.biz.exam.BasExamSearchVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PfExamBeanUtil {

    public static List<BasExamSearchResult> convertList(List<BasExamSearchVo> reqList) {
        List<BasExamSearchResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        BasExamSearchResult res;
        for (BasExamSearchVo basExamSearchVo : reqList) {
            res = new BasExamSearchResult();
            BeanUtils.copyProperties(basExamSearchVo, res);
            res.setExamList(BeanUtil.convertList(basExamSearchVo.getExamList(), BasExamSearchItemResult.class));
            resList.add(res);
        }
        return resList;
    }

    public static List<BasItemResultResult> convertAnswerList(List<BasItemResult> reqList) {
        List<BasItemResultResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        BasItemResultResult res;
        for (BasItemResult basItemResult : reqList) {
            res = new BasItemResultResult();
            BeanUtils.copyProperties(basItemResult, res);
            res.setMediaList(BeanUtil.convertList(basItemResult.getMediaList(), BasMediaResult.class));
            resList.add(res);
        }
        return resList;
    }

}
