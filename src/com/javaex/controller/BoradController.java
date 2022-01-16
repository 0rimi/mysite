package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.BoardDao;
import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;


@WebServlet("/board")
public class BoradController extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("/board");
		request.setCharacterEncoding("UTF-8");
		
		//?action= 뒤에 올값으로 각 주소 > 파라미터 가져오기
		String act = request.getParameter("action");
		
		if("list".equals(act)) {
			
			System.out.println("board> list");
			
		
			////boardDao에서 리스트 출력해주는 메소드 가져오기////
			BoardDao boardDao = new BoardDao();
			//리스트만들어주기
			List<BoardVo> bdList = boardDao.list();
			
			//UserDao의 넘버값 넣어주면 userinfo주는 메소드 사용
			//일단쓸것 들 선언
			UserVo uservo = new UserVo();
			UserDao userDao = new UserDao();
			//bdList의 넘버값 받아오기
			for(int i=0; i<bdList.size(); i++) {
				int no = bdList.get(i).getNo();
				
				String name = userDao.getUserinfo(no).getName();
				
				bdList.get(i).setName(name);					
			}
			
			System.out.println(bdList);
			
			//포워드전에 값을 넣어놓기
			request.setAttribute("bdList", bdList);
								//꺼내쓸이름, 넣어줄아이
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
				
		}else if("writeForm".equals(act)) {
			
			System.out.println("board> writeForm");
				
			//포워드전에 값을 넣어놓기!
				
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/board/writeForm.jsp");
				
		}else if("delete".equals(act)){
			
			System.out.println("board> delete");
			
		}else if("write".equals(act)) {
			
			System.out.println("board> write");
			
			//파라미터값 가져오기
			String title = request.getParameter("title");
			String content = request.getParameter("content");
				//유저넘버는 인풋박스에서!!
			String num = request.getParameter("no");
			int no = Integer.parseInt(num);
			
			System.out.println(title);
			System.out.println(content);
			System.out.println(no);
			
			//받아온 값을 넣어주는 메소드 사용
			BoardDao boardDao = new BoardDao();
						
			boardDao.insert(title, content, no);
			
			//등록후 리스트로 이동하는 리다이렉트
			WebUtil.redirect(request, response, "/mysite/board?action=list");
					
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
