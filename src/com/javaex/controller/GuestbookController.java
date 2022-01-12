package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/guest")
public class GuestbookController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("/guestbook");
		
		//?action= 뒤에 올값으로 각 주소 > 파라미터 가져오기
		String act = request.getParameter("action");
		//System.out.println(act);
			
		if("addList".equals(act)) {
				
			System.out.println("user> addList");
				
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> gbList = guestbookDao.getList();
			
			//포워드전에 값을 넣어놓기!
			request.setAttribute("gbList", gbList);
								//꺼내쓸이름, 넣어줄아이
			
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
				
			
		}else if("add".equals(act)){
			System.out.println("action=add");
			
			//파라미터 3개를 꺼내온다
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			//vo로 만든다
			GuestbookVo guestbookVo = new GuestbookVo(name,password,content);
			System.out.println(guestbookVo);			
			
			//dao 메모리 올린다
			GuestbookDao guestbookDao = new GuestbookDao();
						
			//dao.insert(vo);
			guestbookDao.insert(guestbookVo);
			
			//리다이렉트
			response.sendRedirect("/mysite/guest?action=addList");
			
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);

	}

}
