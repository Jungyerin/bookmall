package com.jx372.bookmall.vo;

public class CartVo {
	
	private String name;
	private String title;
	private int price;
	private int number;
	private Long b_no;
	private Long m_no;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public Long getB_no() {
		return b_no;
	}
	public void setB_no(Long b_no) {
		this.b_no = b_no;
	}
	public Long getM_no() {
		return m_no;
	}
	public void setM_no(Long m_no) {
		this.m_no = m_no;
	}
	@Override
	public String toString() {
		return "카트목록 [회원이름=" + name + ", 책 제목=" + title + ", 가격=" + price + ", 수량=" + number + "]";
	}
	
	

}
