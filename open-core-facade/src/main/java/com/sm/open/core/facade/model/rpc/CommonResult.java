package com.sm.open.core.facade.model.rpc;

/**
* @ClassName: CommonResult
* @Description: 通用返回结果
* @param <T>
*/
public class CommonResult<T> extends Result {
	private static final long serialVersionUID = -4240896825256128290L;
	
	private T content;
	
	/**
	 * 将父类转换成子类
	 * @param result
	 * @return
	 */
	public static <T> CommonResult<T> toCommonResult(Result result){
		CommonResult<T> cr = new CommonResult<T>();
		cr.setIsSuccess(result.getIsSuccess());
		cr.setErrorCode(result.getErrorCode());
		cr.setErrorDesc(result.getErrorDesc());
		cr.setDisplayMsg(result.getDisplayMsg());
		cr.setErrorContext(result.getErrorContext());
		cr.setErrorParameters(result.getErrorParameters());
		return cr;
	}
	
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommonResult [content=" + content + "]";
	}

}
