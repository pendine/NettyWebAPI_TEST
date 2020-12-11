package com.vo;

public class voUNIT_SYST {
	String SYST_ID;
    String SYST_STTS_CD;
    String CRTN_DT;
	public String getSYST_ID() {
		return SYST_ID;
	}
	public void setSYST_ID(String sYST_ID) {
		SYST_ID = sYST_ID;
	}
	public String getSYST_STTS_CD() {
		return SYST_STTS_CD;
	}
	public void setSYST_STTS_CD(String sYST_STTS_CD) {
		SYST_STTS_CD = sYST_STTS_CD;
	}
	public String getCRTN_DT() {
		return CRTN_DT;
	}
	public void setCRTN_DT(String cRTN_DT) {
		CRTN_DT = cRTN_DT;
	}
	
	public String toString() {
		 return " SYST_ID : " + this.SYST_ID + " SYST_STTS_CD : " + this.SYST_STTS_CD+" CRTN_DT : " + this.CRTN_DT ;
		    
	}
	
}
