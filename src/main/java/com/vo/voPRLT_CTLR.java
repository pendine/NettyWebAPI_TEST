package com.vo;

public class voPRLT_CTLR {
	String CTLR_MNGM_NMBR; // Controller ID
	String CTLR_MNGM_NM; // Controller name
	String PRLT_OPER_DVSN_CD;
	String PRLT_TYPE_DVSN_CD;
	char VALD_YN;
	double X_CRDN;
	double Y_CRDN;
	String ISTL_LCTN_ADDR;
	String TRMN_IP;
	String TRMN_PORT;
	int CTLR_CLCT_CYCL;
	int CTLR_STTS_CYCL;
	int WHOL_NPLS;
	String DEL_USER_ID;
	String DEL_DT;
	char DEL_YN;
	String ADTN_USER_ID;
	String ADTN_DT;
	String CHNG_USER_ID;
	String CHNG_DT;
	String PRLT_CTLR_ID;

	
	
	public String getCTLR_MNGM_NMBR() {
		return CTLR_MNGM_NMBR;
	}
	public void setCTLR_MNGM_NMBR(String cTLR_MNGM_NMBR) {
		CTLR_MNGM_NMBR = cTLR_MNGM_NMBR;
	}
	public String getCTLR_MNGM_NM() {
		return CTLR_MNGM_NM;
	}
	public void setCTLR_MNGM_NM(String cTLR_MNGM_NM) {
		CTLR_MNGM_NM = cTLR_MNGM_NM;
	}
	public String getPRLT_OPER_DVSN_CD() {
		return PRLT_OPER_DVSN_CD;
	}
	public void setPRLT_OPER_DVSN_CD(String pRLT_OPER_DVSN_CD) {
		PRLT_OPER_DVSN_CD = pRLT_OPER_DVSN_CD;
	}
	public String getPRLT_TYPE_DVSN_CD() {
		return PRLT_TYPE_DVSN_CD;
	}
	public void setPRLT_TYPE_DVSN_CD(String pRLT_TYPE_DVSN_CD) {
		PRLT_TYPE_DVSN_CD = pRLT_TYPE_DVSN_CD;
	}
	public char getVALD_YN() {
		return VALD_YN;
	}
	public void setVALD_YN(char vALD_YN) {
		VALD_YN = vALD_YN;
	}
	public double getX_CRDN() {
		return X_CRDN;
	}
	public void setX_CRDN(double x_CRDN) {
		X_CRDN = x_CRDN;
	}
	public double getY_CRDN() {
		return Y_CRDN;
	}
	public void setY_CRDN(double y_CRDN) {
		Y_CRDN = y_CRDN;
	}
	public String getISTL_LCTN_ADDR() {
		return ISTL_LCTN_ADDR;
	}
	public void setISTL_LCTN_ADDR(String iSTL_LCTN_ADDR) {
		ISTL_LCTN_ADDR = iSTL_LCTN_ADDR;
	}
	public String getTRMN_IP() {
		return TRMN_IP;
	}
	public void setTRMN_IP(String tRMN_IP) {
		TRMN_IP = tRMN_IP;
	}
	public String getTRMN_PORT() {
		return TRMN_PORT;
	}
	public void setTRMN_PORT(String tRMN_PORT) {
		TRMN_PORT = tRMN_PORT;
	}
	public int getCTLR_CLCT_CYCL() {
		return CTLR_CLCT_CYCL;
	}
	public void setCTLR_CLCT_CYCL(int cTLR_CLCT_CYCL) {
		CTLR_CLCT_CYCL = cTLR_CLCT_CYCL;
	}
	public int getCTLR_STTS_CYCL() {
		return CTLR_STTS_CYCL;
	}
	public void setCTLR_STTS_CYCL(int cTLR_STTS_CYCL) {
		CTLR_STTS_CYCL = cTLR_STTS_CYCL;
	}
	public int getWHOL_NPLS() {
		return WHOL_NPLS;
	}
	public void setWHOL_NPLS(int wHOL_NPLS) {
		WHOL_NPLS = wHOL_NPLS;
	}
	public String getDEL_USER_ID() {
		return DEL_USER_ID;
	}
	public void setDEL_USER_ID(String dEL_USER_ID) {
		DEL_USER_ID = dEL_USER_ID;
	}
	public String getDEL_DT() {
		return DEL_DT;
	}
	public void setDEL_DT(String dEL_DT) {
		DEL_DT = dEL_DT;
	}
	public char getDEL_YN() {
		return DEL_YN;
	}
	public void setDEL_YN(char dEL_YN) {
		DEL_YN = dEL_YN;
	}
	public String getADTN_USER_ID() {
		return ADTN_USER_ID;
	}
	public void setADTN_USER_ID(String aDTN_USER_ID) {
		ADTN_USER_ID = aDTN_USER_ID;
	}
	public String getADTN_DT() {
		return ADTN_DT;
	}
	public void setADTN_DT(String aDTN_DT) {
		ADTN_DT = aDTN_DT;
	}
	public String getCHNG_USER_ID() {
		return CHNG_USER_ID;
	}
	public void setCHNG_USER_ID(String cHNG_USER_ID) {
		CHNG_USER_ID = cHNG_USER_ID;
	}
	public String getCHNG_DT() {
		return CHNG_DT;
	}
	public void setCHNG_DT(String cHNG_DT) {
		CHNG_DT = cHNG_DT;
	}
	public String getPRLT_CTLR_ID() {
		return PRLT_CTLR_ID;
	}
	public void setPRLT_CTLR_ID(String pRLT_CTLR_ID) {
		PRLT_CTLR_ID = pRLT_CTLR_ID;
	}
	
	public String toString() {
		return "PRLT_CTLR : CTLR_MNGM_NMBR [ "+CTLR_MNGM_NMBR+" ] "+
		 "CTLR_MNGM_NM [ "+ CTLR_MNGM_NM + " ] "+
		 "PRLT_OPER_DVSN_CD [ " + PRLT_OPER_DVSN_CD + " ] "+
		 "PRLT_TYPE_DVSN_CD [ " + PRLT_TYPE_DVSN_CD + " ] " +
		 "VALD_YN [ " + VALD_YN + " ] "+
		 "X_CRDN [ " +X_CRDN + " ] "+
		 "Y_CRDN [ " + Y_CRDN + " ] " +
		 "ISTL_LCTN_ADDR [ " +ISTL_LCTN_ADDR + " ] "+
		 "TRMN_IP [ " + TRMN_IP + " ] " +
		 "TRMN_PORT [ " + TRMN_PORT + " ] " +
		 "CTLR_CLCT_CYCL [ " + CTLR_CLCT_CYCL + " ] "+
		 "CTLR_STTS_CYCL [ " + CTLR_STTS_CYCL + " ] " +
		 "WHOL_NPLS [ " + WHOL_NPLS + " ] "+
		 "DEL_USER_ID [ " + DEL_USER_ID + " ] "+
		 "DEL_DT [ "+ DEL_DT +" ] "+
		 "DEL_YN [ "+ DEL_YN + " ] " +
		 "ADTN_USER_ID [ " + ADTN_USER_ID + " ] " +
		 "ADTN_DT [ " + ADTN_DT +" ] "+
		 "CHNG_USER_ID [ " + CHNG_USER_ID + " ] "+ 
		 "CHNG_DT [ " + CHNG_DT + " ] " + 
		 "PRLT_CTLR_ID [ "+ PRLT_CTLR_ID + 
		  " ] ";
		 

	}
}
