package com.jx372.bookmall.vo;

public class CategoryVo {
	
	private Long c_no;
	private String name;
	public Long getC_no() {
		return c_no;
	}
	public void setC_no(Long c_no) {
		this.c_no = c_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "카테고리 분류 [카테고리 번호=" + c_no + ", 카테고리=" + name + "]";
	}
	
	

}
