/**
 * IoServer.java	  V1.0   2018��12��5�� ����10:02:59
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package NiOrIoTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class IoServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		InputStream in = null;
		try { 
			serverSocket = new ServerSocket(5000); 
			int recvMsgSize = 0;
			byte[] recvBuf = new byte[1024]; 
			while(true){ 
				Socket clntSocket = serverSocket.accept(); 
				SocketAddress clientAddress = clntSocket.getRemoteSocketAddress(); 
				System.out.println("Handling client at "+clientAddress); 
				in = clntSocket.getInputStream(); 
				while((recvMsgSize=in.read(recvBuf))!=-1){ 
					byte[] temp = new byte[recvMsgSize]; 
					System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize); 
					System.out.println(new String(temp)); 
				} 
			} 
		} catch (IOException e) {
			e.printStackTrace();
		} finally{ 
			try{ 
				if(serverSocket!=null){ 
					serverSocket.close();
				} if(in!=null){ 
					in.close(); 
				} 
			}catch(IOException e){
				e.printStackTrace(); 
			}
		}
	}
}
