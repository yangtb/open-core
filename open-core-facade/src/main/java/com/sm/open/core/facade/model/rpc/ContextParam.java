package com.sm.open.core.facade.model.rpc;

import com.alibaba.fastjson.JSON;

/**
* @ClassName: ContextParam
* @Description: 上下文参数，比如用户信息
*/
public class ContextParam<T> extends ContextBean  {
	private static final long serialVersionUID = 1363080936010957504L;

	/** 业务参数内容 */
	private T content;
	
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
