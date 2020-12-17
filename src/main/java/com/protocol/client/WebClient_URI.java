package com.protocol.client;

import java.net.URI;
import java.net.URISyntaxException;

import lombok.Data;

//기상청 접속시의 기본 uri 는 아래와 같이 설정해놓음
@Data
public class WebClient_URI {
	
	private String weather = System.getProperty("url", "http://apis.data.go.kr");
    
    private String DeafualtUrl = "/1360000/VilageFcstInfoService" ;
    private String viewVilageFcst = "/getVilageFcst";
    private String serviceKey = "?serviceKey=";
    private String APIKey = "fW24b%2FUEGRWODAIUo6Nx0owzv7jQSggx5iSzj0JeQY4z1UHlo4spwka5vM4XeuRMLpKwg6a%2B%2F%2Bu65jtdoFl67g%3D%3D";
    
    private String service = serviceKey + APIKey;
    
    private String numOfRaws = "&numOfRows=";
    private int numOfRawsInt = 10;
    
    private String pageNo = "&pageNo=";
    private int pageNoInt = 1;
    
    private String dataType = "&dataType=";
    private String resultType = "JSON";
    
    private String base_date = "&base_date=";
    private String base_dateInt = "20201217";
    
    private String base_time = "&base_time=";
    private String base_timeInt = "1100"; 
    
    
    private String nx = "&nx=";
    private int nxInt = 55;
    
    private String ny = "&ny="; 
    private int nyInt = 127;
    
    
    public String getURIAddress() {
    	return weather.toString() 
				+DeafualtUrl
				+ viewVilageFcst
				+ service
				+ pageNo + pageNoInt
				+ numOfRaws + numOfRawsInt
				+ dataType  + resultType
				+ base_date + base_dateInt
				+ base_time + base_timeInt
				+ nx + nxInt
				+ ny + nyInt;
    }
    
    public URI getURI() {
    	try 
    	{
			return new URI(weather.toString()
					+DeafualtUrl
					+ viewVilageFcst
					+ service
					+ pageNo + pageNoInt
					+ numOfRaws + numOfRawsInt
					+ dataType  + resultType
					+ base_date + base_dateInt
					+ base_time + base_timeInt
					+ nx + nxInt
					+ ny + nyInt);
		} 
    	catch (URISyntaxException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    
}
