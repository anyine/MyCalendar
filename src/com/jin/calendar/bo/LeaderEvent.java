package com.jin.calendar.bo;

import java.util.Date;

public class LeaderEvent extends Event {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String leaderName;
	private String email;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LeaderEvent() {
		super();
	}
	
	public LeaderEvent(int id, String title, Date start, Date end, String userName, String leaderName, String email, String status, boolean editable) {
		super();
		this.id=id;
		this.title=title;
		this.start=start;
		this.end=end;
		this.userName = userName;
		this.leaderName = leaderName;
		this.email = email;
		this.status = status;
		this.editable = editable;
	}
}
