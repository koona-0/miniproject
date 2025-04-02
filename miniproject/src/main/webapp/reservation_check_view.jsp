<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<section class="sub">
	<p>모델 하우스 사전 방문예약 확인</p>
	<div>
		<span class="list_title"> <span style="color: blue;">분양정보</span>
			<span style="color: red;">${rsvt.aptnm}</span>
		</span>
		<ul class="sub_ul">
			<li>방문일시</li>
			<li><input type="text" class="sel_input" value="${rsvt.vdate}" disabled></li>
			<li>방문시간</li>
			<li><input class="sel_input2" value="${rsvt.vtime}" disabled>* 해당 시간에 맞춰서 방문해
				주셔야 합니다.</li>
			<li>방문자명</li>
			<li><input type="text" value="${sessionScope.dto.mname}" class="sel_input" disabled></li>
			<li>방문인원</li>
			<li>${rsvt.vcount}명 ※ 방문인원은 최대 2명까지 입니다.</li>
			<li>연락처</li>
			<li><input type="text" value="${sessionScope.dto.mtel}" class="sel_input" disabled></li>
		</ul>
	</div>
	<div>
		<input type="button" value="메인페이지로 이동" onclick="location.href='./index.jsp'">
	</div>
</section>