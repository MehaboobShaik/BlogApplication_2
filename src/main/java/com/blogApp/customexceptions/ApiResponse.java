package com.blogApp.customexceptions;

public class ApiResponse {

	private String messege;
	private boolean success;
	public ApiResponse() {
		super();
	}
	public ApiResponse(String messege, boolean success) {
		super();
		this.messege = messege;
		this.success = success;
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
