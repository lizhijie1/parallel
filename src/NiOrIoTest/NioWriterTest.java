/**
 * NioWriterTest.java	  V1.0   2018年12月7日 下午3:09:38
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package NiOrIoTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioWriterTest {
	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("src/scattingAndGather.txt","rw");
		FileChannel channel = file.getChannel();
		for (int i = 0; i < 10; i++) {
			String dataInfo = "New String to write to file..."+System.currentTimeMillis();
			ByteBuffer buf = ByteBuffer.allocate(48);
			buf.clear();
			buf.put(dataInfo.getBytes());
			buf.flip();
			while(buf.hasRemaining()) {
				channel.write(buf);
			}
		}
	}
}
