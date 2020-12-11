package com.vo;

public class voPRLT_SENSOR_STAT {
	/*
	 *Column Name		ID	Pk	Null?	Data Type	Default	Comments	Histogram	Encryption Alg	Salt
	 *
	 *CTLR_MNGM_NMBR	1	1	N	VARCHAR2 (20 Byte)		제어기 관리 번호	None
	 *PRZN_NMBR			2	2	N	VARCHAR2 (20 Byte)		주차구역 번호	None
	 *CRTN_DT			3	3	N	VARCHAR2 (14 Byte)		생성 일시	None
	 *PARK_STTS_CD		4		Y	VARCHAR2 (7 Byte)	NULL 	주차 상태 코드	None
	 *BATR_STTS_CD		5		Y	VARCHAR2 (7 Byte)	NULL 	배터리 상태 코드	None
	 * */

	String CTLR_MNGM_NMBR;
	String PRZN_NMBR;
	String CRTN_DT;
	String UPDT_DT;
	String PARK_STTS_CD;
	String BATR_STTS_CD;
	String PRZN_ID;
	
	
	public String getPRZN_ID() {
		return PRZN_ID;
	}
	public void setPRZN_ID(String pRZN_ID) {
		PRZN_ID = pRZN_ID;
	}
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
	public String getUPDT_DT() {
		return UPDT_DT;
	}
	public void setUPDT_DT(String cRTN_DT) {
		UPDT_DT = cRTN_DT;
	}
	public String getCRTN_DT() {
		return CRTN_DT;
	}
	public void setCRTN_DT(String cRTN_DT) {
		CRTN_DT = cRTN_DT;
	}
	
	public String getPARK_STTS_CD() {
		return PARK_STTS_CD;
	}
	public void setPARK_STTS_CD(String pARK_STTS_CD) {
		PARK_STTS_CD = pARK_STTS_CD;
	}
	
	public String getBATR_STTS_CD() {
		return BATR_STTS_CD;
	}
	public void setBATR_STTS_CD(String bATR_STTS_CD) {
		BATR_STTS_CD = bATR_STTS_CD;
	}
	
	public String toString() {
		 return "CTLR_MNGM_NMBR : " + CTLR_MNGM_NMBR +
			"	| PRZN_NMBR : " + PRZN_NMBR +
			"	| PRZN_ID : " + PRZN_ID +
			"	| CRTN_DT : " + CRTN_DT +
			"	| PARK_STTS_CD : " + PARK_STTS_CD +
			"	| BATR_STTS_CD : " + BATR_STTS_CD;
	}
}
