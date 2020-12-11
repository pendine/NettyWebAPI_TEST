package com.vo;

import com.util.DateHelper;

public class voPARKING_DISPLAY {
	
	String PARKING_CD;
	String DISPLAY_CD;
	String DISPLAY_NM;
	String MAP_X;
	String MAP_Y;
	String ADDR_1;
	String USE_YN;
	String DEL_YN;
	
	public voPARKING_DISPLAY() {}
	
	public voPARKING_DISPLAY(String Parking_cd) {
		this.PARKING_CD = Parking_cd;
	}
	
	public String getPARKING_CD() {
		return PARKING_CD;
	}

	public void setPARKING_CD(String pARKING_CD) {
		PARKING_CD = pARKING_CD;
	}

	public String getDISPLAY_CD() {
		return DISPLAY_CD;
	}

	public void setDISPLAY_CD(String dISPLAY_CD) {
		DISPLAY_CD = dISPLAY_CD;
	}

	public String getDISPLAY_NM() {
		return DISPLAY_NM;
	}

	public void setDISPLAY_NM(String dISPLAY_NM) {
		DISPLAY_NM = dISPLAY_NM;
	}

	public String getMAP_X() {
		return MAP_X;
	}

	public void setMAP_X(String mAP_X) {
		MAP_X = mAP_X;
	}

	public String getMAP_Y() {
		return MAP_Y;
	}

	public void setMAP_Y(String mAP_Y) {
		MAP_Y = mAP_Y;
	}

	public String getADDR_1() {
		return ADDR_1;
	}

	public void setADDR_1(String aDDR_1) {
		ADDR_1 = aDDR_1;
	}

	public String getUSE_YN() {
		return USE_YN;
	}

	public void setUSE_YN(String uSE_YN) {
		USE_YN = uSE_YN;
	}

	public String getDEL_YN() {
		return DEL_YN;
	}

	public void setDEL_YN(String dEL_YN) {
		DEL_YN = dEL_YN;
	}

	public String toString() {
		return " voPARKING_DISPALY | PARKING_CD : " + PARKING_CD
		+ " | DISPLAY_CD : " + DISPLAY_CD
		+ " | DISPLAY_NM : " + DISPLAY_NM
		+ " | MAP_X : " + MAP_X 
		+ " | MAP_Y : " + MAP_Y
		+ " | ADDR_1 : " + ADDR_1
		+ " | USE_YN : " + USE_YN
		+ " | DEL_YN : " + DEL_YN;
	}
	
}