<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<section>
	<div class="recommend">
		<p>
			추천분양정보<br> <em>실시간 추천 분양정보를 한곳에!</em>
		</p>

		<cr:forEach var="mcdata" items="${mcList}">
			<div class="md_estates">
				<ul>
					<li>
						<div onclick="gochoice(${mcdata.choice_url})">
							<img src="./md_room/${mcdata.choice_image}">
						</div> <span>${mcdata.choice_title} </span>
						<div>${mcdata.choice_description}</div>
					</li>
				</ul>
			</div>
		</cr:forEach>

	</div>
</section>

<script>
function gochoice(url){
	location.href(url);
}
</script>