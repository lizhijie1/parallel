/**
 * TransferFrom.java	  V1.0   2018年12月7日 上午10:58:29
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package NiOrIoTest;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferFrom {
	public static void main(String[] args) {
		RandomAccessFile fromFile = null;
		RandomAccessFile toFile = null;
		try { 
			fromFile = new RandomAccessFile("src/fromFile.txt","rw"); 
			FileChannel fromChannel = fromFile.getChannel(); 
			toFile = new RandomAccessFile("src/toFile.txt","rw"); 
			FileChannel toChannel = toFile.getChannel(); 
			long position = 0; 
			long count = fromChannel.size();
			System.out.println(count); 
			toChannel.transferFrom(fromChannel, position, count);
		} catch (IOException e) { 
			e.printStackTrace(); 
		} finally{ 
			try{ 
				if(fromFile != null){ 
					fromFile.close(); 
				} if(toFile != null){ 
					toFile.close(); 
				} 
			} catch(IOException e){ 
				e.printStackTrace(); 
			} 
		}
	}
}
