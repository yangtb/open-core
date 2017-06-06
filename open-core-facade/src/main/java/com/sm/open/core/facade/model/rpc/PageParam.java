package com.sm.open.core.facade.model.rpc;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
* @ClassName: PageParam
* @Description: 带分页参数的接口入参
* @param <T>
*/
public class PageParam<T> implements Serializable {
	private static final long serialVersionUID = -1004766831690669577L;
	
	/** 页数 */
	private Integer pageNo;
	
	/** 每页条数 */
	private Integer limit;
	
	/** 参数内容 */
	private T content;

	public Integer getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getLimit() {
		return this.limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public T getContent() {
		return this.content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}
	
}