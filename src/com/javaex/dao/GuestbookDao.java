package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {
	
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	//생성자
	
	//메소드gs
	
	//메소드일반		
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	public void getclose() {
		// 5. 자원정리
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
		System.out.println("error:" + e);
		}
	}
	
	//insert
	public void insert(GuestbookVo guestbookVo) {
		
		int count = 0;
		this.getConnection();
		
		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			
			//문자열준비
			String query = "";
			query += " INSERT INTO guestbook ";
			query += " VALUES(seq_guestbook_no.nextval, ?, ?, ?, SYSDATE) ";
			
			//쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, guestbookVo.getName()); // 첫번째 물음표
			pstmt.setString(2, guestbookVo.getPassword());
			pstmt.setString(3, guestbookVo.getContent());
						
			//실행
			count = pstmt.executeUpdate();
				
		// 4.결과처리
			System.out.println(count + "건 삽입");
			
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		
		this.getclose();
		
	}
	
		//delete
		public int delete(GuestbookVo vo) {
			
			int count = 0;
			getConnection();
			
			try {
								
				// 3. SQL문 준비 / 바인딩 / 실행
				
				//문자열준비
				 String query ="";
		         query += " delete from guestbook "; 
		         query += " where no= ? " ; 
		         query += " and password= ? " ;

							
				//쿼리문 만들기
		        pstmt = conn.prepareStatement(query);
				
				//바인딩
		        pstmt.setInt(1, vo.getNo());
		        pstmt.setString(2, vo.getPassword());

							
				//실행
				count = pstmt.executeUpdate();
					
				// 4.결과처리
				System.out.println(count + "건 삭제");
				
			} catch (SQLException e) {
			System.out.println("error:" + e);
			}
			
			getclose();
		    return count;
			
		}

	
	//리스트출력
	public List<GuestbookVo> getList() {
		
		this.getConnection();
		List<GuestbookVo> guestbookList = new ArrayList<GuestbookVo>();
		
		// 3. SQL문 준비 / 바인딩 / 실행
		
		try {	
			//문자열준비
			String query = "";
			query += " SELECT no, ";
			query += " 		  name, ";
			query += " 		  password, ";
			query += " 		  content, ";
			query += " 		  to_char(reg_date, 'yyyy-mm-dd hh24:mi:ss') reg_date ";
			query += " FROM guestbook ";
			query += " ORDER BY reg_date desc ";
			
			//쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩(x)
						
			//실행
			rs = pstmt.executeQuery();
				
		// 4.결과처리
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				
				GuestbookVo guestbookVo = new GuestbookVo(no, name, password, content, regDate);
				guestbookList.add(guestbookVo);
			}
			
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		
		this.getclose();
				
		return guestbookList;
	}


//특정no인사람 출력
	public GuestbookVo select(int index) {
		
		this.getConnection();
		GuestbookVo guestbookVo = new GuestbookVo();
		
		// 3. SQL문 준비 / 바인딩 / 실행
		
		try {	
			//문자열준비
			String query = "";
			query += " SELECT no, ";
			query += " 		  name, ";
			query += " 		  password, ";
			query += " 		  content, ";
			query += " 		  to_char(reg_date, 'yyyy-mm-dd hh24:mi:ss') reg_date ";
			query += " FROM guestbook ";
			query += " WHERE no like ? ";
			
			//쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setInt(1, index );
						
			//실행
			rs = pstmt.executeQuery();
				
		// 4.결과처리
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				
				guestbookVo.setNo(no);
				guestbookVo.setName(name);
				guestbookVo.setPassword(password);
				guestbookVo.setContent(content);
				guestbookVo.setRegDate(regDate);
			}
			
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		
		this.getclose();
				
		return guestbookVo;
	}
}

