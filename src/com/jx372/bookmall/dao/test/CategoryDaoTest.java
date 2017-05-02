package com.jx372.bookmall.dao.test;

import java.util.List;

import com.jx372.bookmall.dao.CategoryDao;
import com.jx372.bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		
		//insertTest("소설");
		//insertTest("컴퓨터/IT");
		//insertTest("인문");
		//insertTest("경제");
		selectTest();
		
		
	}

	public static void insertTest(String cname) {
		CategoryVo vo = new CategoryVo();
		vo.setName(cname);

		new CategoryDao().insert(vo);
	}

	public static void selectTest() {
		List<CategoryVo> list = new CategoryDao().getList();

		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void selectTest(Long no) {
		CategoryVo vo = new CategoryDao().get(no);

		System.out.println(vo);
	}

	public static void deleteTest(Long no) {
		new CategoryDao().delete(no);
	}

	public static void updateTest(Long no) {

		CategoryVo vo = new CategoryVo();


		new CategoryDao().update(vo);

	}
}
