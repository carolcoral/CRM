package com.hjcrm.publics.util;

import java.io.Serializable;

/**
 *  支持分页的Bean,记录一些页面信息.
 * @author likang
 * @date 2016-10-13 上午10:43:06
 */
public class PageBean implements Serializable {
	public static final ThreadLocal<PageBean> pageBeanThreadLocal = new ThreadLocal<PageBean>();
	
	
	private static final long serialVersionUID = 1L;

	private  Integer limit;
	private Integer offset;
	
	// 封装操作参数
	private String operation;

	public static final String NEXTPAGE = "next";
	public static final String PRIVPAGE = "priv";
	public static final String FIRSTPAGE = "first";
	public static final String LASTPAGE = "last";
	public static final String GOPAGE = "gopage";

	private int currentPage = 1;

	/** 总页数 */
	private int countPage;
	/** 总记录数 */
	private long countResult;
	private int pageSize = 20;
	private int firstResult = 0;

	public void nextPage() {
		currentPage++;
	}

	public void prevPage() {
		currentPage--;
	}

	public void goToPage(int page) {
		this.currentPage = page;
	}

	public void goToFirst() {
		this.currentPage = 1;
	}

	public void goToLast() {
		this.currentPage = Integer.MAX_VALUE;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
		this.pageSize = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public void config() {

	}

	public void switchOperation() {
		if (operation == null || "".equals(operation.trim()))
			return;
		if (NEXTPAGE.equalsIgnoreCase(operation.trim()))
			this.nextPage();
		else if (PRIVPAGE.equalsIgnoreCase(operation.trim()))
			this.prevPage();
		else if (FIRSTPAGE.equalsIgnoreCase(operation.trim()))
			this.goToFirst();
		else if (LASTPAGE.equalsIgnoreCase(operation.trim()))
			this.goToLast();
		else if (GOPAGE.equalsIgnoreCase(operation.trim())) {
			if (currentPage >= 0 || currentPage <= this.countPage)
				this.goToPage(currentPage);
			else
				this.goToFirst();
		} else
			this.goToFirst();
	}

	public void accountFirstResult() {
		// 为了提高查询性能，如果countPage没有设置值，当前页不再判断是否大于总页数。这样就不需要依赖数据总量来进行分页。
		if (countPage > 0 && currentPage > countPage)
			currentPage = countPage;
		
		if (currentPage <= 1)
			currentPage = 1;
		this.firstResult = (currentPage - 1) * pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCountPage() {
		return countResult == 0 ? 0 : countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public long getCountResult() {
		return countResult;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	// 不存在的成员变量page的getter和setter方法，用来封装currentPage.避免ParametersInterceptor的ERROR
	public String getPage() {
		return String.valueOf(currentPage);
	}

	public void setPage(String page) {
		try {
			this.currentPage = Integer.parseInt(page);
		} catch (NumberFormatException e) {
		}
	}

	public void setCountResult(long countResult) {
		Long countPageTemp = ((countResult - 1) / pageSize + 1);
		this.countPage = countPageTemp.intValue();
		this.countResult = countResult;
	}


	/** 
	 * 设置最大结果数并计算firstResult和maxResult
	 * @param countResult
	 * @author likang 
	 * @date 2016-10-13 上午10:43:00
	 */
	public void setCountResultAndAccountFirstResult(long countResult) {
		Long countPageTemp = ((countResult - 1) / pageSize + 1);
		this.countPage = countPageTemp.intValue();
		this.countResult = countResult;
		accountFirstResult();
	}
	
	//如果此标志为true,则不设置firstResult,并且不查询Count。
	private boolean noFirstResultAndNoCount = false;


	public boolean isNoFirstResultAndNoCount() {
		return noFirstResultAndNoCount;
	}

	public void setNoFirstResultAndNoCount(boolean noFirstResultAndNoCount) {
		this.noFirstResultAndNoCount = noFirstResultAndNoCount;
	}
}
