package com.domain;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Separate_part {

	Stack<Integer> stack = new Stack<Integer>();
	
	String[] arg;
	
	public Separate_part() {
		
	}

	public String[] separate(String target) {
		String[] stacks;
		Queue<String> strQ = new LinkedList<String>();
	
		for(int i = 0; i<target.length(); i++) {
			if( target.charAt(i)== '{') 
			{
				stack.push(i);
			}
			if( target.charAt(i)== '}') 
			{
				int start = stack.pop();
				int end = i;
				strQ.offer(target.substring(start+1, end));
			}
			
		}

		stacks = new String[strQ.size()];
		
		for(int i=0; i < stacks.length ; i++) {
			stacks[i] = strQ.poll();
		}
		
		return stacks;
	}
	
	
	
}
