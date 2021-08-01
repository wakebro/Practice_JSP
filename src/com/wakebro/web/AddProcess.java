package com.wakebro.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/add")
public class AddProcess extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, 
						   HttpServletResponse resp) 
					throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; character=UTF-8");
		
		PrintWriter out = resp.getWriter();
		String x_ = req.getParameter("x");
		String y_ = req.getParameter("y");
		int x = 0;
		int y = 0;

		if(x_!=null && !x_.equals(""))
			x = Integer.parseInt(x_);

		if(y_!=null && !y_.equals(""))
			y = Integer.parseInt(y_);
		
		int result = x + y;
		
		out.print("계산 결과 : "+result);
	}
}
