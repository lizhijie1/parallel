/**
 * IoTest.java	  V1.0   2018年12月4日 上午8:56:00
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package NiOrIoTest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IoTest {
	public static void main(String[] args) {
		InputStream in = null;
		try{ 
			in = new BufferedInputStream(new FileInputStream("src/nomal_io.txt")); 
			byte [] buf = new byte[1024]; 
			int bytesRead = in.read(buf); 
			System.out.println(bytesRead);
			System.out.println("--------------------");
			while(bytesRead != -1) { 
				for(int i=0;i<bytesRead;i++) 
					System.out.println((char)buf[i]); 
					bytesRead = in.read(buf); 
					}
			}catch (IOException e) {
				e.printStackTrace(); 
			}finally{
				try{ 
					if(in != null){
						in.close(); 
					} 
				}catch (IOException e){
					e.printStackTrace(); 
				} 
		}
	}
}
