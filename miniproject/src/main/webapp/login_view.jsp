<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%Date date = new Date();%>
<form id="f" method="post" action="./loginok.do">
	<aside class="login_pg">
		<p>이메일로 시작하기</p>
		<div>
			<input type="text" name="memail" placeholder="이메일 주소">
		</div>
		<div>
			<input type="password" name="mpw" placeholder="비밀번호">
		</div>
		<div>
			<input type="button" value="로그인" onclick="login_btn()">
		</div>
		<div>
			<input type="button" value="카카오로그인" class="kakao_btn">
		</div>
		<div>
			<span onclick="location.href='./member_join.jsp'"
				style="cursor: pointer;">회원가입</span> <span
				onclick="location.href='./email_search.jsp'"
				style="cursor: pointer;">이메일 찾기</span> <span
				onclick="location.href='./passwd_search.jsp'"
				style="cursor: pointer;">비밀번호 찾기</span>
		</div>
	</aside>
</form>
<script src="./js/login.js?v=<%=date%>"></script>