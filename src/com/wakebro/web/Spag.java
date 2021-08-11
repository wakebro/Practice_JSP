package com.wakebro.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;
		String model ="";
		String num_ = request.getParameter("n");
		if(num_ != null && !num_.equals(""))
			num=Integer.parseInt(num_);
		if(num%2 != 0){
			model = "홀수";
		}else {
			model = "짝수";
		}
		request.setAttribute("model", model);
		RequestDispatcher dp=request.getRequestDispatcher("spag.jsp");
		dp.forward(request, response);
	}
}
