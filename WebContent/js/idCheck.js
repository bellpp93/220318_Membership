/**
 * 아이디 체크 자바스크립트 함수 구현
 */

function idCheck(id) {
	if(id == "") {
		alert('아이디를 입력하세요!!');
	} else {
		url = "IdCheck.jsp?mem_id=" + id;
		window.open(url, "get","width=350, height=150");
	}
}