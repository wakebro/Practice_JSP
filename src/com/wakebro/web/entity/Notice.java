package com.wakebro.web.entity;

import java.sql.Date;

public class Notice {
	private int no;
	private String title;
	private Date regdate;
	private String id;
	private String content;
	private String files;
	private int hit;
	private boolean pub;
	
	public Notice() {
	}
	
	public Notice(int no, String title, Date regdate, String id, String content, String files, int hit, boolean pub) {
		super();
		this.no = no;
		this.title = title;
		this.regdate = regdate;
		this.id = id;
		this.content = content;
		this.files = files;
		this.hit = hit;
		this.pub = pub;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	

	public boolean getPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	@Override
	public String toString() {
		return "Notice [no=" + no + ", title=" + title + ", regdate=" + regdate + ", id=" + id + ", content=" + content
				+ ", files=" + files + ", hit=" + hit + ", pub=" + pub + "]";
	}
	

	
}
