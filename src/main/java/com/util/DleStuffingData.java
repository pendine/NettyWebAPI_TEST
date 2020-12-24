package com.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author : Kim Ki Hwan
 * @Date   : 2020. 6. 11.
 * DEL Stuffing 작업 
 */
public class DleStuffingData {
	
	//  DEL Stuffing insert when Encoding (Packet send) 
	public static byte[] InsertStufData(int len, byte[] sRcv, int tailSize)
	{
		int i, max, total, newCount;
		byte[] sNew = new byte[1024];
		byte[] r_sNew; // 새롭게 동적할당하여 리턴할 배

		newCount = 0;
		// 헤더 부분 복사(HEADER)
		max = 2; // DLE + STX
		
		for (i=0; i<max; i++) // 새로운 배열에 복사
		{
			sNew[i] = sRcv[i];
			newCount++;
		}
		// 헤더(DLE,STX)와 Tail을 제외한 데이터부분에 DLE가 있을 경우 DLE를 추가함
		total = len - tailSize;
		int j = max;
		for (i=max; i<total; i++) // Addr부터 검사하므로 2부터
		{
			sNew[j] = sRcv[i];
			newCount++;
			if (sRcv[i] == (byte)0x10)
			{
				sNew[++j] = (byte)0x10;
				newCount++;
				j++;
				continue;
			}
			j++;
		}
		// Tail 부분 복사(TAIL, CRC)
		for (i=total; i<len; i++)
		{
			sNew[j] = sRcv[i];
			newCount++;
			j++;
		}
		r_sNew = Arrays.copyOf(sNew, newCount); // new_len 길이만큼 새로운 동적배열에 복사
		return r_sNew; // DLE Stuffing을 한 데이터 리턴
	}

	// DLE Stuffing delete when Decoding (Packet receive)
	// Stuffing Data를 제거할 때
	public static byte[] RemoveStufData(int len, byte[] Rcv)
	{
		int i, j, new_len, dleCount;
		
		// 배열 복사
		byte[] sRcv = new byte[len];
		sRcv = Arrays.copyOf(Rcv, len);
		
		byte[] sNew = new byte[1024]; //
		byte[] r_sNew; // 새롭게 동적할당하여 리턴할 배열
		
		dleCount = 0;
		new_len = 0;
		j = 0;
		
		for (i=0; i<len; i++)
		{
			if (j == len) // 기존 rcv msg의 index가 모든 메시지길이를 다 봤으면 종료
				break;
			if (sRcv[j] == 0x10) // 지금 값이 DLE이라면
			{
				dleCount++; // 개수 증가
				if (dleCount == 2) // DLE를 2번 만났다면
				{
					j++;
					dleCount = 0; // DLE Stuffing값이므로 개수 초기화
				}
			}
			else
				dleCount = 0;

			sNew[i] = sRcv[j];
			j++;
			new_len++;
		}
		
		r_sNew = Arrays.copyOf(sNew, new_len); // new_len 길이만큼 새로운 동적배열에 복사
		return r_sNew; // Remove Stuffing한 데이터 리턴
	}
	
	
	
	public static byte[] dleStuffing( byte dle, byte start, byte end, byte[] target) {
		ArrayList<Byte> array = new ArrayList<Byte>();
		
		int startPoint = -1;
		for(int i=0; i<target.length ; i++) 
		{
			if( (target[i] == dle) && (target[i+1] == start) )
			{
				// 어느지점에서 잘라줄건지가 중요한데 여기서는 start 바이트의 시작 부분 앞까지 잘라버림.
				System.out.println("dle stuffing 시작부분(i) :" + i);
				System.out.println("target[i] : " + target[i] + " target[i+1] : " + target[i+1] );
				startPoint = i;
				break;
			}
		}
		
		if(startPoint < 0 || startPoint == target.length-1) 
		{
			// 만약에 시작부분이 없거나 뒤에있다면
			// 예외처리 또는 종료
			System.out.println("startPoint : " + startPoint );
		}
		
		for(int i=startPoint; i<target.length ; i++) {
			if( (target[i] == dle) && (target[i+1] == dle) ) 
			{
				array.add(target[i]);
				System.out.println("dle stuffing 요소 제거");
				i = i + 1;
				continue;
			}
			else
			{
				System.out.println("요소 추가");
				array.add(target[i]);
			}
			
			if( (target[i] == dle) && (target[i+1] == end) ) 
			{
				System.out.println("dle stuffing 종료부분(i+1) : " + (i+1));
				System.out.println("");
				array.add(target[i+1]);
				break;
			}
		}
		
		System.out.println("새로 생성될 바이트 배열 길이 : "+array.size());

		byte[] returnArr = new byte[array.size()];
		
		
		for(int i =0; i<returnArr.length; i++) {
			returnArr[i] = array.get(i);
		}
		
		array = null;
		return returnArr;
	}
	
}
