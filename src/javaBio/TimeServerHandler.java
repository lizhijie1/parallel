package javaBio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {
	private Socket socket;
	
	public TimeServerHandler(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader in= null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			String currentTime = null;
			String body = null;
			while(true){
				body = in.readLine();
				if(body == null){
					break;
				}
                System.out.println("The time server receive order:"+body);
                //如果请求消息为查询时间的指令"QUERY TIME ORDER"则获取当前最新的系统时间。
                currentTime="QUERY TIME ORDER".equalsIgnoreCase(body) ? 
                        new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                out.println(currentTime);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                out.close();
                out=null;
            }
            if(this.socket!=null){
                try {
                    this.socket.close();
                    this.socket=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
	}

}
