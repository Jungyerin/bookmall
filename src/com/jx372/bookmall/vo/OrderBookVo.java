package com.jx372.bookmall.vo;

public class OrderBookVo {
	
	private String name;
	private Long o_no;
	private Long b_no;
	private String title;
	private int number;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getO_no() {
		return o_no;
	}
	public void setO_no(Long o_no) {
		this.o_no = o_no;
	}
	public Long getB_no() {
		return b_no;
	}
	public void setB_no(Long b_no) {
		this.b_no = b_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "주문내역 [회원이름=" + name + ", 주문번호=" + o_no + ", 책 번호=" + b_no + ", 책 이름=" + title + ", 수량="
				+ number + "]";
	}
	
	

}
