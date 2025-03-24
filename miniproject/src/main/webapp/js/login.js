var tel_regp = /^01\d{8,9}/g;	//휴대폰번호 정규식 코드 

var login_btn = function(){
	if (f.memail.value == "") {
		alert("이메일을 입력하세요!")
		f.memail.focus();
	} else if (f.mpw.value == "") {
		alert("패스워드를 입력하세요");
		f.mpw.focus();
	} else {
		f.submit();
	}
}

var search_btn = function(){
	if (f.mname.value == "") {
		alert("이름을 입력하세요.");
		f.mname.focus();
	}else if (f.mtel.value == "") {
		alert("휴대폰번호를 입력하세요.");
		f.mtel.focus();
	} else if (!tel_regp.test(f.mtel.value)) {
		alert("휴대폰번호를 - 없이 10자이상 입력하세요.");
		f.mtel.focus();
	}else {
		f.submit();
	}
}