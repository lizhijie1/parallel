/**
 * NioTest.java	  V1.0   2018年12月4日 上午9:03:27
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
 * 功能描述：NIO
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class NioTest {
	public static void main(String[] args) {
		RandomAccessFile aFile = null;
		try{
			aFile = new RandomAccessFile("src/nomal_io.txt","rw"); 
			FileChannel fileChannel = aFile.getChannel();
			//创建多大容量的缓冲区
			ByteBuffer buf = ByteBuffer.allocate(1024);
			//写入数据到buffer
			int bytesRead = fileChannel.read(buf); 
			System.out.println(bytesRead); 
			while(bytesRead != -1) {
				//position设置为0
				//limit到当前位置
				buf.flip();
				while(buf.hasRemaining()) {
					//从buffer中获取信息
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
