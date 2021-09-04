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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		String sql = "SELECT * FROM NOTICE";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Notice> list = new ArrayList<Notice>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, "mytest", "mytest");
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("CREATE_DATE");
				String id = rs.getString("ID");
				String content = rs.getString("CONTENT");
				Notice notice = new Notice(no, title, regdate, id, content);
				list.add(notice);
			}
			
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp");
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
