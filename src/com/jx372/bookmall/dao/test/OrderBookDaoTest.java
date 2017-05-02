package com.jx372.bookmall.dao.test;

import java.util.List;

import com.jx372.bookmall.dao.OrderBookDao;
import com.jx372.bookmall.vo.OrderBookVo;

public class OrderBookDaoTest {

	public static void main(String[] args) {
		//insertTest(1L, 1L, 1);
		//insertTest(1L, 3L, 1);
		selectTest();

	}
	
	public static void insertTest(Long ono, Long bno, int number) {
		OrderBookVo vo = new OrderBookVo();
		vo.setO_no(ono);
		vo.setB_no(bno);
		vo.setNumber(number);

		new OrderBookDao().insert(vo);
	}

	public static void selectTest() {
		List<OrderBookVo> list = new OrderBookDao().getList();

		for (OrderBookVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void selectTest(Long no) {
		OrderBookVo vo = new OrderBookDao().get(no);

		System.out.println(vo);
	}

	public static void deleteTest(Long no) {
		new OrderBookDao().delete(no);
	}

	public static void updateTest(Long no) {

		OrderBookVo vo = new OrderBookVo();

		new OrderBookDao().update(vo,no);

	}

}
