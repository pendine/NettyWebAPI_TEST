package com.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.domain.ListPIS;

@Service("pisGroup")

/**
 * 하나의 주차장에서 여러개의 제어기를 사용할 때의 클래스이면서
 * 사용할때는 HASH MAP에 들어갈 키값은 주차장을 특정할 별칭이어야함 예 = "시청",제어기아이디
 * */
public class PISGroup {
	private static final Logger logger = LoggerFactory.getLogger(PISGroup.class);

	//2개 뿐이라면 단순히 id값을 hashmap 의 key values를 사용할 수 잇지만
	HashMap<String, ListPIS> linkedPis = new HashMap<String, ListPIS>();

//	ListPIS listPis;
	
	
	public ListPIS getListInfo(String pisId) {
		return linkedPis.get(pisId);
	}
	
	public void init() {
		for(Entry<String, ListPIS> a : linkedPis.entrySet()) {
			a.getValue().initEmpty();
		}
	}
	
	/**
	 * 예시 시장에서 사용하고있는 제어기 아이디 3,4를 연결하고싶으면
	 * PISGroup.linkAddPIS("시장", "3");
	 * PISGroup.linkAddPIS("시장", "4");
	 * 를 사용하면됨.
	 * 연결시 주의. 주차장에 설치된 다수의 제어기중 하나가 HashMap의 키값을 사용해야함. 
	 * */
	public void linkAddPIS(String groupName, String pis2) {
		
		if(!linkedPis.containsKey(groupName)) {
			this.linkedPis.put( groupName, new ListPIS() );
		}
		
//		System.out.println(" group name : " + groupName + " is added pis id : " + pis2);
		logger.info(" group name : {}  is added pis id : {} "  ,groupName ,  pis2);
		linkedPis.get(groupName).add(pis2);;  // 해당 pis에 대해서 리스트 생성후 연관된 제어기 추가.
	}
	
	/**
	 * 예시 시장에 제어기 3,4가 연결되어있음
	 * 시장에 연결되어있는 제어기들을 원한다면
	 * String[] tmp = getListPis("시장");
	 * */	
	public String[] getListPisId(String pis) {
		String[] returnStr = null;
		if(linkedPis.containsKey(pis)) {
			returnStr = linkedPis.get(pis).getRelationPIS();
		}
		
		return returnStr;
	}
	
	
	
	/**
	 * pis id = 3 인 제어기가 어느 장소에 그룹핑된 제어기인지 확인용
	 * return String 제어기 소속장소
	 * 연결된 장소가 없다면 
	 * return String "-1"
	 * 
	 * 활용 예시 3, 4, 5가 연결되어있음
	 * 제어기 아이디 5가 어딘가에 연결되어있는지와 동시에
	 * 해당 아이디와 연결된 제어기 호출방법
	 * 
	 *  String [] tmp = PISGroup.getListPIS( PISGroup.getLinkedId("5") );
	 * */
	
	//주차장 장소와 연관된 제어기가 있는지 확인용
	public String getLinkedId(String pisId) {
		
		for( Map.Entry<String, ListPIS> a : this.linkedPis.entrySet() ) {
			
			String[] compare = a.getValue().getRelationPIS();
			for(String pis : compare ) {
				if( pisId.equals(pis) ) {
					return a.getKey();
				}
			}
//	        Arrays.stream(compare).anyMatch(pisId::equals);
//			Soruce : https://ethankang.tistory.com/28
		}
		
		return "-1";
	}

}
