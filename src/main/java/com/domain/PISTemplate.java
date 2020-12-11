package com.domain;
 
import java.util.HashMap;

import com.service.PISGroup;
import com.util.ApplicationContextProvider;
import com.vo.voPARKING_DISPLAY;
import com.vo.voPRLT_CTLR;
import com.vo.voPRLT_CTLR_STTS;
import com.vo.voPRLT_RT_INFR;

import io.netty.channel.Channel;

public class PISTemplate {
	public boolean turnOnOff;

	public Channel ch;
	public voPRLT_CTLR_STTS voPRLT_stts;
	public voPRLT_RT_INFR voPRLT_infr;
	public voPRLT_CTLR voPRLT_ctlr;
	public voPARKING_DISPLAY voPARKING_display;
	
	public int count = -1;
	public String PIS_ID;
	
	public int accumulated_outCount=-1;
	public int accumulated_inCount=-1;
	
	public int changeIn;
	public int changeOut;
		
	public String FLOR_NMBR;
	
	public PISGroup pisGroup = (PISGroup) ApplicationContextProvider.getApplicationContext().getBean(PISGroup.class);
	
	public void reset() {
		this.count = 0;
		this.turnOnOff = false;
		
		if(!pisGroup.getLinkedId(PIS_ID).equals("-1")) {
			pisGroup.getListInfo( pisGroup.getLinkedId(PIS_ID) ).setEmptySlot(0);
		}
		this.changeIn = 0;
		this.changeOut = 0;
		this.accumulated_inCount = -1;
		this.accumulated_outCount = -1;
		this.voPRLT_infr.perfectReset();
	}
	
	public PISTemplate(voPRLT_CTLR voPRLT_ctlr ) {
		this.voPRLT_ctlr = voPRLT_ctlr;
		this.PIS_ID = voPRLT_ctlr.getCTLR_MNGM_NMBR();
		this.voPRLT_infr = new voPRLT_RT_INFR(voPRLT_ctlr.getCTLR_MNGM_NMBR());			
		this.voPRLT_stts = new voPRLT_CTLR_STTS(voPRLT_ctlr.getCTLR_MNGM_NMBR());
		this.voPRLT_infr = new voPRLT_RT_INFR(voPRLT_ctlr.getCTLR_MNGM_NMBR());
		this.voPARKING_display = new voPARKING_DISPLAY();
		this.FLOR_NMBR = "1";
		this.turnOnOff = false;		
		this.count = -1;
	}
	
	public PISTemplate( Channel c, voPRLT_CTLR_STTS voP ) {
		turnOnOff = false;
		this.ch = c;
		this.voPRLT_stts  = voP;
		
	}
	
	public voPRLT_CTLR getPRLT(){
		return this.voPRLT_ctlr;
	}
	public void setPRLT(voPRLT_CTLR voPRLT_ctlr){
		this.voPRLT_ctlr = voPRLT_ctlr;
	}
	
	public voPRLT_CTLR_STTS getRecentStatus() {
		return this.voPRLT_stts;
	}
	public voPRLT_CTLR_STTS getVoPRLT_stts() {
		return this.voPRLT_stts;
	}

	public void setVoPRLT_stts(voPRLT_CTLR_STTS voPRLT_stts) {
		this.voPRLT_stts = voPRLT_stts;
	}

	public voPRLT_RT_INFR getVoPRLT_infr() {
		return this.voPRLT_infr;
	}

	public void setVoPRLT_infr(voPRLT_RT_INFR voPRLT_infr) {
		this.voPRLT_infr = voPRLT_infr;
	}

	public voPRLT_CTLR getVoPRLT_ctlr() {
		return this.voPRLT_ctlr;
	}

	public void setVoPRLT_ctlr(voPRLT_CTLR voPRLT_ctlr) {
		this.voPRLT_ctlr = voPRLT_ctlr;
	}

	public String getPIS_ID() {
		return this.PIS_ID;
	}

	public void setPIS_ID(String pIS_ID) {
		this.PIS_ID = pIS_ID;
	}

	public String getFLOR_NMBR() {
		return this.FLOR_NMBR;
	}

	public void setFLOR_NMBR(String fLOR_NMBR) {
		this.FLOR_NMBR = fLOR_NMBR;
	}

	public void setRecentStatus(voPRLT_CTLR_STTS recentStatus) {
		this.voPRLT_stts = recentStatus;
	}
	public void setRecentStatus(String facID) {
		this.voPRLT_stts = new voPRLT_CTLR_STTS(facID);
	}
	
	public voPRLT_RT_INFR getVehicleStatus() {
		return this.voPRLT_infr;
	}
	public void setVehicleStatus(voPRLT_RT_INFR prltRtInfr) {
		this.voPRLT_infr = prltRtInfr;
	}
	
	public voPARKING_DISPLAY getPARKING_DISPLAY() {
		return this.voPARKING_display;
	}
	public void setPARKING_DISPAY(voPARKING_DISPLAY voPAKRING_display) {
		this.voPARKING_display = voPAKRING_display;
	}

	public Channel getCh() {
		return this.ch;
	}
	public void setCh(Channel ch) {
		this.ch = ch;
	}		
	
	public String toString() {
		return " ch : " + ch + " floor info : " + FLOR_NMBR + 
				"\n voPRLT_CTLR_STTS : "+ voPRLT_stts.toString() + 
				"\n voPRLT_RT : " + voPRLT_infr.toString() + 
				"\n voPRLT_CTLR : " + voPRLT_ctlr.toString() +
				"\n count : " + count;
	}
}
