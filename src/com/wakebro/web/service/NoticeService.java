package com.wakebro.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.wakebro.web.entity.Notice;

public class NoticeService {
	public List<Notice> getNoticeList(){
		return getNoticeList("title", "", 1);
	}
	public List<Notice> getNoticeList(int page){
		return getNoticeList("title", "", page);
	}
	public List<Notice> getNoticeList(String field, String query, int page){
		List<Notice> list = new ArrayList<Notice>();
		String sql = "SELECT * FROM ("
				+ "SELECT ROWNUM NUM, N.* FROM ("
				+ "SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY CREATE_DATE DESC) N"
				+ ") WHERE NUM BETWEEN ? AND ?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, "mytest", "mytest");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			pstmt.setInt(2, 1 + (page-1) * 10);
			pstmt.setInt(3, page * 10);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("CREATE_DATE");
				String id = rs.getString("ID");
				String content = rs.getString("CONTENT");
				String files = rs.getString("FILES");
				int hit = rs.getInt("HIT");
				Notice notice = new Notice(no, title, regdate, id, content, files, hit);
				list.add(notice);
			}
			
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
		
		return list;
	}
	
	public int getNoticeCount() {
		return getNoticeCount("title", "", 1);
	}
	public int getNoticeCount(String field, String query, int page) {
		String sql = "SELECT * FROM ("
				+ "SELECT ROWNUM NUM, N.* FROM ("
				+ "SELECT * FROM NOTICE ORDER BY CREATE_DATE DESC)"
				+ ") WHERE NUM BETWEEN ? AND ?";
		
		return 0;
	}
	
	public Notice getNotice(int no) {
		String sql = "SELECT * FROM WHERE NO=?";
		return null;
	}
	public Notice getNextNotice(int no) {
		String sql = "SELECT * FROM NOTICE WHERE NO = ("
				+ "SELECT NO FROM NOTICE WHERE CREATE_DATE > ("
				+ "SELECT CREATE_DATE FROM NOTICE WHERE NO=?) "
				+ "AND ROWNUM=1)";
		return null;
	}
	public Notice getPrevNotice(int no) {
		String sql = "SELECT * FROM NOTICE WHERE NO=(" + 
				"SELECT NO FROM (SELECT * FROM NOTICE ORDER BY CREATE_DATE DESC) " + 
				"WHERE CREATE_DATE<(SELECT CREATE_DATE FROM NOTICE WHERE NO=?) " + 
				"AND ROWNUM = 1)";
		return null;
	}

}
