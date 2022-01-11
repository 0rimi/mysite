package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("/user");
		
		//?action= 뒤에 올값으로 각 주소 > 파라미터 가져오기
		String act = request.getParameter("action");
		//System.out.println(act);
			
		if("loginForm".equals(act)) {
				
			System.out.println("user> loginForm");
				
			
			//포워드전에 값을 넣어놓기!
				
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
				
		}else if("joinForm".equals(act)) {
				
			System.out.println("user> joinForm");
				
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
				
		}else if("join".equals(act)) {
				
			System.out.println("user> join");
				
			//파라미터값 받기
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
				
			//Vo에 넣어주기
			UserVo userVo = new UserVo(id, password, name, gender);
			
			//dao쓸거임
			UserDao userDao = new UserDao();
			userDao.insert(userVo);
			
			System.out.println(userVo);
			
			//리다이렉트
			//완료되었습니다~ 창
			WebUtil.forward(request, response, "WEB-INF/views/user/joinOk.jsp");
			
		}else{
			System.out.println("파라미터값이 없습니다");
		}
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
		
	}

}
