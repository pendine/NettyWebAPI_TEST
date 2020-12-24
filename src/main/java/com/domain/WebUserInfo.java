package com.domain;

import lombok.Data;

@Data
public class WebUserInfo {

	String targetHost;
	int targetPort;
	String APIKey;
	String id;
	String pw;
	String requestText;
	
}
