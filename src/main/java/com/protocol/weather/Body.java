package com.protocol.weather;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Body {
	
	String dataType;
	ArrayList<Item> items = new ArrayList<Item>();
	
	String content;

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(Item i : this.items) {
			sb.append( i.toString() );
		}
		return "Data Type : "+ dataType
				+" total item : " + items.size()
				+" item List : [ " + sb.toString() + " ] ";
	}
}
