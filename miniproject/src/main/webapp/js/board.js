var spage = function(){
	if(sform.search.value==""){
		alert("배너명을 입력하세요");
		return false;
	}else{
		return;	
	}
}

function pg(no){
	location.href='./md_board.do?pageno='+no;
}

//체크박스 전체선택 함수 

function check_all(ck){	//ck : true, false
	var ea = document.getElementsByName("ckbox");
	
	var w= 0;
	while(w < ea.length){
		ea[w].checked = ck;
		w++;
	}
}

//하나라도 체크 해제시 전체선택 체크 해제 
function checkdata(){
	
}

function goboardview(idx){
	gvform.bidx.value = idx;
	gvform.submit();
}