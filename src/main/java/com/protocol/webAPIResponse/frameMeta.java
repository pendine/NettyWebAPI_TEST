package com.protocol.webAPIResponse;

import java.awt.List;
import java.util.ArrayList;

public class frameMeta {
	int uid;
	String tm;
	int objN;
	ArrayList<frameObject> objs = new ArrayList<frameObject>();
	
	public void setUid(int uid) 
	{
		this.uid = uid;
	}
	public int getUid() 
	{
		return this.uid;
	}
	
	public void setTm(String tm) 
	{
		this.tm = tm;
	}
	public String getTm() 
	{
		return this.tm;
	}
	
	public void setObjN(int objN) 
	{
		this.objN = objN;
	}
	public int getObjN() 
	{
		return this.objN;
	}
	
	public void putObj(frameObject obj) 
	{
		this.objs.add(obj);
	}
	public ArrayList<frameObject> getObjs() 
	{
		return objs;
	}
	public void setObjs(ArrayList<frameObject> objs) 
	{
		this.objs = objs;
	}
	public frameObject getObj(int index) 
	{
		return this.objs.get(index);
	}
	
}
