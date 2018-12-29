/**
 * TcpServer.java	  V1.0   2018年12月6日 上午9:04:46
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) {
		try {
			
			ServerSocket server = new ServerSocket(50000);
			
			Socket socket = server.accept();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String info = null;
			
			while((info = reader.readLine())!=null){
				System.out.println("我是服务端，客户端对我说："+info);
			}
			
			socket.shutdownInput();
			
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("欢迎您！");
			pw.flush();
			
			//关闭资源
			pw.close();
			os.close();
			reader.close();
			socket.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
