<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="vform" method="post" action="./reservation.do">
<input type="hidden" name="aptnm" value="${oapt.aptnm}">
<input type="hidden" name="aidx" value="${oapt.aidx}">
<input type="hidden" name="midx" value="${sessionScope.dto.midx}">
</form>

<div class="weektails">
	<p>분양정보</p>
	<div id="weektails">
		<p>${oapt.aptnm}</p>
		<ul>
			<li>종류</li>
			<li>${oapt.apt_type} | ${oapt.rent_type}</li>
			<li>주소</li>
			<li>${oapt.addr}</li>
			<li>규모</li>
			<li>총 ${oapt.units}세대 | 총 ${oapt.buildings}개동</li>
			<li>시기</li>
			<li>${oapt.sale_date} | ${oapt.move_date}</li>
			<li>난방구조</li>
			<li>${oapt.heat}</li>
			<li>건설사</li>
			<li>${oapt.builder}</li>
			<li>사진정보</li>
			<li><img src="./room/${oapt.img}"></li>
		</ul>
	</div>
	
	<cr:if test="${isres == false}">
	<div>
		<button class="btn_css" onclick="vform.submit();">방문예약</button>
	</div>
	</cr:if>
	
	<cr:if test="${isres == true}">
	<div>
		<button class="btn_close" onclick="location.href='./reservation_check.do';">방문예약완료</button>
	</div>
	</cr:if>
</div>