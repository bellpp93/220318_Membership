/**
 * 이메일 입력 처리
 */

function change_email() {
		var email_add = document.getElementById("email_add")
		var email_select = document.getElementById("email_select")
		
		// 사용자가 선택한 옵션의 인덱스 값 구하기
		var idx = email_select.options.selectedIndex
		var val = email_select.options[idx].value
		
		email_add.value = val
	}