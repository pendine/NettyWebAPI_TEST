package com.domain;

//예보 버전 조회용 객체
public class functionFcstVersion {

	//페이지번호
	int pageNo;
	
	//한 페이지 결과수
	int numOfRows;
	
	//파일 구분용 매개변수 
	//파일구분-ODAM: 동네예보실황, -VSRT: 동네예보초단기, -SHRT: 동네예보단기
	String ftype; 
	
	//각각의 base_time 로 검색 참고자료 참조
	String basedatetime;

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

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getBasedatetime() {
		return basedatetime;
	}

	public void setBasedatetime(String basedatetime) {
		this.basedatetime = basedatetime;
	}
		
}
