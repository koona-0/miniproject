<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%Date date = new Date();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/index.css?v=<%=date%>">
<link rel="stylesheet" type="text/css" href="./css/member_join.css?v=<%=date%>">
<style>
.box {
	width: 800px;
	height: 300px;
	border: 1px solid black;
	overflow: auto;
}
</style>
<title>회원가입</title>
</head>
<body>

	<!-- 상단 -->
	<cr:import url="./top.jsp"></cr:import>
	<!-- view -->
	<main>
		<cr:import url="./join_view.jsp"></cr:import>
	</main>
	<!-- 카피라이터 -->
	<footer>
		<cr:import url="./copyright.do"></cr:import>
	</footer>

</body>
<script src="./js/join.js?v=<%=date%>"></script>
</html>