var f = document.getElementById("f"); // 명시적으로 폼 참조

var counsel_btn = function() {
	if (checkBoxCount("check1") < 1) { // 임대형태
		alert("임대형태를 최소 1개 체크해주세요.");
	} else if (checkBoxCount("check2") < 1) { // 주거형태
		alert("주거형태를 최소 1개 체크해주세요.");
	} else if (f.cdate.value == "") {
		alert("날짜를 선택하세요");
	}
	//오늘날짜이후 체크는 컨트롤러 모델에서
	 else if (f.ctext.value == "") {
		alert("상담내용을 입력해주세요");
	}else if (f.ctext.length > 10) {
		alert("상담내용을 10자이상 입력해주세요");
	} else { 
		f.submit(); // 폼 제출
	}
};

var checkBoxCount = function(name) {
	var checkboxes = document.getElementsByName(name); // name으로 체크박스 그룹 가져오기
	var count = 0;
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			count++;
		}
	}
	console.log(count);
	return count;
};
