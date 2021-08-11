package com.wakebro.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
		// 1. EL 사용법_변수의 경우
		request.setAttribute("model", model);
		
		// 2. EL 사용법_배열의 경우
		String[] fruits = {"apple", "mango", "banana"};
		request.setAttribute("fruits", fruits);
		
		// 3. EL 사용법_Map의 경우
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", 1);
		m.put("title", "EL 사용법");
		request.setAttribute("m", m);
		
		RequestDispatcher dp=request.getRequestDispatcher("spag.jsp");
		dp.forward(request, response);
	}
}
