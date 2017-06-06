package com.sm.open.core.facade.model.rpc;

/**
* @ClassName: ParamFactory
* @Description: 业务参数构建工厂
*/
public class ParamFactory {
	
	/**
	 * 初始化一个上下文入参
	 * @param userId	用户id
	 * @return
	 */
	public static <T> ContextParam<T> initContextParam(String userId, T content){
		ContextParam<T> param = new ContextParam<T>();
		param.setUserId(userId);
		param.setContent(content);
		return param;
	}
	
	/**
	 * 初始化一个上下文入参
	 * @return
	 */
	public static <T> ContextParam<T> initContextParam(ContextBean contextBean, T content){
		ContextParam<T> param = new ContextParam<T>();
		param.setUserId(contextBean.getUserId());
		
		param.setContent(content);
		return param;
	}
	
	/**
	 * 初始化分页参数入参
	 * @param content	业务数据入参
	 * @param pageNo	页码
	 * @param limit		每页多少条
	 * @return
	 */
	public static <T> PageParam<T> initPageParam(T content, Integer pageNo, Integer limit){
		PageParam<T> param = new PageParam<T>();
		param.setPageNo(pageNo);
		param.setLimit(limit);
		param.setContent(content);
		return param;
	}
	
	/**
	 * 初始化上下文及分页参数入参
	 * @param contextBean	上下文入参，如用户信息等
	 * @param pageParam		分页入参
	 * @return
	 */
	public static <T> PageContextParam<T> initPageContextParam(ContextBean contextBean, PageParam<T> pageParam){
		PageContextParam<T> pageContextParam = new PageContextParam<T>();
		pageContextParam.setUserId(contextBean.getUserId());
		
		pageContextParam.setPageParam(pageParam);
		return pageContextParam;
	}
	
	/**
	 * 初始化上下文及分页参数入参
	 * @param content	业务数据入参
	 * @param pageNo	页码
	 * @param limit		每页多少条
	 * @return
	 */
	public static <T> PageContextParam<T> initPageContextParam(ContextBean contextBean, T content, Integer pageNo, Integer limit){
		PageContextParam<T> pageContextParam = new PageContextParam<T>();
		pageContextParam.setUserId(contextBean.getUserId());
		
		PageParam<T> pageParam = new PageParam<T>();
		pageParam.setPageNo(pageNo);
		pageParam.setLimit(limit);
		pageParam.setContent(content);
		pageContextParam.setPageParam(pageParam);
		return pageContextParam;
	}
	
	/**
	 * 初始化上下文及分页参数入参
	 * @param userId	用户id
	 * @param pageParam	分页入参
	 * @return
	 */
	public static <T> PageContextParam<T> initPageContextParam(String userId, PageParam<T> pageParam){
		PageContextParam<T> pageContextParam = new PageContextParam<T>();
		pageContextParam.setUserId(userId);
		pageContextParam.setPageParam(pageParam);
		return pageContextParam;
	}
	
	/**
	 * 初始化上下文及分页参数入参
	 * @param userId	用户id
	 * @param content	业务数据入参
	 * @param pageNo	页码
	 * @param limit		每页多少条
	 * @return
	 */
	public static <T> PageContextParam<T> initPageContextParam(String userId, T content, Integer pageNo, Integer limit){
		PageContextParam<T> pageContextParam = new PageContextParam<T>();
		pageContextParam.setUserId(userId);
		PageParam<T> pageParam = new PageParam<T>();
		pageParam.setPageNo(pageNo);
		pageParam.setLimit(limit);
		pageParam.setContent(content);
		pageContextParam.setPageParam(pageParam);
		return pageContextParam;
	}
	
}
