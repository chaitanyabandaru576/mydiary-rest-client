package com.chaitu.restapiclient.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;




public class Entry {

	private long id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")//bcz data base accepts this format.so changing format
	private Date entrydate;
	
	private String Description;
	
	private long userid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}
}
