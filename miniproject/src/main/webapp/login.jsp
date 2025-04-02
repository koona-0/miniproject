<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="./css/index.css?v=2">
<link rel="stylesheet" type="text/css" href="./css/login.css?v=2">
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

	<main>
		<cr:import url="./login_view.jsp"></cr:import>
	</main>

	<!-- 카피라이터 -->
	<footer>
		<cr:import url="./copyright.do"></cr:import>
	</footer>
</body>
</html>