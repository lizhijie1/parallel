/**
 * ScattingAndGather.java	  V1.0   2018年12月6日 下午5:30:18
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package NiOrIoTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScattingAndGather {
	public static void main(String[] args) {
		gather();
	}
	
	public static void gather() { 
		ByteBuffer header = ByteBuffer.allocate(10); 
		ByteBuffer body = ByteBuffer.allocate(10); 
		byte [] b1 = {'0', '1'}; 
		byte [] b2 = {'2', '3'}; 
		header.put(b1); 
		body.put(b2); 
		ByteBuffer [] buffs = {header, body}; 
		try {
			RandomAccessFile os = new RandomAccessFile("src/scattingAndGather.txt","rw"); 
			FileChannel channel = os.getChannel(); 
			channel.write(header); 
		} catch (IOException e) {
			e.printStackTrace(); 
		} 
	}
}
