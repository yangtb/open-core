package com.sm.open.core.service.facade.pf.biz.tests;

import com.sm.open.care.core.utils.BeanUtil;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.PfWaitingRoomCheckResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.PfWaitingRoomConsResult;
import com.sm.open.core.facade.model.result.pf.biz.tests.room.PfWaitingRoomExamResult;
import com.sm.open.core.facade.model.result.pf.common.media.BasMediaResult;
import com.sm.open.core.model.vo.pf.biz.test.PfWaitingRoomCheckVo;
import com.sm.open.core.model.vo.pf.biz.test.PfWaitingRoomConsVo;
import com.sm.open.core.model.vo.pf.biz.test.PfWaitingRoomExamVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PfTestBeanUtil {

    public static List<PfWaitingRoomConsResult> convertConsList(List<PfWaitingRoomConsVo> reqList) {
        List<PfWaitingRoomConsResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        PfWaitingRoomConsResult res;
        for (PfWaitingRoomConsVo item : reqList) {
            res = new PfWaitingRoomConsResult();
            BeanUtils.copyProperties(item, res);
            res.setMediaList(BeanUtil.convertList(item.getMediaList(), BasMediaResult.class));
            resList.add(res);
        }
        return resList;
    }

    public static List<PfWaitingRoomCheckResult> convertCheckList(List<PfWaitingRoomCheckVo> reqList) {
        List<PfWaitingRoomCheckResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        PfWaitingRoomCheckResult res;
        for (PfWaitingRoomCheckVo item : reqList) {
            res = new PfWaitingRoomCheckResult();
            BeanUtils.copyProperties(item, res);
            res.setMediaList(BeanUtil.convertList(item.getMediaList(), BasMediaResult.class));
            resList.add(res);
        }
        return resList;
    }

    public static List<PfWaitingRoomExamResult> convertExamList(List<PfWaitingRoomExamVo> reqList) {
        List<PfWaitingRoomExamResult> resList;

        if (CollectionUtils.isEmpty(reqList)) {
            return null;
        }
        resList = new ArrayList<>(reqList.size());
        PfWaitingRoomExamResult res;
        for (PfWaitingRoomExamVo item : reqList) {
            res = new PfWaitingRoomExamResult();
            BeanUtils.copyProperties(item, res);
            res.setMediaList(BeanUtil.convertList(item.getMediaList(), BasMediaResult.class));
            resList.add(res);
        }
        return resList;
    }

}
