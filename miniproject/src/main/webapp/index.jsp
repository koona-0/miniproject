<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>온라인 부동산 중개</title>
<link rel="stylesheet" type="text/css" href="./css/index.css?v=8">
</head>
<body>
	<!-- 상단 -->
	<cr:import url="./top.jsp"></cr:import>

	<main>
		<!-- banner -->
		<cr:import url="./banner.jsp"></cr:import>

		<!-- quickmenu -->
		<cr:import url="./quickmenu.jsp"></cr:import>

		<!-- 금주 분양 매물 정보 -->
		<cr:import url="./weekinfo.do"></cr:import>

		<!-- 추천 분양 정보 -->
		<cr:import url="./mdchoice.do"></cr:import>
	</main>

	<!-- 카피라이터 -->
	<footer>
		<cr:import url="./copyright.do"></cr:import>
	</footer>

</body>
</html>