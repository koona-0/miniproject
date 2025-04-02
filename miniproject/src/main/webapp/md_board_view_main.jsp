<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section class="sub">
	<p>추천분양 정보 게시판</p>
	<div class="boards">이번주 신규 매물정보를 한눈에 확인하실 수 있습니다.</div>
	<div class="info_board">
	
		<ul>
			<li>제&nbsp;&nbsp;&nbsp;목</li>
			<li>${bmap.btitle.replaceAll("<br>", " ")}</li>
			<li>글쓴이</li>
			<li>${bmap.bwriter}</li>
			<li>등록일</li>
			<li>${fn:substring(bmap.reg_date,0,10)}</li>
			<li>조회수</li>
			<li>${bmap.view_count}</li>
			<li class="litext">내&nbsp;&nbsp;&nbsp;용</li>
			<li class="litext">
				<div class="textarea">${bmap.btext}</div>
			</li>
		</ul>
		
	</div>
	<div class="board_btn">
		<input type="button" value="목&nbsp;&nbsp;&nbsp;록" class="btns" onclick="location.href='./md_board.do'">
	</div>
</section>