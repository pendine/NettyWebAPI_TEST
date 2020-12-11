package com.domain;

import java.util.ArrayList;
import java.util.List;

import com.service.PISManager;
import com.util.ApplicationContextProvider;

public class ListPIS {
	PISManager pisManager = (PISManager) ApplicationContextProvider.getApplicationContext().getBean("pisManager");
	
	List<String> a = new ArrayList<>();
	
	int emptySlot=0; //그룹핑된 pis간 사용할 잔여면수이면서 이건 동기화가 필요함 

	
	public void add(String PisID) {
		a.add(PisID);
		
//		this.emptySlot = this.emptySlot / a.size(); //초기 로딩시 저장된 정보가 다른상태일경우. 
	}
	
	public void initEmpty() {
//		int size = a.size();
//		
//		for(int i=0; i<size; i++) {
//			this.emptySlot += pisManager.getPISTemplate(a.get(i)).getVehicleStatus().getWHOLE_RMND_PRZN_NUM();
//		}
//		this.emptySlot = this.emptySlot / size;
		this.emptySlot = 0;
	}
	
	public String[] getRelationPIS() {
		String[] List = new String[a.size()];
		for(int i=0; i<a.size(); i++) {
			List[i] = a.get(i);
		}
		return List;
	}
	
	/**
	 * 원하는 숫자로 초기화.
	 * 운영단말의 opcode가 주차 가능면수 초기화가 아닌이상 사용하지않음
	 * */
	public synchronized void setEmptySlot(int a) {
		this.emptySlot = a;
	}
	
	/**
	 * 제어기 그룹의 잔여면수 획득
	 * */
	public synchronized int getEmptySlot() {
		return this.emptySlot;
	}
	
	/**
	 * 제어기 그룹의 잔여면수에 대한 증감연산.
	 * */
	public synchronized void addEmptySlot(int a) {
		this.emptySlot += a;
	}
}
