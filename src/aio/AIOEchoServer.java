/**
 * AIOServer.java	  V1.0   2018年12月30日 下午7:42:03
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
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.mail.internet.InternetAddress;

import io.netty.buffer.ByteBuf;
/**
 * 
 * 功能描述：AIO实现服务端
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class AIOEchoServer {
	public final static int port = 8000;
	private AsynchronousServerSocketChannel server;
	public AIOEchoServer() throws IOException {
		server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));
	}
	public void start(){
		System.out.println("Server listen on "+port);
		//注册事件和时间完成后的处理器
		//accept方法会立即返回
		//方法签名
		//public final <A> void accept(A attachment,CompletionHandler<AsynchronousSocketChannel,? super A> handler)
		//第一个参数是一个附件，可以是任意类型，作用是让当前线程和后续的回调方法可以共享信息，他会在后续的调用中，传递给handler.
		//他的第二个参数是CompletionHandler接口。
		server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
			final ByteBuffer buffer = ByteBuffer.allocate(1024);
			//成功回调
			@Override
			public void completed(AsynchronousSocketChannel result, Object attachment) {
				System.out.println(Thread.currentThread().getName());
				Future<Integer> writeResult = null;
				try {
					buffer.clear();
					result.read(buffer).get(100,TimeUnit.SECONDS);
					buffer.flip();
					writeResult = result.write(buffer);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					e.printStackTrace();
				}finally {
					try {
						server.accept(null,this);
						writeResult.get();
						try {
							result.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
			}
			//失败回调
			@Override
			public void failed(Throwable exc, Object attachment) {
				System.out.println("failed："+exc);
			}
		});
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new AIOEchoServer().start();
		while(true){
			Thread.sleep(1000);
		}
	}
}
