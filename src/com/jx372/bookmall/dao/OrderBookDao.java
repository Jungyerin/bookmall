package com.jx372.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jx372.bookmall.vo.OrderBookVo;


public class OrderBookDao {

	private Connection getConnection() throws SQLException {

		Connection conn = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2.connection 하기
			String url = "jdbc:mysql://localhost:3306/book_mall?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "root", "00000");
		} catch (ClassNotFoundException e) {
			System.out.println();
		}
		return conn;

	}

	public boolean insert(OrderBookVo orderbookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3.statement 준비
			String sql = "insert into order_book values(?,?,?)";

			pstmt = conn.prepareStatement(sql); // import할때 java.sql로 해워쟈 됨

			// 4. 바인딩
			pstmt.setLong(1, orderbookVo.getO_no());
			pstmt.setLong(2, orderbookVo.getB_no());
			pstmt.setLong(3, orderbookVo.getNumber());

			// 4.sql문 실행
			int count = pstmt.executeUpdate(); // 업데이트한 갯수가 나옴

			return (count == 1);

		} catch (SQLException e) {
			System.out.println("error" + e);
			return false;
		} finally {
			/* 자원정리 */
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public List<OrderBookVo> getList() {
		List<OrderBookVo> list = new ArrayList<OrderBookVo>();

		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		try {
			conn = getConnection();

			// 3.statement 생성
			stmt = conn.createStatement(); // import할때 java.sql로 해줘야 나중에ㅔ 확장서잉
											// 좋음

			// 4.sql문 실행
			String sql = "select m.name,ob.o_no,ob.b_no,b.title,ob.number"
					+ " from order_book ob, book b, orders o,member m" + " where ob.b_no=b.b_no" + " and ob.o_no=o.o_no"
					+ " and o.m_no=m.m_no";
			rs = stmt.executeQuery(sql);

			// 5.fetch row(row를 하나씩 가져오기)
			while (rs.next()) {
				String name = rs.getString(1);
				Long ono = rs.getLong(2);
				Long bno = rs.getLong(3);
				String title = rs.getString(4);
				int number = rs.getInt(5);

				OrderBookVo orderbookVo = new OrderBookVo();

				orderbookVo.setName(name);
				orderbookVo.setO_no(ono);
				orderbookVo.setB_no(bno);
				orderbookVo.setTitle(title);
				orderbookVo.setNumber(number);

				list.add(orderbookVo);

			}

			return list;
		} catch (SQLException e) {
			System.out.println("error" + e);
			return list;
		} finally {
			/* 자원정리 */
			try {
				if (rs != null) {
					rs.close();
				}

				if (stmt != null) {
					stmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public OrderBookVo get(Long no) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		OrderBookVo orderbookVo = null;

		try {
			conn = getConnection();

			String sql = "select m.name,ob.o_no,ob.b_no,b.title,ob.number"
					+ " from order_book ob, book b, orders o,member m" 
					+ " where ob.b_no=b.b_no" 
					+ " and ob.o_no=o.o_no"
					+ " and o.m_no=m.m_no" 
					+ " and o_no=" + no;
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				orderbookVo = new OrderBookVo();
				orderbookVo.setName(rs.getString(1));
				orderbookVo.setO_no(rs.getLong(2));
				orderbookVo.setB_no(rs.getLong(3));
				orderbookVo.setTitle(rs.getString(4));
				orderbookVo.setNumber(rs.getInt(5));

			}

			return orderbookVo;
		} catch (SQLException e) {
			System.out.println("error" + e);
			return orderbookVo;
		} finally {
			/* 자원정리 */
			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3.statement 준비
			String sql = "delete from order_book "
						+ " where o_no="+no;

			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
//			pstmt.setLong(1, ono);
//			pstmt.setLong(2, bno);
//			pstmt.setLong(3, number);

			// 4.sql문 실행
			int count = pstmt.executeUpdate();
			conn.commit();

			return (count == 1);

		} catch (SQLException e) {
			System.out.println("error" + e);
			return false;
		} finally {
			/* 자원정리 */
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean update(OrderBookVo orderbookVo,Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update order_book set " 
					+ " b_no=?,number=? " 
					+ " where "
					+ " o_no="+no;

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, orderbookVo.getB_no());
			pstmt.setInt(2, orderbookVo.getNumber());


			int count = pstmt.executeUpdate(); // 업데이트한 갯수가 나옴

			return (count == 1);

		} catch (SQLException e) {
			System.out.println("error" + e);
			return false;
		} finally {
			/* 자원정리 */
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
