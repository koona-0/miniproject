<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 삭제폼 --%>
<form id="delf" method="get" action="./rsvtdelok.do">
<input type="hidden" name="midx" value="${sessionScope.dto.midx}">
<input type="hidden" name="vidx" value="">
</form>

<section class="sub">
	<p>모델 하우스 방문 예약 리스트</p>
	<div class="boards">모델 하우스 방문 예약한 정보를 한눈에 확인 하실 수 있습니다.</div>
	<div class="info_board">
		<ul>
			<li>NO</li>
			<li>아파트명</li>
			<li>방문일자</li>
			<li>방문시간</li>
			<li>인원수</li>
			<li>취소</li>
		</ul>
				
		<cr:if test="${rlist.size()==0}">
		<ul style="display: block;">
			<li class="nodata">등록된 방문 예약이 없습니다.</li>
		</ul>
		</cr:if>

		<cr:forEach var="rs" items="${rlist}" varStatus="idx">
		<ul class="data_view">
			<li>${rs.cnt - idx.index}</li>
			<li style="text-align: left;">${rs.aptnm}</li>
			<li>${rs.vdate}</li>
			<li>${rs.vtime}</li>
			<li>${rs.vcount}명</li>
			<li><input type="button" value="취소" class="cancel_btn" onclick="delete_btn(${rs.vidx})"></li>
		</ul>
		</cr:forEach>
	</div>
</section>

<script src="./js/reservation.js?v=2">
</script>