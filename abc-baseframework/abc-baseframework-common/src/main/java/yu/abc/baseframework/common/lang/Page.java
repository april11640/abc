package yu.abc.baseframework.common.lang;

import java.io.Serializable;
import java.util.List;

/**
 * 数据分页
 * 
 * @author zsp
 * @date 2017-07-13
 * 
 * @param <T> 数据类型
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 3509406426584089712L;

	/**
	 * 当前页记录集合
	 */
	private List<T> rows;
	/**
	 * 符合条件的总记录个数
	 */
	private int total;

	/**
	 * 构建一个分页对象
	 */
	public Page() {

	}

	/**
	 * 构建一个分页对象
	 *
	 * @param rows
	 * @param total
	 */
	public Page(List<T> rows, int total) {
		this.rows = rows;
		this.total = total;
	}

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

}