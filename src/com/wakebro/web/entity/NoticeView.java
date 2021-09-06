package com.wakebro.web.entity;

import java.sql.Date;

public class NoticeView extends Notice {
	
	private int cmtCount;
	
	public NoticeView() {
	}
	
	public NoticeView(int no, String title, Date regdate, String id, String files, int hit, int cmtCount, boolean pub) {
		super(no, title, regdate, id, "", files, hit, pub);
		this.cmtCount = cmtCount;
	}

	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

	@Override
	public String toString() {
		return "NoticeView [cmtCount=" + cmtCount + "]";
	}


}
