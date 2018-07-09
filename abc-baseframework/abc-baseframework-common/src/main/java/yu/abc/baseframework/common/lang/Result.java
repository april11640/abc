package yu.abc.baseframework.common.lang;

import java.io.Serializable;

/**
 *
 * 
 * @author zsp
 * @date 2017-07-13
 *
 * @param <T>
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 2012311628803641849L;
	
	/**
	 *
	 */
	public static final String OK = "ok";
	/**
	 *
	 */
	public static final String INTERNAL_SERVER_ERROR = "internal-server-error";

	/**
	 *
	 */
	public static final String INTERNAL_SERVER_ERROR_MSG = "内部服务错误";

	/**
	 *
	 */
	public static final String BAD_REQUEST = "bad-request";
	/**
	 *
	 */
	public static final String UNAUTHORIZED = "unauthorized";
	/**
	 *
	 */
	public static final String NOT_FOUND = "not-found";
	/**
	 *
	 */
	public static final String REQUEST_TIMEOUT = "request-timeout";
	/**
	 *
	 */
	public static final String CONFLICT = "conflict";
	/**
	 *
	 */
	public static final String NOT_IMPLEMENTED = "not-implemented";
	/**
	 *
	 */
	public static final String SERVICE_UNAVAILABLE = "service-unavailable";
	/**
	 *
	 */
	public static final String ERROR_ROWS_AFFECTED = "error-rows-affected";
	/**
	 *
	 */
	public static final String ERROR_STATUS = "error-status";
	
	/**
	 *
	 */
	private String code = OK;
	/**
	 *
	 */
	private String msg;
	/**
	 *
	 */
	private T result;
	
	/**
	 *
	 * 
	 * @return 返回true说明ok，否则返回false.
	 */
	public boolean wasOk() {
		return OK.equals(code);
	}
	
	/**
	 *
	 */
	public Result() {
		
	}
	
	/**
	 *
	 *
	 * @param code
	 */
	public Result(String code) {
		this.code = code;
	}
	
	/**
	 *
	 *
	 * @param code
	 * @param msg
	 */
	public Result(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 *
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> Result<T> error() {
		return new Result<T>(INTERNAL_SERVER_ERROR);
	}
	
	/**
	 *
	 *
	 * @param <T>
	 * @param code
	 * @return
	 */
	public static <T> Result<T> error(String code) {
		return new Result<T>(code);
	}
	
	/**
	 *
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> Result<T> errorWithMsg() {
		return new Result<T>(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
	}
	
	/**
	 *
	 *
	 * @param <T>
	 * @param code
	 * @param msg
	 * @return
	 */
	public static <T> Result<T> errorWithMsg(String code, String msg) {
		return new Result<T>(code, msg);
	}
	
	/**
	 *
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> Result<T> result() {
		return new Result<T>();
	}
	
	/**
	 *
	 *
	 * @param <T>
	 * @param result
	 * @return
	 */
	public static <T> Result<T> result(T result) {
		Result<T> response = new Result<T>();
		response.setResult(result);
		return response;
	}
	
	/**
	 *
	 * 
	 * @param source
	 * @return
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
		return "{code: " 
				+ code 
				+ ",\nmsg: " 
				+ msg 
				+ ",\nresult: " 
				+ result 
				+ "}";
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
