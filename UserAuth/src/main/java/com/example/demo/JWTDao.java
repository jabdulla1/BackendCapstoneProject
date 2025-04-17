package com.example.demo;

public class JWTDao {
	
	private String token;

	public JWTDao() {
		super();
	}

	public JWTDao(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
