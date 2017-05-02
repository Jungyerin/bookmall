package com.jx372.bookmall.dao.test;

import java.util.List;

import com.jx372.bookmall.dao.CartDao;
import com.jx372.bookmall.vo.CartVo;

public class CartDaoTest {
	public static void main(String[] args) {
		
		//insertTest(1L, 1L, 1);
		//insertTest(3L, 1L, 2);
		selectTest();

	}

	public static void insertTest(Long bno, Long mno, int number) {
		CartVo vo = new CartVo();
		vo.setB_no(bno);
		vo.setM_no(mno);
		vo.setNumber(number);

		new CartDao().insert(vo);
	}

	public static void selectTest() {
		List<CartVo> list = new CartDao().getList();

		for (CartVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void selectTest(Long no) {
		CartVo vo = new CartDao().get(no);

		System.out.println(vo);
	}

	public static void deleteTest(Long no) {
		new CartDao().delete(no);
	}

	public static void updateTest(Long no) {

		CartVo vo = new CartVo();

		new CartDao().update(vo,no);

	}

}
