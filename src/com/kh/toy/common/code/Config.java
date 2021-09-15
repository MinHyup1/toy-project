package com.kh.toy.common.code;

public enum Config {
	//DOMAIN("http://pclass.ga")
	DOMAIN("http://localhost:9090"),
	COMPANY_EMAIL("p01073027511@gmail.com"),
	STMP_AUTHENTICATION_ID("p01073027511@gmail.com"),
	STMP_AUTHENTICATION_PASSWORD("k75117511"),
	//UPLOAD_PATH("C:\\CODE\\upload\\")
	UPLOAD_PATH("C:\\CODE\\upload\\");
	
	public final String DESC;
	
	private Config(String desc) {
		this.DESC = desc;
	
	}
}
