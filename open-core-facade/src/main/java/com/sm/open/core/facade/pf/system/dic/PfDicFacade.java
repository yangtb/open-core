package com.sm.open.core.facade.pf.system.dic;

import com.sm.open.core.facade.model.param.pf.system.dic.PfDicParam;
import com.sm.open.core.facade.model.param.pf.system.dic.SysDictionaryParam;
import com.sm.open.core.facade.model.result.pf.system.dic.SysDictionaryResult;
import com.sm.open.core.facade.model.rpc.CommonResult;
import com.sm.open.core.facade.model.rpc.PfPageResult;

import java.util.List;

/**
 * @ClassName: PfDicFacade
 * @Description: 字典facade接口
 * @Author yangtongbin
 * @Date 2017/10/9 17:13
 */
public interface PfDicFacade {

    /**
     * 获取字典分组
     *
     * @param param
     * @return
     */
    PfPageResult<SysDictionaryResult> listDicGroups(PfDicParam param);

    /**
     * 获取字典枚举
     *
     * @param param
     * @return
     */
    PfPageResult<SysDictionaryResult> listEnums(PfDicParam param);

    /**
     * 新增字典
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addDic(SysDictionaryParam param);

    /**
     * 编辑字典
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editDic(SysDictionaryParam param);

    /**
     * 删除字典
     *
     * @param list
     * @return
     */
    CommonResult<Boolean> delDic(List<Long> list);

    /**
     * 新增枚举
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> addEnum(SysDictionaryParam param);

    /**
     * 编辑枚举
     *
     * @param param
     * @return
     */
    CommonResult<Boolean> editEnum(SysDictionaryParam param);

    /**
     * 获取所欲枚举
     *
     * @return
     */
    CommonResult<List<SysDictionaryResult>> listAllEnums();
}
