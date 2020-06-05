package com.sm.open.core.facade.model.rpc;

import com.alibaba.fastjson.JSON;

/**
* @ClassName: PageContextParam
* @Description: 带分页的且带上下文参数的业务数据入参
* @param <T>
*/
public class PageContextParam<T> extends ContextBean {
	private static final long serialVersionUID = 1548069007753795148L;
	
	/** 分页入参 */
	private PageParam<T> pageParam;
	
	public PageParam<T> getPageParam() {
		return pageParam;
	}

	public void setPageParam(PageParam<T> pageParam) {
		this.pageParam = pageParam;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
