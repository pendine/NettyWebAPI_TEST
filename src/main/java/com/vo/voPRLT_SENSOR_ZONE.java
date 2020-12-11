package com.vo;

public class voPRLT_SENSOR_ZONE {
	/*
	 * Column Name		ID	Pk	Null?	Data Type			Default	Comments	Histogram	Encryption Alg	Salt
	 * 
	 * CTLR_MNGM_NMBR	1	1	N		VARCHAR2 (20 Byte)			제어기 관리 번호	Frequency
	 * PRZN_NMBR		2	2	N		VARCHAR2 (20 Byte)			주차구역 번호	None
	 * PRZN_ID			3		Y		VARCHAR2 (30 Byte)			주차구역 ID	None
	 * PRZN_NM			4		Y		VARCHAR2 (50 Byte)			주차구역 명		None
	 * PRZN_KIND		5		Y		VARCHAR2 (7 Byte)	NULL 	주차구역 종류(0:전체, 1:일반, 2:장애인, 3:임산부, 4:경차)	None
	 * GROP_DEVC_NMBR	6		Y		VARCHAR2 (20 Byte)			그룹 장치 번호	None		
	 * GROP_DEVC_ID		7		Y		VARCHAR2 (30 Byte)			그룹 장치 ID	None
	 * CHNL_VAL 채널값 정보도 있지만 사용하지 않기때문에 취급 안함
	 */
	
	String CTLR_MNGM_NMBR;	
	String PRZN_NMBR;
	String PRZN_ID;
	String PRZN_NM;
	String PRZN_KIND;
	String GROP_DEVC_NMBR;		
	String GROP_DEVC_ID;
	
	
	public voPRLT_SENSOR_ZONE() {}
	
//	public voPRLT_SENSOR_ZONE init(String CTLR_MNGM_NMBR, ) {	}
	
	public String getCTLR_MNGM_NMBR() {
		return CTLR_MNGM_NMBR;
	}
	public void setCTLR_MNGM_NMBR(String cTLR_MNGM_NMBR) {
		CTLR_MNGM_NMBR = cTLR_MNGM_NMBR;
	}
	public String getPRZN_NMBR() {
		return PRZN_NMBR;
	}
	public void setPRZN_NMBR(String pRZN_NMBR) {
		PRZN_NMBR = pRZN_NMBR;
	}
	public String getPRZN_ID() {
		return PRZN_ID;
	}
	public void setPRZN_ID(String pRZN_ID) {
		PRZN_ID = pRZN_ID;
	}
	public String getPRZN_NM() {
		return PRZN_NM;
	}
	public void setPRZN_NM(String pRZN_NM) {
		PRZN_NM = pRZN_NM;
	}
	public String getPRZN_KIND() {
		return PRZN_KIND;
	}
	public void setPRZN_KIND(String pRZN_KIND) {
		PRZN_KIND = pRZN_KIND;
	}
	public String getGROP_DEVC_NMBR() {
		return GROP_DEVC_NMBR;
	}
	public void setGROP_DEVC_NMBR(String gROP_DEVC_NMBR) {
		GROP_DEVC_NMBR = gROP_DEVC_NMBR;
	}
	public String getGROP_DEVC_ID() {
		return GROP_DEVC_ID;
	}
	public void setGROP_DEVC_ID(String gROP_DEVC_ID) {
		GROP_DEVC_ID = gROP_DEVC_ID;
	}
	
	public String toString() {
		return "CTLR_MNGM_NMBR : "+ CTLR_MNGM_NMBR+	
		"	| PRZN_NMBR : " + PRZN_NMBR +
		"	| PRZN_ID : " + PRZN_ID +
		"	| PRZN_NM : " + PRZN_NM + 
		"	| PRZN_KIND : " + PRZN_KIND +
		"	| GROP_DEVC_NMBR : " + GROP_DEVC_NMBR +		
		"	| GROP_DEVC_ID : " + GROP_DEVC_ID;
	}

}
