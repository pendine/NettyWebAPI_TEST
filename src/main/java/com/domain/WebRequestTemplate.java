package com.domain;

public class WebRequestTemplate {
	
	
	public String endPoint = "http://apis.data.go.kr/1360000/VilageFcstInfoService";
	private String APIKey = "fW24b%2FUEGRWODAIUo6Nx0owzv7jQSggx5iSzj0JeQY4z1UHlo4spwka5vM4XeuRMLpKwg6a%2B%2F%2Bu65jtdoFl67g%3D%3D";
	private int pageNo = 1; //기본값
	private int numOfRows = 10; //기본값
	private String dataType;
	private String Date;
	private String DateDay;
	private String Datetime;
	private String x;//double형
	private String y;//double형
	
	public WebRequestTemplate() {
		// TODO Auto-generated constructor stub
		APIKey = "";
		dataType = "";
		DateDay = "";
		Datetime = "";
		x = "";
		y = "";
	}
	
	public String getAPIKey() {
		return APIKey;
	}
	public void setAPIKey(String aPIKey) {
		APIKey = aPIKey;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDate() {
		return DateDay;
	}
	public void setDate(String dateDay) {
		if(dateDay.length()!=8)
			new Exception("Exception on Date Length : " + dateDay.length() 
						+" Date Length is 8 ('YYYYMMDD')");
		DateDay = dateDay;
	}
	public String getDateDay() {
		return DateDay;
	}
	public void setDateDay(String dateDay) {
		if(dateDay.length()!=8)
			new Exception("Exception on DateDay Length : " + dateDay.length() 
						+" DateDay Length is 8 ('YYYYMMDD')");
		DateDay = dateDay;
	}
	public String getDatetime() {
		return Datetime;
	}
	public void setDatetime(String datetime) {
		if(datetime.length()!=4)
			new Exception("Exception on Datetime Length : " + Datetime.length() 
						+" DateDay Length is 4 ('HHMM')");
		Datetime = datetime;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	
	public String toString() {
		return   "APIKey : " + APIKey  
				+ " | pageNo : " + Integer.toString(pageNo) 
				+ " | numOfRows : " + Integer.toString(numOfRows)
				+ " | dataType : " + dataType
				+ " | DateDay : " + DateDay
				+ " | Datetime : " + Datetime
				+ " | x coordinate : " + x
				+ " | y coordinate : " + y;
	}
	
}
