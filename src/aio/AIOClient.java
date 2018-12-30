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
			//连接成功回调
			@Override
			public void completed(Void result, Object attachment) {
				//向服务端写入数据
				client.write(ByteBuffer.wrap("Hello!".getBytes()),null,new CompletionHandler<Integer, Object>() {
					//写入完成，回调
					@Override
					public void completed(Integer result, Object attachment) {
						//准备从服务端读取数据
						final ByteBuffer buffer = ByteBuffer.allocate(1024);
						client.read(buffer, buffer,new CompletionHandler<Integer,ByteBuffer>() {
							//成功读取所有数据后的回调
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
