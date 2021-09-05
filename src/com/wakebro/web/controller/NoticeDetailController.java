package com.wakebro.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wakebro.web.entity.Notice;

import java.sql.*;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		String sql = "SELECT * FROM NOTICE WHERE no=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, "mytest", "mytest");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			rs.next();
			String title = rs.getString("TITLE");
			Date regdate = rs.getDate("CREATE_DATE");
			String id = rs.getString("ID");
			String content = rs.getString("CONTENT");
			String files = rs.getString("FILES");
			
			Notice notice = new Notice(no, title, regdate, id, content, files);
			
			request.setAttribute("notice", notice);
			/*
			request.setAttribute("title", title);
			request.setAttribute("regdate", regdate);
			request.setAttribute("id", id);
			request.setAttribute("content", content);
			*/
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!con.isClosed() && con != null)
					con.close();
				if (!pstmt.isClosed() && pstmt != null)
					pstmt.close();
				if (!rs.isClosed() && rs != null)
					rs.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
