var tel_regp = /^01\d{8,9}/g;	//휴대폰번호 정규식 코드 
var mail_regp = /^[a-z0-9.-_]+@[a-z0-9ㄱ-힇]+\.[a-z0-9ㄱ-힇]{2,}/i;	//이메일 정규식 코드

window.onload = function() {
	let me = sessionStorage.getItem("memail");
	if(me != null){
		f.emailck.value = "Y";	//이메일 중복체크 확인 
		//이메일 중복체크버튼 비활성화 
		//	document.getElementsByClassName("mail_btn")[0].style.display = "none";	//css깨짐 아래로 대체 	
		document.getElementsByClassName("mail_btn")[0].style.visibility = "hidden";
		document.getElementsByClassName("mail_btn")[0].disabled = true;

		f.mcode.value = "2";
		f.mjoin.value = "KAKAO";
		f.memail.value = me;
		f.memail.readOnly = true;
		f.mpw.value = me;
		f.mpw.readOnly = true;
		f.mpw2.value = me;
		f.mpw2.readOnly = true;
	}
}


//유효성, 패스워드 확인, submit
function join_btn() {
	if (f.memail.value == "" || f.emailck.value != "Y") {
		alert("이메일 입력 후 중복체크를 하세요.")
		f.memail.focus();
	} else if (f.mpw.value == "") {
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
	} else if (f.mpw.value != f.mpw2.value) {
		alert("패스워드가 다릅니다.");
		f.mpw2.focus();
	} else if (f.mname.value == "") {
		alert("이름을 입력하세요.");
		f.mname.focus();
	} else if (f.mtel.value == "") {
		alert("휴대폰번호를 입력하세요.");
		f.mtel.focus();
	} else if (!tel_regp.test(f.mtel.value)) {
		alert("휴대폰번호를 - 없이 10자이상 입력하세요.");
		f.mtel.focus();
	} else if (!f.over14_agree.checked || !f.terms_agree.checked || !f.privacy_agree.checked) {
		alert("필수동의를 체크해주세요.");
	} else {
		f.submit();
	}
}

//이메일 중복체크
function duplcheck() {
	if (f.memail.value == "") {
		alert("이메일을 입력해주세요.");
		f.memail.focus();
	} else if (!mail_regp.test(f.memail.value)) {
		alert("이메일을 형식에 맞게 입력해주세요.");
		f.memail.focus();
	} else {
		ajaxpost(f.memail.value);
	}
}

//Ajax 함수를 이용하여 중복 체크 
var ok = "";	//Back에게 통신을 보내는 역할을 하는 변수 
function ajaxpost(data) {
	function wck() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		}
	}

	function getdata() {
		if (ok.readyState == XMLHttpRequest.DONE && ok.status == 200) {
			if (this.response == "ok") {
				alert("사용가능한 이메일입니다.");
				f.memail.readOnly = true; //읽기전용 변경
				document.getElementById("emailck").value = "Y";
			} else if (this.response == "no") {
				alert("해당 이메일은 이미 사용 중입니다.");
				f.memail.value = "";
			}
		}
	}

	ok = wck();
	ok.onreadystatechange = getdata;
	ok.open("GET", "./emailck.do?memail=" + data, true);
	ok.send();
}



//약관 관련

//약관 출력
window.onload = function() {
	var http = new XMLHttpRequest() // Ajax 객체 생성

	// 첫 번째 약관 파일 로드
	http.open("GET", "./agree/agree1.txt", false)
	http.send()
	document.getElementById("ag").innerHTML = http.response

	// 두 번째 약관 파일 로드
	http.open("GET", "./agree/agree2.txt", false)
	http.send()
	document.getElementById("ag2").innerHTML = http.response
}

//전체선택 누르면 모두 상태 변경
var check_all = function(z) {
	f.over14_agree.checked = z;
	f.terms_agree.checked = z;
	f.privacy_agree.checked = z;
	f.marketing_agree.checked = z;
}

//하나라도 체크해제된 경우 전체선택 해제 또는 모두 체크된 경우 전체선택 체크
var check_one = function() {
	//모두 체크된 경우 
	if (f.over14_agree.checked && f.terms_agree.checked && f.privacy_agree.checked && f.marketing_agree.checked) {
		document.getElementById("all_cb").checked = true;
	} else {
		document.getElementById("all_cb").checked = false;
	}
} 