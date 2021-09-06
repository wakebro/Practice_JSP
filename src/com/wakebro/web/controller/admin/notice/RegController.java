package com.wakebro.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.wakebro.web.entity.Notice;
import com.wakebro.web.service.NoticeService;

@MultipartConfig(
//	location = "/tmp",
	fileSizeThreshold = 1024*1024,
	maxFileSize = 1024*1024*50,
	maxRequestSize = 1024*1024*50*5
)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp");
		rd.forward(request, response);
				
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		
		Collection<Part> parts = request.getParts();
		StringBuilder builder = new StringBuilder();
		for(Part p : parts) {
			if(!(p.getName().equals("file")))
				continue;
			
			Part filepart = p;
			String fileName = filepart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");
			InputStream fis = filepart.getInputStream();
			
			String realPath = request.getServletContext().getRealPath("/upload");
			File dir = new File(realPath);
			if(!dir.exists()) {
				String cmd = "chmod -R 777" + realPath;
				Runtime.getRuntime().exec(cmd);
			}
			String filePath = realPath + File.separator + fileName;
			FileOutputStream fos = new FileOutputStream(filePath);
			
			byte[] buf = new byte[1024];
			int size = 0;
			while((size = fis.read(buf)) != -1) 
				fos.write(buf, 0, size);
			
			
			fos.close();
			fis.close();
			
		}
		builder.delete(builder.length()-1, builder.length());
		
		boolean pub = false;
		if(isOpen != null)
			pub = true;
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setId("ali");
		notice.setFiles(builder.toString());
		
		NoticeService service = new NoticeService();
		//service.insertNotice(notice);
		
		response.sendRedirect("list");
		
		
	}

}
