package yu.abc.baseframework.common.exceptions;

/**
 * 基础业务异常
 * 
 * @author zsp
 */
public class BaseBizException extends Exception {
	
	private static final long serialVersionUID = -7678875967251181234L;

	public BaseBizException(String msg) {
		super(msg);
	}
	
	public BaseBizException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public BaseBizException(Throwable cause) {
		super(cause);
	}
	
}
