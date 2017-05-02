package com.jx372.bookmall.dao.test;

import java.util.List;

import com.jx372.bookmall.dao.OrderDao;
import com.jx372.bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		
		//insertTest(1L, 1L, 40000, "서울시 서초구 비트교육센터");
		selectTest();
	}


	public static void insertTest(Long ono, Long omno, int price, String address) {
		OrderVo vo = new OrderVo();
		vo.setO_no(ono);
		vo.setM_no(omno);
		vo.setPrice(price);
		vo.setAddress(address);

		new OrderDao().insert(vo);
	}

	public static void selectTest() {
		List<OrderVo> list = new OrderDao().getList();

		for (OrderVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void selectTest(Long no) {
		OrderVo vo = new OrderDao().get(no);

		System.out.println(vo);
	}

	public static void deleteTest(Long no) {
		new OrderDao().delete(no);
	}

	public static void updateTest(Long no) {

		OrderVo vo = new OrderVo();

		new OrderDao().update(vo);

	}

}
