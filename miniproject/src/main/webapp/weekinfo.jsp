<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<section>
	<div class="weekdays">
		<p>
			금주분양 매물정보<br> <em>이번주 신규 매물정보!</em>
		</p>

		<cr:forEach var="aptdata" items="${aptList}">
			<div class="week_estates">
				<ul>
					<li><a href=""> <span>매매</span>
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