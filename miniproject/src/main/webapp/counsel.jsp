<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상담신청</title>
<link rel="stylesheet" type="text/css" href="./css/index.css?v=1">
<link rel="stylesheet" type="text/css" href="./css/counsel.css?v=3">
</head>
<body>

	<!-- 상단 -->
	<cr:import url="./top.jsp"></cr:import>


	<!-- view -->
	<main>
		<cr:import url="./counsel_view.jsp"></cr:import>
	</main>


	<!-- 카피라이터 -->
	<footer>
		<cr:import url="./copyright.do"></cr:import>
	</footer>
</body>
</html>