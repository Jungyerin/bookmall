package com.jx372.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jx372.bookmall.vo.OrderVo;


public class OrderDao {
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

	public boolean insert(OrderVo orderVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3.statement 준비
			String sql = "insert into orders values(?,?,?,?)";

			pstmt = conn.prepareStatement(sql); // import할때 java.sql로 해워쟈 됨

			// 4. 바인딩
			pstmt.setLong(1, orderVo.getO_no());
			pstmt.setLong(2, orderVo.getM_no());
			pstmt.setInt(3, orderVo.getPrice());
			pstmt.setString(4, orderVo.getAddress());

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

	public List<OrderVo> getList() {
		List<OrderVo> list = new ArrayList<OrderVo>();

		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		try {
			conn = getConnection();

			// 3.statement 생성
			stmt = conn.createStatement(); // import할때 java.sql로 해줘야 나중에ㅔ 확장서잉
											// 좋음

			// 4.sql문 실행
			String sql = "select o.o_no, m.name,m.email,o.price,o.address "
						+" from orders o,member m"
						+" where o.m_no=m.m_no";
			rs = stmt.executeQuery(sql);

			// 5.fetch row(row를 하나씩 가져오기)
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				int price=rs.getInt(4);
				String address = rs.getString(5);

				OrderVo orderVo = new OrderVo();

				orderVo.setO_no(no);
				orderVo.setName(name);
				orderVo.setEmail(email);
				orderVo.setPrice(price);
				orderVo.setAddress(address);				

				list.add(orderVo);

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

	public OrderVo get(Long no) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		OrderVo orderVo = null;

		try {
			conn = getConnection();

			String sql = "select o.o_no, m.name,m.email,o.price,o.address "
					+" from orders o,member m"
					+" where o.m_no=m.m_noo"
					+" and o.o_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				orderVo = new OrderVo();
				orderVo.setO_no(rs.getLong(1));
				orderVo.setName(rs.getString(2));
				orderVo.setEmail(rs.getString(3));
				orderVo.setPrice(rs.getInt(4));
				orderVo.setAddress(rs.getString(5));

			}

			return orderVo;
		} catch (SQLException e) {
			System.out.println("error" + e);
			return orderVo;
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
			String sql = "delete from orders where o_no=?";

			pstmt = conn.prepareStatement(sql); // import할때 java.sql로 해워쟈 됨

			// 4. 바인딩
			pstmt.setLong(1, no);

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

	public boolean update(OrderVo orderVo) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update orders set "
					+ " m_no=?,price=?,address=? "
					+ " where o_no=?";

			pstmt = conn.prepareStatement(sql); // import할때 java.sql로 해워쟈 됨

			pstmt.setLong(1, orderVo.getM_no());
			pstmt.setInt(2, orderVo.getPrice());
			pstmt.setString(3, orderVo.getAddress());

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
