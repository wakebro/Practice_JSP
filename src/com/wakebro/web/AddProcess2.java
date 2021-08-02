package com.wakebro.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/add2")
public class AddProcess2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, 
						   HttpServletResponse resp) 
					throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; character=UTF-8");
		
		PrintWriter out = resp.getWriter();
		String[] num_ = req.getParameterValues("num");
		
		int result = 0;
		
		for(String n : num_) {
			int num = Integer.parseInt(n);
			result += num;
		}
				
		out.print("계산 결과 : "+result);
	}
}
