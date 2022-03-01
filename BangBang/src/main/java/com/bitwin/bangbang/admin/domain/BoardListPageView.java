package com.bitwin.bangbang.admin.domain;

import java.util.List;

public class BoardListPageView {

	private int totalCount;
	private int currentPage;
	private int countPerPage;
	private List<Board> list;
	private int pageTotalCount;

	public BoardListPageView(int totalCount, int currentPage, int countPerPage,List<Board> list) {
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.countPerPage = countPerPage;
		this.list = list;
		calTotalCount();
	}

	private void calTotalCount() {
		pageTotalCount = totalCount / countPerPage;
		pageTotalCount = totalCount % countPerPage > 0 ? ++pageTotalCount : pageTotalCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	public List<Board> getList() {
		return list;
	}

	public void setList(List<Board> list) {
		this.list = list;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	@Override
	public String toString() {
		return "ListPageView [totalCount=" + totalCount + ", currentPage=" + currentPage + ", countPerPage="
				+ countPerPage + ", list=" + list + ", pageTotalCount=" + pageTotalCount + "]";
	}

}