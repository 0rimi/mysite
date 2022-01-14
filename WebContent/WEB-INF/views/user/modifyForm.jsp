<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
    
<%@ page import="com.javaex.vo.UserVo" %>
<%@ page import="com.javaex.dao.UserDao" %>
    
<%
	UserVo authUser = (UserVo)session.getAttribute("authUser");
  	
	UserVo userinfo = (UserVo)request.getAttribute("userinfo");
	/*
	//인증된 유저no와 같은 유저정보(이름, 패스워드, 아이디, 성별) 가져오는 userinfo만들기
	UserDao userDao = new UserDao();
	//유저넘버 넣어주기	
	int index = authUser.getNo();
	UserVo userinfo = userDao.getUserinfo(index);
	*/
  
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>회원</h2>
				<ul>
					<li>회원정보</li>
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>회원정보</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원정보</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				 <!-- //content-head -->
	
				<div id="user">
					<div id="modifyForm">
						<form action="" method="">
	
							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label> 
								<span class="text-large bold"><%=userinfo.getId()%></span>
							</div>
	
							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> 
								<input type="text" id="input-pass" name="password" value="<%=userinfo.getPassword() %>" placeholder="비밀번호를 입력하세요"	>
							</div>
	
							<!-- 이름 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> 
								<input type="text" id="input-name" name="name" value="<%=userinfo.getName() %>" placeholder="이름을 입력하세요">
							</div>
	
							<!-- 성별 -->
							<div class="form-group">
								<span class="form-text">성별</span> 
								
								<%
								String gender = userinfo.getGender();
								if(gender.equals("male")){%>							
								<label for="rdo-male">남</label> 
								<input type="radio" id="rdo-male" name="gender" value="male" checked="checked"> 
								<label for="rdo-female">여</label> 
								<input type="radio" id="rdo-female" name="gender" value="female"> 
								<%}else{%>
								<label for="rdo-male">남</label> 
								<input type="radio" id="rdo-male" name="gender" value="male"> 
								<label for="rdo-female">여</label> 
								<input type="radio" id="rdo-female" name="gender" value="female" checked="checked"> 
								<%}%>
	
							</div>
	
							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원정보수정</button>						
							</div>
							
							<input type="hidden" name="no" value="<%=authUser.getNo() %>">
							<input type="hidden" name="action" value="modify">
						</form>
					
					
					</div>
					<!-- //modifyForm -->
				</div>
				<!-- //user -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->
	
	
</body>

</html>