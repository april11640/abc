package yu.abc.baseframework.common.lang;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 
 * @author zsp
 * 
 * @param <T>
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 3509406426584089712L;

	/**
	 *
	 */
	private List<T> rows;
	/**
	 *
	 */
	private int total;

	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 *
	 */
	public Page() {

	}

	/**
	 *
	 *
	 * @param rows
	 * @param total
	 */
	public Page(List<T> rows, int total) {
		this.rows = rows;
		this.total = total;
	}

}