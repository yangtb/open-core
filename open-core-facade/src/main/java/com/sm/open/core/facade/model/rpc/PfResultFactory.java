package com.sm.open.core.facade.model.rpc;

import java.util.List;

/**
 * @ClassName: ResultFactory
 * @Description: Result以及PageResult工厂生成类
 * @Author yangtongbin
 * @Date 2017/9/9 16:05
 */
public class PfResultFactory {

    /**
     * 初始化一个成功的结果，返回普通业务数据实体集合
     *
     * @param count 总数
     * @param data  业务list集合
     * @param <T>
     * @return
     */
    public static <T> PfPageResult<T> initPagePfResultWithSuccess(Long count, List<T> data) {
        PfPageResult<T> result = new PfPageResult<T>();
        result.setCode("0");
        result.setMsg("分页查询成功");
        result.setCount(count);
        result.setData(data);
        return result;
    }

    public static <T> PfPageResult<T> initPageResultWithError(String errorCode, String errorDesc) {
        PfPageResult<T> result = new PfPageResult<T>();
        result.setCode(errorCode);
        result.setMsg(errorDesc);
        return result;
    }

}
