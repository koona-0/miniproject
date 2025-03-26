<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<!--최상단-->
<header>
	<div class="top_banner"></div>
</header>
<!--최상단끝-->
<!--메뉴-->
<nav>
	<div class="menus">
		<ul class="menus_ul">
			<li><img src="./logo/e_click_logo.png"
				onclick="location.href='./index.do'"></li>
			<li>일반매물</li>
			<li>추천매물</li>
			<li>중계의뢰</li>
			<li onclick="location.href='./counsel.jsp'">상담신청</li>
			<li>업체의뢰</li>
			<li>의뢰현황</li>
			<li class="logins"><cr:if
					test="${not empty sessionScope.dto.mname}">
			${sessionScope.dto.mname}님 환영합니다 <a href="./logout.jsp">[로그아웃]</a>
				</cr:if> <cr:if test="${empty sessionScope.dto.mname}">
					<span title="로그인" onclick="location.href='./login.jsp'"> <img
						src="./ico/login.svg">
					</span>
					<span title="회원가입" onclick="location.href='./member_join.jsp'"><img
						src="./ico/membership.svg"></span>
				</cr:if></li>
		</ul>
	</div>
</nav>
<!--메뉴끝-->