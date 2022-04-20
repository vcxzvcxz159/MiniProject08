package com.model2.mvc.common;


public class Search {
	
	private int currentPage;
	private String searchCondition;
	private String searchKeyword;
	private int pageSize;
	
	private int endRowNum;
	private int startRowNum;
	
	
	private String minCharge;
	private String maxCharge;
	private int priceCondition;

	public Search(){
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getMinCharge() {
		return minCharge;
	}

	public void setMinCharge(String minCharge) {
		this.minCharge = minCharge;
	}

	public String getMaxCharge() {
		return maxCharge;
	}

	public void setMaxCharge(String maxCharge) {
		this.maxCharge = maxCharge;
	}
	public int getPriceCondition() {
		return priceCondition;
	}

	public void setPriceCondition(int priceCondition) {
		this.priceCondition = priceCondition;
	}
	
	//==> Select Query 시 ROWNUM 마지막 값 
	public int getEndRowNum() {
		return getCurrentPage()*getPageSize();
	}
	//==> Select Query 시 ROWNUM 시작 값
	public int getStartRowNum() {
		return (getCurrentPage()-1)*getPageSize()+1;
	}
}