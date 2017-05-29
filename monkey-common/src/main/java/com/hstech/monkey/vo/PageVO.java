package com.hstech.monkey.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hstech.monkey.utils.PageUtils;

public class PageVO implements Serializable {

	private static final long serialVersionUID = 3638966220437679413L;

	public PageVO() {
		super();
	}

	public PageVO(List<Map<String, Object>> list) {
		super();
		this.list = list;
	}

	public PageVO(List<Map<String, Object>> list, int isLast) {
		this(list);
		this.isLast = isLast;
	}

	public PageVO(int page, List<Map<String, Object>> list, Number total) {
		if (total == null) {
			total = 0;
		}
		setTotal(total.longValue());
		setIsLast(PageUtils.isBottom(page, total.longValue()));
		setList(list);
		setCurrentPage(page);
	}

	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private int isLast = 0;//是否是最后一页 0不是最后一页 1是最后一页
	private long total = 0L;// 总数
	private int currentPage = 1;
	private String remain;//保留字段
	private String title;

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public int getIsLast() {
		return isLast;
	}

	public void setIsLast(int isLast) {
		this.isLast = isLast;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		this.remain = remain;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
