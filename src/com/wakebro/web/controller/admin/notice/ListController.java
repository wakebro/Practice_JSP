package com.wakebro.web.controller.admin.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wakebro.web.entity.Notice;
import com.wakebro.web.entity.NoticeView;
import com.wakebro.web.service.NoticeService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] openNos = request.getParameterValues("open-no");
		String[] delNos = request.getParameterValues("del-no");
		String cmd = request.getParameter("cmd");
		
		switch(cmd) {
		case "일괄공개":
			break;
		case "일괄삭제":
			NoticeService service = new NoticeService();
			int[] nos = new int[delNos.length];
			for (int i = 0; i < nos.length; i++) {
				nos[i] = Integer.parseInt(delNos[i]);
			}
			int resultCode = service.deleteNoticeAll(nos);
			break;
		}
		
		response.sendRedirect("list");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title";
		if(field_ != null && !field_.equals(""))
			field = field_;
		
		String query = "";
		if(query_ != null && !query.equals(""))
			query = query_;
		
		int page = 1;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getNoticeList(field, query, page);
		int count = service.getNoticeCount(field, query);
		count = (count % 10) == 0 ? count / 10 : (count / 10) + 1; 
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
			
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp");
		rd.forward(request, response);
			
		
	}
}
