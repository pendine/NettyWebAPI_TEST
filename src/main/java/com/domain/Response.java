package com.domain;

import com.protocol.weather.Body;
import com.protocol.weather.Header;

import lombok.Data;

@Data
public class Response {
	
	private Header header = new Header();
	private Body body = new Body();

}
