var tel_regp = /^01\d{8,9}/g;	//휴대폰번호 정규식 코드 
var mail_regp = /^[a-z0-9.-_]+@[a-z0-9ㄱ-힇]+\.[a-z0-9ㄱ-힇]{2,}/i;	//이메일 정규식 코드

//로그인 버튼
var login_btn = function() {
	if (f.memail.value == "") {
		alert("이메일을 입력하세요!")
		f.memail.focus();
	} else if (!mail_regp.test(f.memail.value)) {
		alert("이메일을 형식에 맞게 입력해주세요.");
		f.memail.focus();
	} else if (f.mpw.value == "") {
		alert("패스워드를 입력하세요");
		f.mpw.focus();
	} else {
		f.submit();
	}
}

//이메일 찾기 버튼
var email_search_btn = function() {
	if (f.mname.value == "") {
		alert("이름을 입력하세요.");
		f.mname.focus();
	} else if (f.mtel.value == "") {
		alert("휴대폰번호를 입력하세요.");
		f.mtel.focus();
	} else if (!tel_regp.test(f.mtel.value)) {
		alert("휴대폰번호를 - 없이 10자이상 입력하세요.");
		f.mtel.focus();
	} else {
		f.submit();
	}
}

//비밀번호 찾기 버튼
var pw_search_btn = function() {
	if (f.memail.value == "") {
		alert("이메일을 입력하세요!")
		f.memail.focus();
	} else if (!mail_regp.test(f.memail.value)) {
		alert("이메일을 형식에 맞게 입력해주세요.");
		f.memail.focus();
	} else if (f.mtel.value == "") {
		alert("휴대폰번호를 입력하세요.");
		f.mtel.focus();
	} else if (!tel_regp.test(f.mtel.value)) {
		alert("휴대폰번호를 - 없이 10자이상 입력하세요.");
		f.mtel.focus();
	} else {
		f.submit();
	}
}

//비밀번호 변경 버튼 
var updatepw_btn = function() {
	if (f.mpw.value == "") {
		alert("비밀번호를 입력하세요.");
		f.mpw.focus();
	} else if (f.mpw.value.length < 10) {
		alert("비밀번호를 10자 이상 입력하세요.");
		f.mpw.focus();
	} else if (f.mpw2.value == "" || f.mpw.length < 10) {
		alert("비밀번호 확인을 입력하세요.");
		f.mpw2.focus();
	} else if (f.mpw.value.length < 10) {
		alert("비밀번호 확인을 10자 이상 입력하세요.");
		f.mpw2.focus();
	} else if(f.mpw.value != f.mpw2.value){
		alert("패스워드가 다릅니다.");
		f.mpw2.focus();
	} else {
		f.submit();
	}
}


