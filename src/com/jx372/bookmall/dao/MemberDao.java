package com.jx372.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jx372.bookmall.vo.MemberVo;


public class MemberDao {
	
	private Connection getConnection() throws SQLException {

		Connection conn = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2.connection 하기
			String url = "jdbc:mysql://localhost:3306/book_mall?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "root", "00000");
		//	System.out.println("연결성공");
		} catch (ClassNotFoundException e) {
		//	System.out.println("연결안됨");
		}
		return conn;

	}

	public boolean insert(MemberVo memberVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			
		//	System.out.println(bookVo.getM_name()+" "+bookVo.getM_email());

			// 3.statement 준비
			String sql = "insert into member values(null,?,?,?,password(?))";

		//	System.out.println(sql);
			pstmt = conn.prepareStatement(sql); 

			// 4. 바인딩
			pstmt.setString(1, memberVo.getName());
			pstmt.setString(2, memberVo.getPhone());
			pstmt.setString(3, memberVo.getEmail());
			pstmt.setString(4, memberVo.getPw());

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

	public List<MemberVo> getList() {
		List<MemberVo> list = new ArrayList<MemberVo>();

		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		try {
			conn = getConnection();

			// 3.statement 생성
			stmt = conn.createStatement(); // import할때 java.sql로 해줘야 나중에ㅔ 확장서잉
											// 좋음

			// 4.sql문 실행
			String sql = "select m_no,name,phone,email,pw from member";
			rs = stmt.executeQuery(sql);

			// 5.fetch row(row를 하나씩 가져오기)
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String phone=rs.getString(3);
				String email=rs.getString(4);
				String pw=rs.getString(5);

				MemberVo memberVo = new MemberVo();

				memberVo.setNo(no);
				memberVo.setName(name);
				memberVo.setPhone(phone);
				memberVo.setEmail(email);
				memberVo.setPw(pw);
				

				list.add(memberVo);

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

	public MemberVo get(Long no) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		MemberVo memberVo = null;

		try {
			conn = getConnection();

			String sql = "select m_no,name,phone,email,pw from member where m_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				memberVo = new MemberVo();
				memberVo.setNo(rs.getLong(1));
				memberVo.setName(rs.getString(2));
				memberVo.setPhone(rs.getString(3));
				memberVo.setEmail(rs.getString(4));
				memberVo.setPw(rs.getString(5));

			}

			return memberVo;
		} catch (SQLException e) {
			System.out.println("error" + e);
			return memberVo;
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
			String sql = "delete from member where m_no="+no;

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

	public boolean update(MemberVo memberVo, Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update member set name=?,phone=?,email=?,pw=password(?) where m_no="+no;

			pstmt = conn.prepareStatement(sql); // import할때 java.sql로 해워쟈 됨

			pstmt.setString(1, memberVo.getName());
			pstmt.setString(2, memberVo.getPhone());
			pstmt.setString(3, memberVo.getEmail());
			pstmt.setString(4, memberVo.getPw());

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
