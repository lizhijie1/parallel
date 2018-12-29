/**
 * TcpClient.java	  V1.0   2018��12��6�� ����9:19:04
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
	public static void main(String[] args) {
		try {
			Socket client = new Socket("127.0.0.1",50000);
			OutputStream os = client.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("�û�����admin�����룺123");
			pw.flush();
			client.shutdownOutput();
			 //3����ȡ������������ȡ�������˵���Ӧ��Ϣ
			 InputStream is = client.getInputStream();
			 BufferedReader br = new BufferedReader(new InputStreamReader(is));
			 String info = null;
			 while((info=br.readLine())!=null){
			  System.out.println("���ǿͻ��ˣ�������˵��"+info);
			 }
			
			 //4���ر���Դ
			 br.close();
			 is.close();
			 pw.close();
			 os.close();
			 client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
