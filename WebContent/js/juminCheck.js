/**
 * 주민번호 정상 유무 체크
 */

function juminCheck(jumin1, jumin2) {
		var juminNum = jumin1 + jumin2
		
		// 주민번호 체크 공식 적용 전에 먼저 정규표현식 (패턴)에 맞는지 체크한다.
		var jumin_pattern = new RegExp('^[0-9]{6}[1234][0-9]{6}')
		
		if(!jumin_pattern.test(juminNum)){
			alert('입력받은 주민번호가 정규표현식 패턴에 적합하지 않습니다!!')
			return
		} else {
			alert('입력받은 주민번호가 정규표현식 패턴에 적합합니다.')
			
			var sum = 0
			var temp = 0
			var result = 0
			var weight = new Array(2,3,4,5,6,7,8,9,2,3,4,5)
			
			// 주민번호 체크 공식 적용
			for(i=0; i<12; i++) {
				sum = sum + (juminNum.charAt(i))*weight[i]
			}
			
			temp = 11-(sum%11)
			result = temp%10
			
			if(result != juminNum.charAt(12)) {
				alert('입력한 주민번호는 틀린 번호입니다!!')
				document.juminForm.jumin_num1.focus()
			} else {
				alert('입력한 주민번호는 정상인 주민번호 입니다.')
			}
		}
	}