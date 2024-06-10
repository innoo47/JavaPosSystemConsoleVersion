package com.JavaPosSystem;

public class Login_Model {

	private static Login_Model singleton = new Login_Model();

	public static synchronized Login_Model getInstance() {
		return singleton;
	}

	private String id;
	private String pw;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
