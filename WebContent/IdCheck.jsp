<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 추가 -->
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="mem" scope="page" class="member.DBBean"/>
<%
	String mem_id = request.getParameter("mem_id");
	
	// idCheck 값이 가질 수 있는 값은 1 또는 -1이다.
	int idCheck = mem.confirmId(mem_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idCheck.jsp 페이지</title>
</head>
<body>
	<table border="0" align="center">
		<tr>
			<td align="center">
				<%
					if(idCheck == 1) {  // ID가 중복인 경우
				%>
				<br>
				<%= mem_id %>는 이미 존재하는 ID 입니다.&nbsp;<br><br>
				<input type="button" value="닫기" 
				onClick="javascript:self.close(); opener.document.memberForm.mem_id.focus();"/>
				<%
					} else {
				%>
				<br>
				<%= mem_id %>는 사용 가능한 ID 입니다.&nbsp;<br><br>
				<input type="button" value="닫기" 
				onClick="javascript:self.close(); opener.document.memberForm.mem_passwd.focus();"/>
				<%
					}
				%>
			</td>
		</tr>
	</table>
</body>
</html>