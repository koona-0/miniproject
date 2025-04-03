<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<section>
	<div class="weekdays">
		<p>
			금주분양 매물정보<br> <em>이번주 신규 매물정보!</em>
		</p>
		<form id="f" method="post" action="./week_tails.do">
		<input type="hidden" name="aidx" value="">
		<input type="hidden" name="midx" value="">
		<input type="hidden" name="mname" value="${sessionScope.dto.mname}">
		</form>
		<cr:forEach var="aptdata" items="${aptList}">
			<div class="week_estates">
				<ul>
					<li><a onclick="selapt(${aptdata.aidx},${sessionScope.dto.midx})">
						<span>${aptdata.state}</span>
							<div>${aptdata.aptnm}</div>
							<aside>${aptdata.addr}</aside> <span>${aptdata.apt_type} |
								${aptdata.rent_type}</span> <label>${aptdata.sale_date} |
								${aptdata.move_date}</label>
							<div>
								<img src="./room/${aptdata.img}">
							</div>
					</a></li>

				</ul>
			</div>
		</cr:forEach>

	</div>
</section>

<script>
function selapt(aidx,midx){
	if(!midx) {	//로그인 정보가 없을 때 
		alert('로그인이 필요한 서비스입니다.');
		location.href='./login.jsp';
	}else {		//로그인 정보가 있을 때 
		f.midx.value=midx;
		f.aidx.value=aidx;
		f.submit();
	}
}
</script>