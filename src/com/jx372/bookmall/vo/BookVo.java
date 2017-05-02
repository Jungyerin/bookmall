package com.jx372.bookmall.vo;

public class BookVo {

	private Long b_no;
	private String b_title;
	private int b_price;
	private Long c_no;
	private String cname;


	public Long getB_no() {
		return b_no;
	}

	public void setB_no(Long b_no) {
		this.b_no = b_no;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public int getB_price() {
		return b_price;
	}

	public void setB_price(int b_price) {
		this.b_price = b_price;
	}

	public Long getC_no() {
		return c_no;
	}

	public void setC_no(Long bc_no) {
		this.c_no = bc_no;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "서적목록 [책 번호=" + b_no + ", 책 제목=" + b_title + ", 가격=" + b_price + "," +"카테고리="
				+ cname + "]";
	}

	




	
}
