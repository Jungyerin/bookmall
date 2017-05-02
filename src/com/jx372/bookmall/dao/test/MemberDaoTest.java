package com.jx372.bookmall.dao.test;

import java.util.List;

import com.jx372.bookmall.dao.MemberDao;
import com.jx372.bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		
		//insertTest();
		selectTest();
		updateTest(3L, "정예린33", "255", "cadi@hanmail.net", "1234");
		selectTest(3L);
		deleteTest(2L);
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
		

		System.out.println("선택하여 확인 : "+vo);
	}

	public static void deleteTest(Long no) {
		new MemberDao().delete(no);
		System.out.println(no+"번 째 회원을 지웠습니다.");
	}

	public static void updateTest(Long no, String name,String phone,String email,String pw) {

		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setPw(pw);
		new MemberDao().update(vo,no);

	}
}
