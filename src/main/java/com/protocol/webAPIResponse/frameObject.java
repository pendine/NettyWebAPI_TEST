package com.protocol.webAPIResponse;

import java.util.ArrayList;

public class frameObject {
	int id;
	String type;
	String stat;
	double[] box = new double[4];
	double speed;
	double[] chtg;
	int evtN;
	ArrayList<ObjectEvent> evts = new ArrayList<ObjectEvent>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
	public double[] getBox() {
		return box;
	}
	public void setBox(double[] box) {
		this.box = box;
	}
	public void setBoxElement(double elements, int index) {
		this.box[index] = elements;
	}
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double[] getChtg() {
		return chtg;
	}
	public void setChtg(double[] chtg) {
		this.chtg = chtg;
	}
	
	public int getEvtN() {
		return evtN;
	}
	public void setEvtN(int evtN) {
		this.evtN = evtN;
	}
	
	public ArrayList<ObjectEvent> getEvts() {
		return evts;
	}
	public void setEvts(ArrayList<ObjectEvent> evts) {
		this.evts = evts;
	}
	public void setObjectEvent(ObjectEvent objEvnt) {
		this.evts.add(objEvnt);
	}
	
	
}
