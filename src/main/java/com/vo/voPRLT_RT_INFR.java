package com.vo;

public class voPRLT_RT_INFR {
	String CTLR_MNGM_NMBR;
	String FLOR_NMBR;
	String UPDT_DT;
	int GNRL_RMND_PRZN_NUM;
	int LGVH_RMND_PRZN_NUM; 
	int HVVH_RMND_PRZN_NUM; 
	int EMVH_RMND_PRZN_NUM; 
	int HNDC_RMND_PRZN_NUM; 
	int WMON_RMND_PRZN_NUM; 
	int ETC_RMND_PRZN_NUM; 
	int ELECT_RMND_PRZN_NUM;		
	int WHOLE_RMND_PRZN_NUM; 
	int ACML_INFL_UNUM ;	//'누적 유입 대수';
	int ACML_OTFL_UNUM ;	//'누적 유출 대수';
	int CTLR_RMND_NUM ;		//'제어기 잔여 개수';
	int CTLR_PARK_NUM ;		//'제어기 주차 개수';
	int OTFL_ABNR ;			//'유출 이상';
	int OTFL_NRML ;			//'유출 정상';
	int INFL_ABNR ;			//'유입 이상';
	int INFL_NRML ;			//'유입 정상';
	int OFST_VAL;			//옵셋값
	
	public voPRLT_RT_INFR() {}

	public voPRLT_RT_INFR(String CTLR_MNGM_NMBR) {
		this.CTLR_MNGM_NMBR = CTLR_MNGM_NMBR;
	}
	
	public int getOFST_VAL() {
		return OFST_VAL;
	}
	public void setOFST_VAL(int oFST_VAL) {
		OFST_VAL = oFST_VAL;
	}
	
	public int getACML_INFL_UNUM() {
		return ACML_INFL_UNUM;
	}

	public void setACML_INFL_UNUM(int aCML_INFL_UNUM) {
		ACML_INFL_UNUM = aCML_INFL_UNUM;
	}

	public int getACML_OTFL_UNUM() {
		return ACML_OTFL_UNUM;
	}

	public void setACML_OTFL_UNUM(int aCML_OTFL_UNUM) {
		ACML_OTFL_UNUM = aCML_OTFL_UNUM;
	}

	public int getCTLR_RMND_NUM() {
		return CTLR_RMND_NUM;
	}

	public void setCTLR_RMND_NUM(int cTLR_RMND_NUM) {
		CTLR_RMND_NUM = cTLR_RMND_NUM;
	}

	public int getCTLR_PARK_NUM() {
		return CTLR_PARK_NUM;
	}

	public void setCTLR_PARK_NUM(int cTLR_PARK_NUM) {
		CTLR_PARK_NUM = cTLR_PARK_NUM;
	}

	public int getOTFL_ABNR() {
		return OTFL_ABNR;
	}

	public void setOTFL_ABNR(int oTFL_ABNR) {
		OTFL_ABNR = oTFL_ABNR;
	}

	public int getOTFL_NRML() {
		return OTFL_NRML;
	}

	public void setOTFL_NRML(int oTFL_NRML) {
		OTFL_NRML = oTFL_NRML;
	}

	public int getINFL_ABNR() {
		return INFL_ABNR;
	}

	public void setINFL_ABNR(int iNFL_ABNR) {
		INFL_ABNR = iNFL_ABNR;
	}

	public int getINFL_NRML() {
		return INFL_NRML;
	}

