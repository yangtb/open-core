package com.sm.open.core.facade.model.rpc;

import java.util.List;

/**
* @ClassName: Result
* @Description: 返回结果
* @param <T>	返回结果的业务数据
*/
public class PageResult<T> extends Result{
	private static final long serialVersionUID = -3343893909519199619L;

	/** 当前页数 */
	private int currentPageno;
	/** 总页数 */
	private int totalPageno;
	/** 是否还有下页 */
	private boolean next;
	/** 总条数 */
	private int total;
	
	/** 返回结果的业务数据 */
	private List<T> content;
	
	/**
	 * 将父类转换成子类
	 * @param result
	 * @return
	 */
	public static <T> PageResult<T> toPageResult(Result result){
		PageResult<T> pr = new PageResult<T>();
		pr.setIsSuccess(result.getIsSuccess());
		pr.setErrorCode(result.getErrorCode());
		pr.setErrorDesc(result.getErrorDesc());
		pr.setDisplayMsg(result.getDisplayMsg());
		pr.setErrorContext(result.getErrorContext());
		pr.setErrorParameters(result.getErrorParameters());
		return pr;
	}

	public int getCurrentPageno() {
		return currentPageno;
	}

	public void setCurrentPageno(int currentPageno) {
		this.currentPageno = currentPageno;
	}

	public int getTotalPageno() {
		return totalPageno;
	}

	public void setTotalPageno(int totalPageno) {
		this.totalPageno = totalPageno;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PageResult [currentPageno=" + currentPageno + "; totalPageno=" + totalPageno + "; next=" + next
				+ "; content=" + content + "; total=" + total + "]";
	}

}
