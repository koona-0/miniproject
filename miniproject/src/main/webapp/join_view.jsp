<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>


<form id="f" method="post" action="./joinok.do">
<input type="hidden" id="emailck" value="N">	<!-- 이메일 체크 여부 Y, N -->	
<input type="hidden" name="mcode" value="1">	<!-- 1 : 자회사회원가입 / 2 : 카카오 -->
<input type="hidden" name="mjoin" value="WEB">	<!-- WEB, KAKAO -->
	<p>이메일로 회원가입</p>
	<div>
		<a>이메일</a> 
		<input type="text" name="memail" placeholder=" 이메일 주소를 입력해주세요." autocomplete="none" style="width: 400px; float: left;"> 
		<input type="button" value="중복체크" class="mail_btn" onclick="duplcheck()">
	</div>
	<div>
		<a>비밀번호</a> 
		<input type="password" name="mpw" placeholder=" 10~16자(영문,숫자,특수 문자 조합)로 입력해주세요." autocomplete="none" maxlength="16">
	</div>
	<div>
		<a>비밀번호 확인</a> 
		<input type="password" name="mpw2" placeholder=" 비밀번호를 다시 한 번 입력해주세요." autocomplete="none" maxlength="16">
	</div>
	<div>
		<a>이름</a> 
		<input type="text" placeholder=" 이름을 입력해주세요." name="mname" autocomplete="none">
	</div>
	<div>
		<a>휴대폰번호</a> 
		<input type="text" placeholder=" -없이 숫자만 입력해주세요." name="mtel" autocomplete="none" maxlength="11">
	</div>
	
	<!-- 약관 동의 -->
	<div>
		<input type="checkbox" id="all_cb" onclick="check_all(this.checked)"> <span>전체 동의</span>
	</div>

	<div class="line"></div>

	<div class="bottom">
		<div class="box1">
			<input type="checkbox" name="over14_agree" value="Y" onclick="check_one()"> <a class="a1">(필수)
				<span>만 14세 이상입니다.</span>
			</a>
		</div>
		<div class="box2">
			<input type="checkbox" name="terms_agree" value="Y" onclick="check_one()"> <a class="a2">(필수)
				<span><u>이용약관</u>에 동의</span>
			</a>
		</div>
		<div class="box3">
			<input type="checkbox" name="privacy_agree" value="Y" onclick="check_one()"> <a class="a3">(필수)
				<span><u>개인정보 수집 및 이용</u>에 동의</span>
			</a>
		</div>
		<section class="text1" id="ag"></section>
		<div class="box4">
			<input type="checkbox" name="marketing_agree" value="Y" onclick="check_one()"> <span>(선택)
				마케팅 수신에 동의</span>
		</div>
		<section class="text2" id="ag2"></section>
	</div>
	<div>
		<input type="button" value="가입 하기" onclick="join_btn()">
	</div>
</form>

<script src="./js/join.js?v=6"></script>
<script>

</script>
