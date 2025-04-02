<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<form id="gvform" method="post" action="./md_board_view.do">
<input type="hidden" name="bidx" value="">
</form>
	
<section>
	<div class="recommend">
		<p>
			추천분양정보<br> <em>실시간 추천 분양정보를 한곳에!</em>
		</p>

		<div class="md_estates">
			<ul>
				<cr:forEach var="mcdata" items="${mcList}">
				${mcdata.file_url}<br>
				<cr:if test="${mcdata.b_url != ''}">
					<a href="${mcdata.b_url}">
				</cr:if>
				
				<cr:if test="${mcdata.b_url == ''}">
				<!-- 이게 맞나? 담에 수정하기 -->
					<a onclick="goboardview(${mcdata.bidx})">
				</cr:if>
				
						<li>
							<div>
							<cr:if test="${fn:length(mcdata.file_url) < 10}">
								<img src="./md_room/${mcdata.file_url}">
							</cr:if>
							<cr:if test="${fn:length(mcdata.file_url) >= 10}">
								<img src="./..${mcdata.file_url}">
							</cr:if>
							</div> <span>${mcdata.btitle} </span>
							<div>${mcdata.btext}</div>
					</li>
					</a>
				</cr:forEach>
			</ul>
		</div>

	</div>
</section>

<script src="./js/board.js?v=2"></script>