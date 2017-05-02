package com.jx372.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jx372.bookmall.vo.BookVo;

public class BookDao {
	
	private Connection getConnection() throws SQLException {

		Connection conn = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2.connection 하기
			String url = "jdbc:mysql://localhost:3306/book_mall?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "root", "00000");
		} catch (ClassNotFoundException e) {
			System.out.println();
		}
		return conn;

	}

	public boolean insert(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3.statement 준비
			String sql = "insert into book values(null,?,?,?)";

			pstmt = conn.prepareStatement(sql); 

			// 4. 바인딩
			pstmt.setString(1, bookVo.getB_title());
			pstmt.setInt(2, bookVo.getB_price());
			pstmt.setLong(3, bookVo.getC_no());

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

	public List<BookVo> getList() {
		List<BookVo> list = new ArrayList<BookVo>();

		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		try {
			conn = getConnection();

			// 3.statement 생성
			stmt = conn.createStatement(); // import할때 java.sql로 해줘야 나중에ㅔ 확장서잉
											// 좋음

			// 4.sql문 실행
			String sql = "select b.b_no,b.title,b.price,b.c_no,c.name"
						+" from book b, category c"
						+" where b.c_no=c.c_no";
			rs = stmt.executeQuery(sql);

			// 5.fetch row(row를 하나씩 가져오기)
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				int price=rs.getInt(3);
				Long cno=rs.getLong(4);
				String cname=rs.getString(5);

				BookVo bookVo = new BookVo();

				bookVo.setB_no(no);
				bookVo.setB_title(title);
				bookVo.setB_price(price);
				bookVo.setC_no(cno);
				bookVo.setCname(cname);				

				list.add(bookVo);

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

	public BookVo get(Long no) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		BookVo bookVo = null;

		try {
			conn = getConnection();

			String sql = "select b.b_no,b.title,b.price,b.c_no,c.name"
					+" from book b, category c"
					+" where b.c_no=c.c_no"
					+" and b_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				bookVo = new BookVo();
				bookVo.setB_no(rs.getLong(1));
				bookVo.setB_title(rs.getString(2));
				bookVo.setB_price(rs.getInt(3));
				bookVo.setC_no(rs.getLong(4));
				bookVo.setCname(rs.getString(5));

			}

			return bookVo;
		} catch (SQLException e) {
			System.out.println("error" + e);
			return bookVo;
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

			String sql = "delete from book where b_no="+no;

			pstmt = conn.prepareStatement(sql); 

			int count = pstmt.executeUpdate(); 

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

	public boolean update(BookVo bookVo, Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update book set "
					+ " title=?,price=?,c_no=? "
					+ " where b_no="+no;

			pstmt = conn.prepareStatement(sql); // import할때 java.sql로 해워쟈 됨

			pstmt.setString(1, bookVo.getB_title());
			pstmt.setInt(2, bookVo.getB_price());
			pstmt.setLong(3, bookVo.getC_no());
			
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
