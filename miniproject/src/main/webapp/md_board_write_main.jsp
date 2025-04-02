<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="f" method="post" action="./writeok.do" enctype="multipart/form-data">

<section class="sub">
	<p>추천분양 정보 게시판</p>
	<div class="boards">이번주 신규 매물정보를 한눈에 확인하실 수 있습니다.</div>
	<div class="info_board">
		<ul>
			<li>제&nbsp;&nbsp;&nbsp;목</li>
			<li><input type="text" name="btitle" class="board_in2"></li>
			<li>글쓴이</li>
			<li><input type="text" name="bwriter" class="board_in1" value="관리자" readonly></li>
			<li>썸네일 이미지</li>
			<li><input type="file" name="bfile"></li>
			<li class="litext">내&nbsp;&nbsp;&nbsp;용</li>
			<li class="litext"><textarea name="btext" id="board_text">
                    
                </textarea></li>
		</ul>
	</div>
	<div class="board_btn">
		<input type="button" value="등&nbsp;&nbsp;&nbsp;록" class="btns" onclick="write_btn()">
	</div>
</section>

</form>

<script>
	window.onload = function() {
		CKEDITOR.replace('board_text', {
			height : 360
		});
	};
	
	function write_btn(){
		if(f.btitle.value==""){
			alert("제목을 입력해주세요");
			f.btitle.focus();
		} else if(f.bwriter.value==""){
			alert("글쓴이를 입력해주세요");
			f.bwriter.focus();
		}else if(f.bfile.value==""){
			alert("파일을 첨부해주세요");
			f.bfile.focus();
		}else if(f.btext.value==""){
			alert("내용을 입력해주세요");
			f.btext.focus();
		}else{
			f.submit();
		}
	}
	
</script>