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
<body>
	<%=request.getAttribute("model") %>입니다.<br>
	${model}<br>
	${fruits[0] }<br>
	${fruits[1] }<br>
	${fruits[2] }<br>
	${m.id }<br>
	${m.title }<br>
</body>
</html>