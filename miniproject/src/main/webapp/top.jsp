<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<!--최상단-->
<header>
	<div class="top_banner"></div>
</header>
<!--최상단끝-->
<!--메뉴-->
<nav>
	<div class="menus">
		<ul class="menus_ul">
			<li><img src="./logo/e_click_logo.png"
				onclick="location.href='./index.do'"></li>
			<li>일반매물</li>
			<li onclick="location.href='./md_board.do'">추천매물</li>
			<li>중계의뢰</li>
			<li onclick="is_loginc(${sessionScope.dto.midx})">상담신청</li>
			<li>업체의뢰</li>
			<li>의뢰현황</li>
			
			 <li class="logins" onmouseleave="myinfo_menu(2)">
        <span title="회원정보" onclick="myinfo_menu(1)">
        <img src="./ico/login.svg">
        <ul class="login_info" id="login_info">
          <cr:if test="${empty sessionScope.dto.mname}">
            <li style="block">
            <span onclick="location.href='./login.jsp'">로그인</span> / <span onclick="location.href='./member_join.jsp'">회원가입</span> 
            </li>
          </cr:if>
            <cr:if test="${not empty sessionScope.dto.mname}">
              <li>${sessionScope.dto.mname}님 <a href="./logout.jsp">[로그아웃]</a></li>
            </cr:if>
        </ul>
        </span>
        <span title="모델 하우스 사전예약 리스트" onclick="reserve_page(${sessionScope.dto.midx})"><img src="./ico/reserve_list.svg"></span>
      </li>
    </ul>
  </div>
 </nav>
 
 <form id="rf" method="post" action="reservation_list.do">
 <input type="hidden" name="midx" value="">
 </form>
 
 
 <script>
 //상담신청 클릭시 로그인 확인
 function is_loginc(midx){
	if(!midx) {	//로그인 정보가 없을 때 
		alert('로그인이 필요한 서비스입니다.');
		location.href='./login.jsp';
	}else {		//로그인 정보가 있을 때 
		f.midx.value=midx;
		location.href='./counsel.jsp'
		
	}
}
 
 
    //해당 함수는 모델 하우스 사전 방문예약 확인 리스트 페이지로 이동 되도록 합니다.
    function reserve_page(midx) {
    	if(!midx){	//null과 undefined 모두 잡음 
    		alert('로그인 후 이용가능합니다.');
    	}else{
	    	rf.midx.value=midx;
	    	rf.submit();
    	}
    	
    }
    function myinfo_menu(part){
        var log_menu = document.getElementById("login_info");
        if(part==1){
            if(log_menu.style.display == "none"){
                log_menu.style.display = "block"; 
            }
            else{
                log_menu.style.display = "none"; 
            }
        }
        else{
            log_menu.style.display = "none"; 
        }
    }
 </script>