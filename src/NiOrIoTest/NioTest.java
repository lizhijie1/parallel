/**
 * NioTest.java	  V1.0   2018��12��4�� ����9:03:27
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
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 * ����������NIO
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class NioTest {
	public static void main(String[] args) {
		RandomAccessFile aFile = null;
		try{
			aFile = new RandomAccessFile("src/nomal_io.txt","rw"); 
			FileChannel fileChannel = aFile.getChannel();
			//������������Ļ�����
			ByteBuffer buf = ByteBuffer.allocate(1024);
			//д�����ݵ�buffer
			int bytesRead = fileChannel.read(buf); 
			System.out.println(bytesRead); 
			while(bytesRead != -1) {
				//position����Ϊ0
				//limit����ǰλ��
				buf.flip();
				while(buf.hasRemaining()) {
					//��buffer�л�ȡ��Ϣ
					System.out.println(buf);
					System.out.print((char)buf.get());
				} 
				//buf.compact(); 
				buf.clear();
				bytesRead = fileChannel.read(buf); 
				System.out.println(">>>>>>>>>>");
				System.out.println(bytesRead);
				System.out.println("<<<<<<<<<<");
			} 
		}catch (IOException e){ 
			e.printStackTrace(); 
		}finally{
			try{ 
				if(aFile != null){ 
					aFile.close(); 
				} 
			}catch (IOException e){
				e.printStackTrace(); 
			} 
		}
	}
}
