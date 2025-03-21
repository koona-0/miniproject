<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="copyright">
	<ol>
		<li><img src="./logo/e_click_logo.png" class="logos"></li>
		<li>
			<ul>
				<li>회사소개</li>
				<li>이용약관</li>
				<li>위치기반서비스 약관</li>
				<li>제휴문의</li>
				<li>개인정보처리방침</li>
				<li>신고센터</li>
			</ul>
		</li>
		<li>
			<ul>
				<li>저작권규약</li>
				<li>책임한계 및 법적고지</li>
				<li>이메일 무단수집거부</li>
				<li>고객센터문의</li>
				<li></li>
				<li></li>
			</ul>
		</li>
		<li>
			<ul>
				<cr:forEach var="crdata" items="${crList }">
					<li>${crdata.corp_name}| 대표 : ${crdata.ceo}</li>
					<li>${crdata.corp_addr}</li>
					<li>사업자번호 : ${crdata.biz_no} | 통신판매업 : ${crdata.sales_no}</li>
					<li>정보보호책임자 : ${crdata.sec_mgr}</li>
					<li>영업문의 : ${crdata.tel}</li>
					<li>팩스번호 : ${crdata.fax}</li>
					<li>Copyright ⓒ 2025 Develop by Gunayeong AllRight Reserved.</li>
				</cr:forEach>

			</ul>
		</li>
	</ol>
</div>