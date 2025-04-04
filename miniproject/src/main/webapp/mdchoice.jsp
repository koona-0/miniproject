<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Date"%>
<%Date date = new Date();%>
<%-- 해당 게시물로 이동 --%>
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
					<a href="#" onclick="goboardview(${mcdata.bidx})">
						<li>
							<div>
								<img src="/miniproject/${mcdata.file_url}">
							</div> <span>${mcdata.btitle} </span>
							<div>${mcdata.btext}</div>
					</li>
					</a>
				</cr:forEach>
			</ul>
		</div>

	</div>
</section>

<script src="./js/board.js?v=<%=date%>"></script>