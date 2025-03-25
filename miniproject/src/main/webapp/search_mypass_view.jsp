<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="f" method="post" action="./updatepw_ok.do">
<input type="hidden" name="memail" value="${param.mail}">
	<section class="sub">
		<div>
			<ul>
				<li>회원가입 정보에 따른 패스워드 변경</li>
			</ul>
		</div>
		<div class="text1 repass">
			<div>
				<input type="password" name="mpw"
					placeholder="최소 10 ~ 16자 (영문,숫자,특수 문자 조합)로 입력해주세요." class="passin"
					autocomplete="none" maxlength="16">
			</div>
			<div>
				<input type="password" name="mpw2" placeholder="동일한 비밀번호를 입력하세요"
					class="passin" autocomplete="none" maxlength="16">
			</div>
		</div>
		<div>
			<input type="button" value="비밀번호 변경" class="search_submit"
				onclick="updatepw_btn()">
		</div>
	</section>
</form>

<script src="./js/login.js?v=3"></script>