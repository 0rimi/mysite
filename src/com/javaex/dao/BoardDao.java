package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestbookVo;

public class BoardDao {
	
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
	
	//리스트 전체 출력(게시판리스트용)
	public List<BoardVo> list() {
		
		this.getConnection();
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			
			//문자열준비
			String query = "";
			query += " SELECT  	no, ";
			query += " 			title, ";
			query += " 			content, ";
			query += " 			hit, ";
			query += " 			to_char(reg_date, 'yyyy-mm-dd') regdate, ";
			query += " 			user_no ";
			query += " FROM board ";
			
			
			//쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩 없음.
			
			//실행
			rs = pstmt.executeQuery();
				
			// 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regdate = rs.getString("regdate");
				int userNo = rs.getInt("user_no");
				
				BoardVo boardVo = new BoardVo(no, title, content, hit, regdate, userNo);
				boardList.add(boardVo);
			}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		
			this.getclose();
			return boardList;
			
	}
	
	
	
	
	//조회수 늘어나는 메소드


	

}
