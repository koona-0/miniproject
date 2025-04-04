<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%Date date = new Date();%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 찾기</title>
<link rel="stylesheet" type="text/css" href="./css/index.css?v=<%=date%>">
<link rel="stylesheet" type="text/css" href="./css/email_search.css?v=<%=date%>">
</head>
<body>
	<!-- 상단 -->
	<cr:import url="./top.jsp"></cr:import>

	<main>
		<cr:import url="./email_search_view.jsp"></cr:import>
	</main>

	<!-- 카피라이터 -->
	<footer>
		<cr:import url="./copyright.do"></cr:import>
	</footer>
</body>
</html>