	public void setINFL_NRML(int iNFL_NRML) {
		INFL_NRML = iNFL_NRML;
	}

	
	public String getCTLR_MNGM_NMBR() {
		return CTLR_MNGM_NMBR;
	}
	public void setCTLR_MNGM_NMBR(String cTLR_MNGM_NMBR) {
		CTLR_MNGM_NMBR = cTLR_MNGM_NMBR;
	}
	public String getFLOR_NMBR() {
		return FLOR_NMBR;
	}
	public void setFLOR_NMBR(String fLOR_NMBR) {
		FLOR_NMBR = fLOR_NMBR;
	}
	public String getUPDT_DT() {
		return UPDT_DT;
	}
	public void setUPDT_DT(String uPDT_DT) {
		UPDT_DT = uPDT_DT;
	}
	public int getGNRL_RMND_PRZN_NUM() {
		return GNRL_RMND_PRZN_NUM;
	}
	public void setGNRL_RMND_PRZN_NUM(int gNRL_RMND_PRZN_NUM) {
		GNRL_RMND_PRZN_NUM = gNRL_RMND_PRZN_NUM;
	}
	public int getLGVH_RMND_PRZN_NUM() {
		return LGVH_RMND_PRZN_NUM;
	}
	public void setLGVH_RMND_PRZN_NUM(int lGVH_RMND_PRZN_NUM) {
		LGVH_RMND_PRZN_NUM = lGVH_RMND_PRZN_NUM;
	}
	public int getHVVH_RMND_PRZN_NUM() {
		return HVVH_RMND_PRZN_NUM;
	}
	public void setHVVH_RMND_PRZN_NUM(int hVVH_RMND_PRZN_NUM) {
		HVVH_RMND_PRZN_NUM = hVVH_RMND_PRZN_NUM;
	}
	public int getEMVH_RMND_PRZN_NUM() {
		return EMVH_RMND_PRZN_NUM;
	}
	public void setEMVH_RMND_PRZN_NUM(int eMVH_RMND_PRZN_NUM) {
		EMVH_RMND_PRZN_NUM = eMVH_RMND_PRZN_NUM;
	}
	public int getHNDC_RMND_PRZN_NUM() {
		return HNDC_RMND_PRZN_NUM;
	}
	public void setHNDC_RMND_PRZN_NUM(int hNDC_RMND_PRZN_NUM) {
		HNDC_RMND_PRZN_NUM = hNDC_RMND_PRZN_NUM;
	}
	public int getWMON_RMND_PRZN_NUM() {
		return WMON_RMND_PRZN_NUM;
	}
	public void setWMON_RMND_PRZN_NUM(int wMON_RMND_PRZN_NUM) {
		WMON_RMND_PRZN_NUM = wMON_RMND_PRZN_NUM;
	}
	public int getETC_RMND_PRZN_NUM() {
		return ETC_RMND_PRZN_NUM;
	}
	public void setETC_RMND_PRZN_NUM(int eTC_RMND_PRZN_NUM) {
		ETC_RMND_PRZN_NUM = eTC_RMND_PRZN_NUM;
	}
	public int getELECT_RMND_PRZN_NUM() {
		return ELECT_RMND_PRZN_NUM;
	}
	public void setELECT_RMND_PRZN_NUM(int eLECT_RMND_PRZN_NUM) {
		ELECT_RMND_PRZN_NUM = eLECT_RMND_PRZN_NUM;
	}
	public int getWHOLE_RMND_PRZN_NUM() {
		return WHOLE_RMND_PRZN_NUM;
	}
	public void setWHOLE_RMND_PRZN_NUM(int wHOLE_RMND_PRZN_NUM) {
		WHOLE_RMND_PRZN_NUM = wHOLE_RMND_PRZN_NUM;
	}
	
	public void addWHOLE_RMND_PRZN_NUM(int count) {
		this.WHOLE_RMND_PRZN_NUM += count ;
	}
	
	public String toString() {
		String returnStr = " CTLR_MNGM_NMBR : "+ CTLR_MNGM_NMBR +
				" FLOR_NMBR : "+FLOR_NMBR + 
				" UPDT_DT : " + UPDT_DT +
//				" GNRL_RMND_PRZN_NUM : " + GNRL_RMND_PRZN_NUM +
//				" LGVH_RMND_PRZN_NUM : " + LGVH_RMND_PRZN_NUM + 
//				" HVVH_RMND_PRZN_NUM : " + HVVH_RMND_PRZN_NUM + 
//				" EMVH_RMND_PRZN_NUM : " + EMVH_RMND_PRZN_NUM +
//				" HNDC_RMND_PRZN_NUM : " + HNDC_RMND_PRZN_NUM + 
//				" WMON_RMND_PRZN_NUM : " + WMON_RMND_PRZN_NUM + 
//				" ETC_RMND_PRZN_NUM : " + ETC_RMND_PRZN_NUM +
//				" ELECT_RMND_PRZN_NUM : "+ ELECT_RMND_PRZN_NUM + 
				" WHOLE_RMND_PRZN_NUM : "+ WHOLE_RMND_PRZN_NUM + 
				" ACML_INFL_UNUM : "+ ACML_INFL_UNUM + 
				" ACML_OTFL_UNUM : "+ ACML_OTFL_UNUM + 
				" CTLR_RMND_NUM : "+ CTLR_RMND_NUM + 
				" CTLR_PARK_NUM : "+ CTLR_PARK_NUM + 
				" OTFL_ABNR : "+ OTFL_ABNR + 
				" OTFL_NRML : "+ OTFL_NRML + 
				" INFL_ABNR : "+ INFL_ABNR + 
				" INFL_NRML : "+ INFL_NRML + 
				" OFST_VAL : " + OFST_VAL;
		return returnStr;
	}
	
	public void resetEvent() {
		OTFL_ABNR  = 0 ;			//'유출 이상';
		OTFL_NRML = 0 ;			//'유출 정상';
		INFL_ABNR = 0;			//'유입 이상';
		INFL_NRML = 0;			//'유입 정상';
		this.OTFL_ABNR  = 0 ;			//'유출 이상';
		this.OTFL_NRML = 0 ;			//'유출 정상';
		this.INFL_ABNR = 0;			//'유입 이상';
		this.INFL_NRML = 0;			//'유입 정상';
	}
	
	public void perfectReset() {
		this.GNRL_RMND_PRZN_NUM = 0 ;
		this.OTFL_NRML = 0 ;
		this.INFL_ABNR = 0;
		this.INFL_NRML = 0;
		this.WHOLE_RMND_PRZN_NUM = 0 ;
		this.ACML_INFL_UNUM = 0 ;
		this.ACML_OTFL_UNUM = 0 ;
		this.CTLR_RMND_NUM = 0 ;
		this.CTLR_PARK_NUM = 0 ;
		this.OTFL_ABNR = 0 ;
		this.OTFL_NRML = 0 ;
		this.INFL_ABNR = 0 ;
		this.INFL_NRML = 0 ;
		this.OFST_VAL = 0 ;
	}
	
}
