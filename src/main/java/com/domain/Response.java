package com.domain;

import lombok.Data;

@Data
public class Response {
	
	private Header header = new Header();
	private Body body = new Body();

}
