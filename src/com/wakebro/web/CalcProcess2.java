package com.wakebro.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/calc2")
public class CalcProcess2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, 
						   HttpServletResponse resp) 
					throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; character=UTF-8");
		ServletContext application = req.getServletContext();
		HttpSession session = req.getSession();
		
		PrintWriter out = resp.getWriter();
		String v_ = req.getParameter("v");
		String op = req.getParameter("operator");
		
		int v = 0;
		int result = 0;

		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		// 계산
		if(op.equals("=")) {
//			int x = (Integer)application.getAttribute("value");
			int x = (Integer)session.getAttribute("value");
			int y = v;
//			String operator = (String)application.getAttribute("op");
			String operator = (String)session.getAttribute("op");
			if(operator.equals("+"))
				result = x + y;
			else	
				result = x - y;
			out.print("계산 결과 : " + result);
		}
		// 값을 저장
		else {
//			application.setAttribute("value", v);
//			application.setAttribute("op", op);
			session.setAttribute("value", v);
			session.setAttribute("op", op);
			resp.sendRedirect("calc2.html");
		}
		
	}
}
