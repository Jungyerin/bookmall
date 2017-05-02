package com.jx372.bookmall.dao.test;

import java.util.List;

import com.jx372.bookmall.dao.MemberDao;
import com.jx372.bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		
		//insertTest();
		selectTest();
	}
	
	public static void insertTest() {
		MemberVo vo = new MemberVo();
		vo.setName("정예린3");
		vo.setPhone("89543");
		vo.setEmail("cadi3@gmail.com");
		vo.setPw("12343");

		new MemberDao().insert(vo);
	}

	public static void selectTest() {
		List<MemberVo> list = new MemberDao().getList();

		for (MemberVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void selectTest(Long no) {
		MemberVo vo = new MemberDao().get(no);

		System.out.println(vo);
	}

	public static void deleteTest(Long no) {
		new MemberDao().delete(no);
	}

	public static void updateTest(Long no) {

		MemberVo vo = new MemberVo();


		new MemberDao().update(vo);

	}
}
