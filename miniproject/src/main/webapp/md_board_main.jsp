<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<section class="sub">
	<p>추천분양 정보 게시판</p>
	<div class="boards">이번주 신규 매물정보를 한눈에 확인하실 수 있습니다.</div>
	<div class="info_board">
		<ul>
			<li>NO</li>
			<li>제목</li>
			<li>글쓴이</li>
			<li>조회수</li>
			<li>등록일</li>
		</ul>
		all : ${all } p : ${param.all }<br><br><br><br>
		total : ${total } p : ${param.total }<br><br><br><br>
		
		<%-- <cr:if test ="${param.all.size()==0}"> --%>
		<cr:if test ="${fn:length(all)==0}">
		<ul style="display: none;">
			<li class="nodata">등록된 게시물이 없습니다.</li>
		</ul>
		</cr:if>
		
		<cr:set var="ino" value="${param.total - userpage}"/>
		
		<%--
		 --%>
		<cr:forEach var="bn" items="${param.all}" varStatus="idx">
		<ul class="data_view">
			<li>1${ino - bidx.index}</li>
			<li style="text-align: left;">게시물 제목출력${bn.btitle}</li>
			<li>관리자${bn.bwriter}</li>
			<li>30${bn.view_count}</li>
			<li>2025-03-31${bn.reg_date}</li>
		</ul>
		</cr:forEach>
	</div>
	
	<cr:set var="pageidx" value="${param.total / 10 + (1-((param.total / 10) % 1)) % 1}"/>
	
	<div class="info_pageing">
		<ol>
		<cr:forEach var="no" begin="1" end="${pageidx}" step="1">
			<li onclick="pg('${no}')">1${no}</li>
			</cr:forEach>
		</ol>
	</div>
	
	<form id="sform" method="get" action="./bannerlist 수정하기">
	<div class="info_search">
		<input type="text" class="search_text" placeholder="검색어를 입력하세요">
		<input type="button" value="검색" class="search_btn" onclick="spage()">
	</div>
	</form>
	
</section>

<script src="./js/board.js?v=1"></script>