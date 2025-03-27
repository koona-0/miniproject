<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="weektails">
	<p>분양정보</p>
	<div id="weektails">
		<p>${sessionScope.oapt.aptnm}</p>
		<ul>
			<li>종류</li>
			<li>${sessionScope.oapt.apt_type} | ${sessionScope.oapt.rent_type}</li>
			<li>주소</li>
			<li>${sessionScope.oapt.addr}</li>
			<li>규모</li>
			<li>총 ${sessionScope.oapt.units}세대 | 총 ${sessionScope.oapt.buildings}개동</li>
			<li>시기</li>
			<li>${sessionScope.oapt.sale_date} | ${sessionScope.oapt.move_date}</li>
			<li>난방구조</li>
			<li>${sessionScope.oapt.heat}</li>
			<li>건설사</li>
			<li>${sessionScope.oapt.builder}</li>
			<li>사진정보</li>
			<li><img src="./room/${sessionScope.oapt.img}"></li>
		</ul>
	</div>
	<div>
		<button class="btn_css">방문예약</button>
	</div>
	<div>
		<button class="btn_close">방문예약완료</button>
	</div>
</div>