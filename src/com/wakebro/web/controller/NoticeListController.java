package com.wakebro.web.controller;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService service = new NoticeService();
		List<Notice> list = service.getNoticeList();
		
		request.setAttribute("list", list);
			
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp");
		rd.forward(request, response);
			
		
	}
}
