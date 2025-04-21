<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<%Date date = new Date();%>
<form id="f" method="post" action="./loginok.do">
	<input type="hidden" name="mcode" value="1"> 
	<input type="hidden" name="kakao_id" value=""> 
	<input type="hidden" name="kakao_nicknm" value="">
	
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
			<input type="button" value="카카오로그인" class="kakao_btn" onclick="kakao_login()">
		</div>
		<div>
			<span onclick="location.href='./member_join.jsp'" style="cursor: pointer;">회원가입</span>
			<span> &nbsp&nbsp|&nbsp&nbsp </span>
			<span onclick="location.href='./email_search.jsp'" style="cursor: pointer;">이메일 찾기</span> 
			<span> &nbsp&nbsp|&nbsp&nbsp </span>
			<span onclick="location.href='./passwd_search.jsp'" style="cursor: pointer;">비밀번호 찾기</span>
		</div>
	</aside>
</form>
<script src="./js/login.js?v=<%=date%>"></script>

<script src="https://t1.kakaocdn.net/kakao_js_sdk/v1/kakao.js"></script>
<script>
	Kakao.init('59127331115f411c0e9aa843d98b17c5');
	function kakao_login() {
		// Kakao.Auth.login : 카카오 회원가입 및 로그인 페이지를 출력하는 함수 
		Kakao.Auth.login({
			//성공시 출력되는 형태 
			success : function(response) {	//response : 결과화면
				Kakao.API.request({			//사용자 가입정보를 요청 
					url : '/v2/user/me',	// 사용자 정보 가져오기
					success : function(response) {	//API서버에서 가입정보를 가져옴 
						console.log(response);
						let id = response["id"];		//고유값 
						let nickname = response["kakao_account"]["profile"]["nickname"];		//카카오 닉네임
						f.mcode.value = "2";
						f.kakao_id.value = id;
						f.submit();
					},
					fail : function(error) {
						console.log(error);
						console.log("카카오 API 접속 오류");
					}
				});
			},
			//API 키가 맞지 않을 경우 출력되는 함수 
			fail : function(error) {
				console.log(error);
				console.log("카카오 key 접속 오류 및 환경설정 오류");
			}
		});
	}
</script>