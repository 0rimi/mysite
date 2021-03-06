package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	
	//메소드일반
	
	//연결
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
	
	
	
	//닫기
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


	
	//저장 메소드(회원가입)
	public int insert(UserVo userVo) {
		
		int count = 0;
		this.getConnection();
		
		try {
		
		// 3. SQL문 준비 / 바인딩 / 실행
		
		//문자열준비
		String query = "";
		query += " INSERT INTO users ";
		query += " VALUES(seq_users_no.nextval, ?, ?, ?, ?) ";
		
		//쿼리문 만들기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		pstmt.setString(1, userVo.getId());
		pstmt.setString(2, userVo.getPassword());
		pstmt.setString(3, userVo.getName());
		pstmt.setString(4, userVo.getGender());
		
		//실행
		count = pstmt.executeUpdate();
			
		// 4.결과처리
		System.out.println(count + "건 삽입");
		
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	
		this.getclose();
		return count;	
		
	}
	
	
	//회원정보 1명 가져오기(회원정보수정용)
	//해당회원 넘버받고 다른 정보 주는용도
	public UserVo getUserinfo(int index) {
		
		this.getConnection();
		
		UserVo userVo = null;
		
		try {
		
		// 3. SQL문 준비 / 바인딩 / 실행
		
		//문자열준비
		String query = "";
		query += " SELECT  	no, ";
		query += " 			id, ";
		query += " 			password, ";
		query += " 			name, ";
		query += " 			gender ";
		query += " FROM users ";
		query += " WHERE no = ? ";
		
		//쿼리문 만들기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		pstmt.setInt(1, index);
		
		//실행
		rs = pstmt.executeQuery();
			
		// 4.결과처리
		while(rs.next()) {
			int no = rs.getInt("no");
			String id = rs.getString("id");
			String password = rs.getString("password");
			String name = rs.getString("name");
			String gender = rs.getString("gender");
			
			
			userVo = new UserVo();
			userVo.setNo(no);
			userVo.setId(id);
			userVo.setPassword(password);
			userVo.setName(name);
			userVo.setGender(gender);
			
		}
		
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	
		this.getclose();
		return userVo;	
		
	}
	
	//회원정보 1명 가져오기(회원정보수정용)
	//아이디 패스워드를 주면 해당 회원정보 목록 만듬.
	public UserVo getUser(String id, String password) {
		
		this.getConnection();
		
		UserVo userVo = null;
		
		try {
		
		// 3. SQL문 준비 / 바인딩 / 실행
		
		//문자열준비
		String query = "";
		query += " SELECT  	no, ";
		query += " 			name ";
		query += " FROM users ";
		query += " WHERE id = ? ";
		query += " AND password = ? ";
		
		//쿼리문 만들기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		pstmt.setString(1, id);
		pstmt.setString(2, password);
		
		//실행
		rs = pstmt.executeQuery();
			
		// 4.결과처리
		while(rs.next()) {
			int no = rs.getInt("no");
			String name = rs.getString("name");
			
			userVo = new UserVo();
			userVo.setNo(no);
			userVo.setName(name);
		}
		
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	
		this.getclose();
		return userVo;	
		
	}
	
	//회원정보 수정하는 메소드
	public int Update(UserVo userVo) {
		
		this.getConnection();
		UserVo upUser = null;
		int count = 0;
		
		try {
		
		// 3. SQL문 준비 / 바인딩 / 실행
		
		//문자열준비
		String query = "";
		query += " UPDATE   users ";
		query += " SET	    password = ?, ";
		query += " 			name = ?, ";
		query += " 			gender = ? ";
		query += " WHERE	no = ? ";
	
		//쿼리문 만들기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		pstmt.setString(1, userVo.getPassword());
		pstmt.setString(2, userVo.getName());
		pstmt.setString(3, userVo.getGender());
		pstmt.setInt(4, userVo.getNo());
		
		//실행
		count = pstmt.executeUpdate();
			
		// 4.결과처리
		System.out.println(count + "건 수정되었습니다.");
		
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	
		this.getclose();
		
		return count;
		
	}
	
	
	
	

}
