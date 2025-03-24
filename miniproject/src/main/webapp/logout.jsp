<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
HttpSession hs = request.getSession();
hs.invalidate();	//모든 session 값을 삭제 
out.print("<script>"
		+"alert('로그아웃 되셨습니다.');"
		+"location.href='./login.jsp';"
		+"</script>");	
%>