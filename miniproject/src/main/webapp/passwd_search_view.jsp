<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="f" method="post" action="./pwsearch_ok.do">
<section class="sub">
	<div>
		<ul>
			<li onclick="location.href='./email_search.jsp'"
				style="cursor: pointer;">이메일 찾기</li>
				<li onclick="location.href='./passwd_search.jsp'"
				style="cursor: pointer;">비밀번호 찾기</li>
		</ul>
	</div>
	<div class="text1">
		<div>
			<input type="text" name="memail" placeholder="가입하신 이메일을 입력하세요">
		</div>
		<div>
			<input type="text" name="mtel" placeholder="휴대폰 번호는 - 빼고 숫자만 입력하세요">
		</div>
	</div>
	<div>
		<input type="button" value="비밀번호 찾기" onclick="pw_search_btn()">
	</div>
</section>
</form>

<script src="./js/login.js?v=3"></script>