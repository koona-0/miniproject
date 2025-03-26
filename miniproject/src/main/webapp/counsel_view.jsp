<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="f" method="post" action="./counselok.do">
<section class="sub">
<p>상담신청</p>
<div>
<span class="list_title">◎ <span style="color: blue;">상담자 정보</span></span>
<ul class="sub_ul">
<li>상담자명</li>    
<li><input type="text" class="sel_input" name="mname" value="${sessionScope.dto.mname}" readonly></li> 
<li>이메일</li>    
<li><input type="text" class="sel_input" name="memail" value="${sessionScope.dto.memail}" readonly></li> 
<li>연락처</li>    
<li><input type="text" class="sel_input" name="mtel" value="${sessionScope.dto.mtel}" readonly></li> 
<li>임대형태</li>    
<li>
<label><input type="checkbox" name="check1" class="sel_check" value="Y"> 공공임대</label>
<label><input type="checkbox" name="check1" class="sel_check" value="Y"> 민간임대</label>
<label><input type="checkbox" name="check1" class="sel_check" value="Y"> 민간분양</label>
<label><input type="checkbox" name="check1" class="sel_check" value="Y"> 기타</label>
</li>   
<li>주거형태</li>    
<li>
<label><input type="checkbox" name="check2" class="sel_check" value="Y"> 아파트</label>
<label><input type="checkbox" name="check2" class="sel_check" value="Y"> 빌라,단독주택</label>
<label><input type="checkbox" name="check2" class="sel_check" value="Y"> 사무실.상가.빌딩</label>
</li>       
<li>상담일시</li>    
<li>
<input type="date" name="cdate" class="sel_input2"> * 해당 일시에 맞춰서 담당자가 연락 드립니다.   
</li>    
<li>상담내용</li>    
<li style="height: 150px !important; line-height: 150px;">
<textarea class="sel_input3" name="ctext" placeholder="구체적인 상담내용을 입력해 주세요"></textarea>
</li>
</ul>
</div>
<div><input type="button" value="상담신청" onclick="counsel_btn()"></div>
</section>
</form>

<script src="./js/counsel.js?v=6"></script>