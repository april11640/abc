package yu.abc.baseframework.common.lang;

import java.io.Serializable;

/**
 * 操作结果。
 * 
 * @author zsp
 * @date 2017-07-13
 *
 * @param <T> 结果数据类型
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 2012311628803641849L;
	
	/**
	 * 成功
	 */
	public static final String OK = "ok";
	/**
	 * 内部服务错误错误码
	 */
	public static final String INTERNAL_SERVER_ERROR = "internal-server-error";
	/**
	 * 内部服务错误错误消息
	 */
	public static final String INTERNAL_SERVER_ERROR_MSG = "内部服务错误";

	/**
	 * 无效的请求错误码
	 */
	public static final String BAD_REQUEST = "bad-request";
	/**
	 * 未授权错误码
	 */
	public static final String UNAUTHORIZED = "unauthorized";
	/**
	 * 未找到错误码
	 */
	public static final String NOT_FOUND = "not-found";
	/**
	 * 请求超时错误码
	 */
	public static final String REQUEST_TIMEOUT = "request-timeout";
	/**
	 * 冲突错误码
	 */
	public static final String CONFLICT = "conflict";
	/**
	 * 未实现错误码
	 */
	public static final String NOT_IMPLEMENTED = "not-implemented";
	/**
	 * 服务不可用错误码
	 */
	public static final String SERVICE_UNAVAILABLE = "service-unavailable";
	/**
	 * 不正确的影响行数
	 */
	public static final String ERROR_ROWS_AFFECTED = "error-rows-affected";
	/**
	 * 状态错误
	 */
	public static final String ERROR_STATUS = "error-status";
	
	/**
	 * 错误码
	 */
	private String code = OK;
	/**
	 * 错误信息
	 */
	private String msg;
	/**
	 * 响应结果
	 */
	private T result;
	
	/**
	 * 判断返回结果是否正常。
	 * 
	 * @return 返回true说明ok，否则返回false.
	 */
	public boolean wasOk() {
		return OK.equals(code);
	}
	
	/**
	 * 构建一个响应。
	 */
	public Result() {
		
	}
	
	/**
	 * 构建一个响应。
	 *
	 * @param code 错误码
	 */
	public Result(String code) {
		this.code = code;
	}
	
	/**
	 * 构建一个响应。
	 *
	 * @param code 错误码
	 * @param msg 错误信息
	 */
	public Result(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * 返回一个缺省错误（internal-server-error）响应。
	 *
	 * @param <T> 响应结果类型
	 * @return 响应
	 */
	public static <T> Result<T> error() {
		return new Result<T>(INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * 返回一个错误响应。
	 *
	 * @param <T> 响应结果类型
	 * @param code 错误码
	 * @return 响应
	 */
	public static <T> Result<T> error(String code) {
		return new Result<T>(code);
	}
	
	/**
	 * 返回一个缺省错误（internal-server-error）响应。
	 *
	 * @param <T> 响应结果类型
	 * @return 响应
	 */
	public static <T> Result<T> errorWithMsg() {
		return new Result<T>(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
	}
	
	/**
	 * 返回一个错误响应。
	 *
	 * @param <T> 响应结果类型
	 * @param code 错误码
	 * @param msg 错误信息
	 * @return 响应
	 */
	public static <T> Result<T> errorWithMsg(String code, String msg) {
		return new Result<T>(code, msg);
	}
	
	/**
	 * 返回一个结果响应。
	 *
	 * @param <T> 响应结果类型
	 * @return 响应
	 */
	public static <T> Result<T> result() {
		return new Result<T>();
	}
	
	/**
	 * 返回一个结果响应。
	 *
	 * @param <T> 响应结果类型
	 * @param result 响应结果
	 * @return 响应
	 */
	public static <T> Result<T> result(T result) {
		Result<T> response = new Result<T>();
		response.setResult(result);
		return response;
	}
	
	/**
	 * 拷贝一个结果响应。
	 * 
	 * @param source 源对象
	 * @return 拷贝的对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> Result<T> copy(Result<?> source) {
		if(source == null) {
			throw new IllegalArgumentException();
		}
		Result<T> target = new Result<T>();
		target.setCode(source.getCode());
		target.setMsg(source.getMsg());
		Object result = source.getResult();
		if(result != null) {
			try {
				target.setResult((T)result);
			} catch(ClassCastException e) {
				//do nothing.
			}
		}
		return target;
	}

	@Override
	public String toString() {
		return "Result{" +
				"code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", result=" + result +
				'}';
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
}
