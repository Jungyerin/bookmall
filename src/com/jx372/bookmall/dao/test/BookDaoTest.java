package com.jx372.bookmall.dao.test;

import java.util.List;

import com.jx372.bookmall.dao.BookDao;
import com.jx372.bookmall.vo.BookVo;



public class BookDaoTest {
	public static void main(String[] args) {
		
		//insertTest("자바의 신",20000,2L);
		//insertTest("7년의 밤",13000,1L);
		//insertTest("타나토노트",20000,1L);
		//insertTest("주식알아보기",15000,4L);
		
		selectTest();
		updateTest(7L,"업데이트2",3L, 20000);
		selectTest(7L);
	//	deleteTest(5L);
	//	selectTest();

	}
	
	public static void insertTest(String title, int price, Long ca) {
		BookVo vo = new BookVo();
		vo.setB_title(title);
		vo.setB_price(price);
		vo.setC_no(ca);
		new BookDao().insert(vo);
	}

	public static void selectTest() {
		List<BookVo> list = new BookDao().getList();

		for (BookVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void selectTest(Long no) {
		BookVo vo = new BookDao().get(no);

		System.out.println("선택 하여 출력 : "+vo);
	}

	public static void deleteTest(Long no) {
		new BookDao().delete(no);
		System.out.println(no+"번 서적이 지워졌습니다!");
	}

	public static void updateTest(Long no, String title, Long cno,int price) {

		BookVo vo = new BookVo();
		
		vo.setB_title(title);
		vo.setC_no(cno);
		vo.setB_price(price);


		new BookDao().update(vo,no);

	}

}
