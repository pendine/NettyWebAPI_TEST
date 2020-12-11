package com.vo;

import com.util.DateHelper;

public class voPRLT_CTLR_STTS {
	String CTLR_MNGM_NMBR;
	String UPDT_DT;
	String CMNC_STTS_CD;

	
	public void init() {
		this.UPDT_DT = DateHelper.getDate();
	}
	
	public void init(String CTLR_MNGM_NMBR) {
		this.CTLR_MNGM_NMBR = CTLR_MNGM_NMBR;
		this.UPDT_DT = DateHelper.getDate();
	}
	
	public voPRLT_CTLR_STTS() {}
	
	public voPRLT_CTLR_STTS(String CTLR_MNGM_NMBR) {
		this.CTLR_MNGM_NMBR = CTLR_MNGM_NMBR;
		this.UPDT_DT = DateHelper.getDate();
	}	
	
	public String getCTLR_MNGM_NMBR() {
		return CTLR_MNGM_NMBR;
	}
	public void setCTLR_MNGM_NMBR(String cTLR_MNGM_NMBR) {
		CTLR_MNGM_NMBR = cTLR_MNGM_NMBR;
	}
	public String getUPDT_DT() {
		return UPDT_DT;
	}
	public void setUPDT_DT(String uPDT_DT) {
		UPDT_DT = uPDT_DT;
	}
	public String getCMNC_STTS_CD() {
		return CMNC_STTS_CD;
	}
	public void setCMNC_STTS_CD(String cMNC_STTS_CD) {
		CMNC_STTS_CD = cMNC_STTS_CD;
	}
	
	public String toString() {
		return " CTLR_MNGM_NMBR : "+ CTLR_MNGM_NMBR +
		 " | UPDT_DT : " + UPDT_DT +
		 " | CMNC_STTS_CD : " + CMNC_STTS_CD;
	}
	
}