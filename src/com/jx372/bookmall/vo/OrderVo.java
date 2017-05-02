package com.jx372.bookmall.vo;

public class OrderVo {
	
	private Long o_no;
	private Long m_no;
	private String name;
	private String email;
	private int price;
	private String address;
	public Long getO_no() {
		return o_no;
	}
	public void setO_no(Long o_no) {
		this.o_no = o_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public Long getM_no() {
		return m_no;
	}
	public void setM_no(Long m_no) {
		this.m_no = m_no;
	}
	@Override
	public String toString() {
		return "주문정보 [주문번호=" + o_no + ", 회원이름=" + name + ", email=" + email + ", 총 결제금액=" + price + ", 배송지="
				+ address + "]";
	}
	
	
	
	

}
