package com.jx372.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jx372.bookmall.vo.CartVo;

public class CartDao {
	
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

	public boolean insert(CartVo cartVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3.statement 준비
			String sql = "insert into cart values(?,?,?)";

			pstmt = conn.prepareStatement(sql); // import할때 java.sql로 해워쟈 됨

			// 4. 바인딩
			pstmt.setLong(1, cartVo.getB_no());
			pstmt.setLong(2, cartVo.getM_no());
			pstmt.setLong(3, cartVo.getNumber());

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

	public List<CartVo> getList() {
		List<CartVo> list = new ArrayList<CartVo>();

		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		try {
			conn = getConnection();

			// 3.statement 생성
			stmt = conn.createStatement(); // import할때 java.sql로 해줘야 나중에ㅔ 확장서잉
											// 좋음

			// 4.sql문 실행
			String sql = "select m.name, b.title, b.price, c.number"
						+" from member m, book b, cart c"
						+" where m.m_no=c.m_no"
						+" and b.b_no=c.b_no";
			rs = stmt.executeQuery(sql);

			// 5.fetch row(row를 하나씩 가져오기)
			while (rs.next()) {
				String name=rs.getString(1);
				String title = rs.getString(2);
				int price=rs.getInt(3);
				int number=rs.getInt(4);

				CartVo cartVo = new CartVo();

				cartVo.setName(name);
				cartVo.setTitle(title);
				cartVo.setPrice(price);
				cartVo.setNumber(number);				

				list.add(cartVo);

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

	public CartVo get(Long no) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		CartVo cartVo = null;

		try {
			conn = getConnection();

			String sql = "select m.name, b.title, b.price, c.number"
					+" from member m, book b, cart c"
					+" where m.m_no=c.m_no"
					+" and b.b_no=c.b_no"
					+" and c.m_no="+no;
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				cartVo = new CartVo();
				cartVo.setName(rs.getString(1));
				cartVo.setTitle(rs.getString(2));
				cartVo.setPrice(rs.getInt(3));
				cartVo.setNumber(rs.getInt(4));

			}

			return cartVo;
		} catch (SQLException e) {
			System.out.println("error" + e);
			return cartVo;
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
			String sql = "delete from cart where m_no="+no;

			pstmt = conn.prepareStatement(sql); 


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

	public boolean update(CartVo cartVo,Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update cart set "
					+ " b_no=?,number=? "
					+ " where m_no="+no;

			pstmt = conn.prepareStatement(sql); 

			pstmt.setLong(1, cartVo.getB_no());
			pstmt.setInt(3, cartVo.getNumber());


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
