<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%Date date = new Date();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천분양 정보 게시판</title>
<link rel="stylesheet" type="text/css" href="./css/index.css?v=<%=date%>">
<link rel="stylesheet" type="text/css" href="./css/md_board.css?v=<%=date%>">
<style>
.box {
	width: 800px;
	height: 300px;
	border: 1px solid black;
	overflow: auto;
}
</style>
</head>
<body>
	<!-- 상단 -->
	<cr:import url="./top.jsp"></cr:import>

	<!-- view -->
	<main>
		<cr:import url="./md_board_main.jsp">
		<cr:param name="total" value="${total}"/>
		<cr:param name="search" value="${search}"/>
		<cr:param name="all" value="${all}"/>
	<%--
	 --%>
		
		</cr:import>
	</main>
	<!-- 카피라이터 -->
	<footer>
		<cr:import url="./copyright.do"></cr:import>
	</footer>
</body>
</html>