package com.wakebro.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.wakebro.web.entity.Notice;
import com.wakebro.web.entity.NoticeView;

public class NoticeService {
	
	public int removeNoticeAll(int[] ids) {
		
		return 0;
	}
	public int pubNoticeAll(int[] ids) {
		return 0;
	}
	public int insertNotice(Notice notices) {
		return 0;
	}
	public int deleteNotice(int id) {
		return 0;
	}
	public int updateNotice(Notice notice) {
		return 0;
	}
	public List<Notice> getNoticeNewestList() {
		return null;
	}
	
	public List<NoticeView> getNoticeList(){
		return getNoticeList("title", "", 1);
	}
	public List<NoticeView> getNoticeList(int page){
		return getNoticeList("title", "", page);
	}
	public List<NoticeView> getNoticeList(String field, String query, int page){
		List<NoticeView> list = new ArrayList<NoticeView>();
		String sql = "SELECT * FROM ("
				+ "SELECT ROWNUM NUM, N.* FROM ("
				+ "SELECT * FROM NOTICE_VIEW WHERE "+field+" LIKE ? ORDER BY CREATE_DATE DESC) N"
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
				//String content = rs.getString("CONTENT");
				String files = rs.getString("FILES");
				int hit = rs.getInt("HIT");
				int cmtCount = rs.getInt("CMT_COUNT");
				NoticeView notice = new NoticeView(
						no, 
						title, 
						regdate, 
						id,
						//content,
						files, 
						hit,
						cmtCount);
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
		return getNoticeCount("title", "");
	}
	public int getNoticeCount(String field, String query) {
		int count = 0;
		String sql = "SELECT COUNT(NO) COUNT FROM ("
				+ "SELECT ROWNUM NUM, N.* FROM ("
				+ "SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY CREATE_DATE DESC) N)";
		
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, "mytest", "mytest");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + query + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				count = rs.getInt("COUNT");
			
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
		
		return count;
	}
	
	public Notice getNotice(int no) {
		Notice notice = null;
		String sql = "SELECT * FROM NOTICE WHERE NO=?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, "mytest", "mytest");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int nNo = rs.getInt("NO");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("CREATE_DATE");
				String id = rs.getString("ID");
				String content = rs.getString("CONTENT");
				String files = rs.getString("FILES");
				int hit = rs.getInt("HIT");
				notice = new Notice(nNo, title, regdate, id, content, files, hit);
			};
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
		return notice;
	}
	public Notice getNextNotice(int no) {
		Notice notice = null;
		String sql = "SELECT * FROM NOTICE WHERE NO = ("
				+ "SELECT NO FROM NOTICE WHERE CREATE_DATE > ("
				+ "SELECT CREATE_DATE FROM NOTICE WHERE NO=?) "
				+ "AND ROWNUM=1)";
		
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, "mytest", "mytest");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int nNo = rs.getInt("NO");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("CREATE_DATE");
				String id = rs.getString("ID");
				String content = rs.getString("CONTENT");
				String files = rs.getString("FILES");
				int hit = rs.getInt("HIT");
				notice = new Notice(nNo, title, regdate, id, content, files, hit);
			};
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
		
		return notice;
	}
	public Notice getPrevNotice(int no) {
		Notice notice = null;
		String sql = "SELECT * FROM NOTICE WHERE NO=(" + 
				"SELECT NO FROM (SELECT * FROM NOTICE ORDER BY CREATE_DATE DESC) " + 
				"WHERE CREATE_DATE<(SELECT CREATE_DATE FROM NOTICE WHERE NO=?) " + 
				"AND ROWNUM = 1)";
		

		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, "mytest", "mytest");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int nNo = rs.getInt("NO");
				String title = rs.getString("TITLE");
				Date regdate = rs.getDate("CREATE_DATE");
				String id = rs.getString("ID");
				String content = rs.getString("CONTENT");
				String files = rs.getString("FILES");
				int hit = rs.getInt("HIT");
				notice = new Notice(nNo, title, regdate, id, content, files, hit);
			};
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
		
		return notice;
	}
	public int deleteNoticeAll(int[] nos) {
		int result = 0;
		String params = "";
		
		for (int i = 0; i < nos.length; i++) {
			params += nos[i];
			
			if(i < nos.length -1)
				params += ",";
		}
		
		String sql = "DELETE NOTICE WHERE no= IN("+params+")";

		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		Connection con = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, "mytest", "mytest");
			stmt = con.createStatement();
			result = stmt.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!con.isClosed() && con != null)
					con.close();
				if (!stmt.isClosed() && stmt != null)
					stmt.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return result;
	}

}
