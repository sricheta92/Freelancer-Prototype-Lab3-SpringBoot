package com.payload;

public class ProjectBidPayload {
	
	private int project_id;
	private int user_id;
	private int bid_price;
	private int bid_days;
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getBid_price() {
		return bid_price;
	}
	public void setBid_price(int bid_price) {
		this.bid_price = bid_price;
	}
	public int getBid_days() {
		return bid_days;
	}
	public void setBid_days(int bid_days) {
		this.bid_days = bid_days;
	}

}
