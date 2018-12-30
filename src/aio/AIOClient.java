/**
 * AIOClient.java	  V1.0   2018年12月30日 下午8:16:06
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.NetworkChannel;
import java.util.concurrent.CompletableFuture;

public class AIOClient {
	public static void main(String[] args) throws IOException, InterruptedException {
		final AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
		client.connect(new InetSocketAddress("localhost",8000),null,new CompletionHandler<Void,Object>() {

			@Override
			public void completed(Void result, Object attachment) {
				client.write(ByteBuffer.wrap("Hello!".getBytes()),null,new CompletionHandler<Integer, Object>() {
					@Override
					public void completed(Integer result, Object attachment) {
						final ByteBuffer buffer = ByteBuffer.allocate(1024);
						client.read(buffer, buffer,new CompletionHandler<Integer,ByteBuffer>() {
							@Override
							public void completed(Integer result, ByteBuffer attachment) {
								buffer.flip();
								System.out.println(new String(buffer.array()));
								buffer.clear();
							}

							@Override
							public void failed(Throwable exc, ByteBuffer attachment) {
							}
						
						});
					}
					@Override
					public void failed(Throwable exc, Object attachment) {
					}
				});
			}
			@Override
			public void failed(Throwable exc, Object attachment) {
			}
		});
		//主线程立即结束，这里等待上述处理全部完成
		Thread.sleep(1000);
	}
}
