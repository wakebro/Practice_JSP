package com.wakebro.web.controller.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wakebro.web.entity.Notice;
import com.wakebro.web.service.NoticeService;

import java.sql.*;

@WebServlet("/notice/detail")
public class DetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeService service = new NoticeService();
		Notice notice = service.getNotice(no);
		
		request.setAttribute("notice", notice);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp");
		rd.forward(request, response);
		
	}

}
