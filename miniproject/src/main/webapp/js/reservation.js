var visit_btn = function(){
	if (f.vdate.value == "") {
		alert("방문일시를 선택해주세요")
		f.vdate.focus();
	} else if (f.vtime.value == "") {
		alert("방문시간을 선택해주세요");
		f.vtime.focus();
	} else if (f.vcount.value == "") {
		alert("방문인원을 선택해주세요");
		f.vcount.focus();
	} else {
		f.submit();
	}
}