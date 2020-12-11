package com.util;

public class SequenceGenerator {
	
	private Integer sequenceNumber =1;
	
	public SequenceGenerator() { sequenceNumber = 1; }
	public SequenceGenerator(int startNumber) { sequenceNumber = startNumber; }  
	
	public int nextValue() {
		synchronized (sequenceNumber) {
			if (sequenceNumber == Integer.MAX_VALUE) sequenceNumber = 0; 
			else 									 ++sequenceNumber;
		}
		return sequenceNumber;
	}
	
	public int currentValue() {
		return sequenceNumber;
	}
}
