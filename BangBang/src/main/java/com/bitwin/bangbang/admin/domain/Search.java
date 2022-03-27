package com.bitwin.bangbang.admin.domain;

public class Search {
	private String keyword;
	private String search;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "Search [keyword=" + keyword + ", search=" + search + "]";
	}

}
