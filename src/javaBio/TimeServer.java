package javaBio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞式i/o创建的TimeServer
 * @author Administrator
 *
 */

public class TimeServer {
	public static void main(String[] args) {
		int port = 9090;
		if(args!=null&&args.length>0){
			try {
				port = Integer.valueOf(args[0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port:"+port);
			Socket socket = null;
			while(true){//通过无限循环监听客户端的连接
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(server != null){
				System.out.println("The time server close");
                try {
                    server.close();
                    server=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
			}
		}
	}
	
}
