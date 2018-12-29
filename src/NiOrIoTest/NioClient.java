/**
 * NioClient.java	  V1.0   2018年12月5日 上午9:59:04
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package NiOrIoTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class NioClient {
	public static void main(String[] args) throws IOException {
		 ByteBuffer buffer = ByteBuffer.allocate(1024); 
		 SocketChannel socketChannel = null; 
		 try { 
			 socketChannel = SocketChannel.open(); 
			 socketChannel.configureBlocking(false); 
			 socketChannel.connect(new InetSocketAddress("127.0.0.1",5000)); 
			 if(socketChannel.finishConnect()) { 
				 int i=0; 
				 while(true) { 
					 TimeUnit.SECONDS.sleep(1); 
					 String info = "I'm "+i+++"-th information from client"; 
					 buffer.clear(); 
					 buffer.put(info.getBytes()); 
					 buffer.flip(); 
					 while(buffer.hasRemaining()){ 
						 System.out.println(buffer); 
						 socketChannel.write(buffer); 
					} 
				} 
			} 
		} catch (IOException | InterruptedException e) {
			e.printStackTrace(); 
		} finally{ 
			try{ 
				if(socketChannel!=null){ 
					socketChannel.close(); 
				} 
			}catch(IOException e){ 
				e.printStackTrace(); 
			} 
		}
	} 
}
