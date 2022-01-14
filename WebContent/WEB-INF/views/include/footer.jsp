<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%@ page import="com.javaex.vo.UserVo" %>
    
<%
	UserVo authUser = (UserVo)session.getAttribute("authUser");

%>    

		<div id="footer">
			Copyright ⓒ 2022 이영림. All right reserved
		</div>
		<!-- //footer -->    