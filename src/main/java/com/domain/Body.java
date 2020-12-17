package com.domain;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Body {
	
	String dataType;
	ArrayList<Item> item = new ArrayList<Item>();

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(Item i : this.item) {
			sb.append( i.toString() );
		}
		return "Data Type : "+ dataType
				+" total item : " + item.size()
				+" item List : [ " + sb.toString() + " ] ";
	}
}
