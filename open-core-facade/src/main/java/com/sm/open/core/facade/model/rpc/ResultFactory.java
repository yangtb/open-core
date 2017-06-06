package com.sm.open.core.facade.model.rpc;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ResultFactory
 * @Description: Result以及PageResult工厂生成类
 */
public class ResultFactory {

    /**
     * 初始化一个成功结果
     *
     * @return
     */
    public static Result initResultWithSuccess() {
        Result result = new Result();
        result.setIsSuccess(true);
        return result;
    }

    /**
     * 初始化一个成功结果，返回显示消息
     *
     * @param displayMsg 需要显示的消息
     * @return
     */
    public static Result initResultWithSuccess(String displayMsg) {
        Result result = new Result();
        result.setIsSuccess(true);
        result.setDisplayMsg(displayMsg);
        return result;
    }

    /**
     * 初始化一个成功的结果，返回普通业务数据实体
     *
     * @param t 业务数据
     * @return
     */
    public static <T> CommonResult<T> initCommonResultWithSuccess(T t) {
        CommonResult<T> result = new CommonResult<T>();
        result.setIsSuccess(true);
        result.setContent(t);
        return result;
    }

    /**
     * 初始化一个成功的结果，返回普通业务数据实体和显示消息
     *
     * @param t          业务数据
     * @param displayMsg 要显示的消息
     * @return
     */
    public static <T> CommonResult<T> initCommonResultWithSuccess(T t, String displayMsg) {
        CommonResult<T> result = new CommonResult<T>();
        result.setIsSuccess(true);
        result.setContent(t);
        result.setDisplayMsg(displayMsg);
        return result;
    }

    /**
     * 初始化一个成功的结果，返回普通业务数据实体集合
     *
     * @param dataList      业务数据集合
     * @param currentPageno 当前页数
     * @param totalPageno   总页数
     * @return
     */
    public static <T> PageResult<T> initPageResultWithSuccess(List<T> dataList, int currentPageno, int totalPageno) {
        PageResult<T> result = new PageResult<T>();
        result.setIsSuccess(true);
        result.setContent(dataList);
        result.setCurrentPageno(currentPageno);
        result.setTotalPageno(totalPageno);
        return result;
    }

    /**
     * 初始化一个成功的结果，返回普通业务数据实体集合
     *
     * @param dataList      业务数据集合
     * @param currentPageno 当前页数
     * @param totalPageno   总页数
     * @param displayMsg    要显示的消息
     * @return
     */
    public static <T> PageResult<T> initPageResultWithSuccess(List<T> dataList, int currentPageno, int totalPageno, String displayMsg) {
        PageResult<T> result = new PageResult<T>();
        result.setIsSuccess(true);
        result.setContent(dataList);
        result.setDisplayMsg(displayMsg);
        result.setCurrentPageno(currentPageno);
        result.setTotalPageno(totalPageno);
        return result;
    }

    /**
     * 初始化一个错误的消息
     *
     * @param errorCode 错误编码
     * @param errorDesc 错误描述
     * @return
     */
    public static <T> Result initResultWithError(String errorCode, String errorDesc) {
        Result result = new Result();
        result.setIsSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorDesc(errorDesc);
        return result;
    }

    /**
     * 初始化一个错误的消息，包含多个错误参数集合
     *
     * @param errorCode       错误编码
     * @param errorDesc       错误描述
     * @param errorParameters 错误参数集合
     * @return
     */
    public static <T> Result initResultWithError(String errorCode, String errorDesc, Object[] errorParameters) {
        Result result = new Result();
        result.setIsSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorDesc(errorDesc);
        result.setErrorParameters(errorParameters);
        return result;
    }

    /**
     * 初始化一个错误的消息，包含多个错误参数集合
     *
     * @param errorCode   错误编码
     * @param errorDesc   错误描述
     * @param errorParams 错误参数集合
     * @return
     */
    public static <T> Result initResultWithError(String errorCode, String errorDesc, List<Object> errorParams) {
        Result result = new Result();
        result.setIsSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorDesc(errorDesc);
        Object[] errorParameters = null;
        errorParameters = errorParams.toArray(errorParameters);
        result.setErrorParameters(errorParameters);
        return result;
    }

    /**
     * 初始化一个错误的消息，包含多个子业务错误堆栈信息
     *
     * @param errorCode    错误编码
     * @param errorDesc    错误描述
     * @param errorContext 错误堆栈信息
     * @return
     */
    public static <T> Result initResultWithErrorStack(String errorCode, String errorDesc, ErrorContext errorContext) {
        Result result = new Result();
        result.setIsSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorDesc(errorDesc);
        result.setErrorContext(errorContext);
        return result;
    }

    /**
     * 初始化一个错误的消息，包含多个子业务错误堆栈信息
     *
     * @param errorCode 错误编码
     * @param errorDesc 错误描述
     * @param errors    错误堆栈信息
     * @return
     */
    public static <T> Result initResultWithErrorStack(String errorCode, String errorDesc, CommonError[] errors) {
        Result result = new Result();
        result.setIsSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorDesc(errorDesc);
        if (errors != null && errors.length > 0) {
            List<CommonError> errorStack = new ArrayList<CommonError>();
            for (int i = 0; i < errors.length; i++) {
                errorStack.add(errors[i]);
            }
            ErrorContext errorContext = new ErrorContext(errorStack);
            result.setErrorContext(errorContext);
        }
        return result;
    }

    /**
     * 初始化一个错误的消息，包含多个子业务错误堆栈信息
     *
     * @param errorCode 错误编码
     * @param errorDesc 错误描述
     * @param errors    错误堆栈信息
     * @return
     */
    public static <T> Result initResultWithErrorStack(String errorCode, String errorDesc, List<CommonError> errors) {
        Result result = new Result();
        result.setIsSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorDesc(errorDesc);
        ErrorContext errorContext = new ErrorContext(errors);
        result.setErrorContext(errorContext);
        return result;
    }

    /**
     * 初始化一个错误的消息，包含多个子业务错误堆栈信息及多个错误参数集合
     *
     * @param errorCode   错误编码
     * @param errorDesc   错误描述
     * @param errors      错误堆栈信息
     * @param errorParams 错误参数集合
     * @return
     */
    public static <T> Result initResultWithErrorStack(String errorCode, String errorDesc, List<CommonError> errors, List<Object> errorParams) {
        Result result = new Result();
        result.setIsSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorDesc(errorDesc);
        ErrorContext errorContext = new ErrorContext(errors);
        result.setErrorContext(errorContext);
        Object[] errorParameters = null;
        errorParameters = errorParams.toArray(errorParameters);
        result.setErrorParameters(errorParameters);
        return result;
    }

}
