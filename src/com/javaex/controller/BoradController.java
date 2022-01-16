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
				
		}else if("write".equals(act)) {
			
			System.out.println("board> write");
			
			//파라미터값 가져오기
			String title = request.getParameter("title");
			String content = request.getParameter("content");
				//유저넘버는 인풋박스에서!!
			String num = request.getParameter("no");
			int no = Integer.parseInt(num);
			
			System.out.println("제목" + title);
			System.out.println("내용" + content);
			System.out.println("유저번호" + no);
			
			//받아온 값을 넣어주는 메소드 사용
			BoardDao boardDao = new BoardDao();
						
			boardDao.insert(title, content, no);
			
			//등록후 리스트로 이동하는 리다이렉트
			WebUtil.redirect(request, response, "/mysite/board?action=list");
					
		}else if("delete".equals(act)){
			
			System.out.println("board> delete");
			
			//게시물쓴 유저넘버 파라미터값 가져오기
			String unum = request.getParameter("no");
			int no = Integer.parseInt(unum); 
			
			System.out.println("게시글유저넘버" + no);
			
			BoardDao boardDao = new BoardDao();
			
			boardDao.delete(no);
			
			//등록후 리스트로 이동하는 리다이렉트
			WebUtil.redirect(request, response, "/mysite/board?action=list");
			
		}else if("read".equals(act)) {
			
			System.out.println("board> read");

			//파라미터값 받아오기
			String num = request.getParameter("no");
			int no = Integer.parseInt(num);

			//조회수 업데이트
			BoardDao boardDao = new BoardDao();
			boardDao.hit(no);
			
			//넘버값 넣고 해당 유저 정보 출력해주기
			BoardVo userVo = boardDao.get(no);
			
			//포워드해주기
			request.setAttribute("userVo", userVo);
			
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");
				
		}else if("modifyForm".equals(act)) {
			
			System.out.println("board> modifyForm");
			
			//파라미터값 얻기
			String num = request.getParameter("no");
			int no = Integer.parseInt(num);
		
			//기본 정보 입력해줄수 있는 기본 정보들 
			BoardDao boardDao = new BoardDao();
			
			//넘버값 넣고 해당 유저 정보 출력해주기
			BoardVo userVo = boardDao.get(no);
			
			//포워드해주기
			request.setAttribute("userVo", userVo);
	
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/board/modifyForm.jsp");
				
		}else if("modify".equals(act)) {
			
			System.out.println("board> modify");
			
			//파라미터값 얻기
			String num = request.getParameter("no");
			int no = Integer.parseInt(num);
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			//해당 넘버의 내용 수정하는 메소드 사용			
			//넘버, 제목, 내용 순으로 넣기
			BoardDao boardDao = new BoardDao();
			boardDao.update(no, title, content);
			
			//리다이렉트
			WebUtil.redirect(request, response, "/WEB-INF/views/board/modifyForm.jsp");
				
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
