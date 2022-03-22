<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member_control.jsp 페이지</title>
<!-- 회원가입 폼에서 넘어오는 한글 데이터가 깨지지 않게 하기 위해서 추가 -->
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="mem" scope="page" class="member.DBBean"/>
<jsp:setProperty property="*" name="mem"/>

<%
	String action = request.getParameter("action");

	if(action.equals("insert")) {
		if(mem.insertDB()) {
			response.sendRedirect("welcome.jsp");
			System.out.println("member 테이블에 데이터 입력 성공!!");
		} else {
			response.sendRedirect("Member_Form.jsp");
			System.out.println("member 테이블에 데이터 입력 실패!!");
		}
	}
%>
</head>
<body>

</body>
</html>