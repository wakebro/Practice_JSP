<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 스파게티코드와 MVC1
// 	int num = 0;
// 	String model ="";
// 	String num_ = request.getParameter("n");
// 	if(num_ != null && !num_.equals(""))
// 		num=Integer.parseInt(num_);
// 	if(num%2 != 0){
// 		model = "홀수";
// 	}else {
// 		model = "짝수";
// 	}
%>
<%-- ------------------------------------------- --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
pageContext.setAttribute("model", "hello");
%>
<body>
	<%-- EL의 사용 --%>
	<%=request.getAttribute("model") %>입니다.<br>
	${model}<br>
	
	<%-- EL의 사용_배열의 경우 --%>
	${fruits[0] }<br>
	${fruits[1] }<br>
	${fruits[2] }<br>
	
	<%-- EL의 사용_Map의 경우 --%>
	${m.id }<br>
	${m.title }<br>
	
	<%-- *Scope 사용 --%>
	${requestScope.model}<br>
	
	<%-- EL 내장객체 --%>
	${param.n }<br><%-- 홀수인지 짝수인지 받는 쿼리스트링 n --%>
	${header.accept}<br>
	
	<%-- EL 연산자 --%>
	${param.n > 4 }<br>
	${param.n gt 4 }<br>
	${empty param.n?'값이 없습니다':param.n}<br>
	${empty param.n}<br>
	${not empty param.n}<br>
	${not empty param.n?param.n:'값이 없습니다'}<br>
	${param.n/3}<br>
</body>
</html>