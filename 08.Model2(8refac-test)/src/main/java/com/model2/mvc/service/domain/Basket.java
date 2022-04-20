package com.model2.mvc.service.domain;

import java.sql.Date;

public class Basket {
	
	/// Field
	private int basketNo;
	private String userId;
	private int prodNo;
	private Date regDate;

	/// Constructor
	public Basket() {
	}

	/// Method
	// (setter/getter)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProdNo() {
		return prodNo;
	}

	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	
	public int getBasketNo() {
		return basketNo;
	}

	public void setBasketNo(int basketNo) {
		this.basketNo = basketNo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	/// ToString Overriding
	@Override
	public String toString() {
		return "Basket [basketNo=" + basketNo + ", userId=" + userId + ", prodNo=" + prodNo + ", regDate=" + regDate
				+ "]";
	}
	
}
