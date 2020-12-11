package com.protocol;

import java.nio.ByteOrder;

/**
 * @brief 통신객체 인터페이스
 */

public interface Message {
	
	/**
	 * @brief 클래스 속성 값을 메시지 형식에 맞게 byte[]로 복사 후 리턴
	 * @return 메시지 byte 배열
	 */
	public byte[] array(ByteOrder order);

	/**
	 * @brief byte[] 값을 클래스 속성에 설정
	 * @param array : byte배열
	 */
	public void parse(byte[] array, ByteOrder order);
	
	/**
	 * @brief byte[] 값을 클래스 속성에 설정
	 * @param array : byte배열
	 * default = byteOrder = bigEndian
	 */
	public void parse(byte[] array);

	
	/**
	 * @brief 메시지 길이 값 리턴
	 * @return 메시지 길이
	 */
	public int size();
	

	
	
}
