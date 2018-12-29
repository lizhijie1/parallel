package JavaNio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimerServer implements Runnable {
	private Selector selector;//多路复用器
	private ServerSocketChannel serverChannel;//
	private volatile boolean stop;
	
	/**
	 * @Title: MultiplexerTimeServer
	 * @Description: TODO(初始化多路复用器，绑定监听端口)
	 * @return void    返回类型
	 * @throws
	 */
	public MultiplexerTimerServer(int port){
		try {
			//1.打开选择器
			selector = Selector.open();
			//2.打开服务器套接字通道
			serverChannel = ServerSocketChannel.open();
			//3.设置阻塞模式
			serverChannel.configureBlocking(false);
			//4. 获取与此通道关联的服务器套接字
			serverChannel.socket().bind(new InetSocketAddress(port),1024);
			//5. 向给定的选择器注册此通道
			serverChannel.register(selector,SelectionKey.OP_ACCEPT);
			System.out.println("The time server is start in port:"+port);
		} catch (Exception e) {
			e.printStackTrace();
            System.exit(1);
		}
	}
	public void stop(){
		this.stop = true;
	}
	@Override
	public void run() {
		while(!stop){
			try {
				 //1.设置selector的休眠时间为1s，无论是否有读写等事件发生，selector每隔1s都被唤醒一次
				 selector.select(1000);
				 //2.当有就绪状态的channel时，selector就返回就绪状态的channel的SelectionKey集合
				 Set<SelectionKey> selectedKeys = selector.selectedKeys();
				 //3.迭代
				 Iterator it = selectedKeys.iterator();
				 SelectionKey key = null;
				 if(it.hasNext()){
					 key = (SelectionKey) it.next();
					 it.remove();
				 }
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		/**
		 * 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动注册并关闭，所以不需要重复释放资源
		 */
		if(selector!=null){
			try {
				selector.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	private void handleInput(SelectionKey key) throws IOException{
		//如果此键有效
		if(key.isValid()){
			//处理新接入的请求消息
			//通过SelectionKey的操作符进行判断即可获知网络事件类型
			if(key.isAcceptable()){
				//Accept the new connection
				//返回此键的通道
				ServerSocketChannel ssc=(ServerSocketChannel) key.channel();
				
				SocketChannel sc = ssc.accept();
				
				sc.configureBlocking(false);
				
				sc.register(selector, SelectionKey.OP_READ);
			}
			if(key.isReadable()){
				SocketChannel sc=(SocketChannel) key.channel();
				//1.开辟1k的缓冲区
				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				//2.
				int readBytes = sc.read(byteBuffer);
				
				if(readBytes>0){
					//1.反转此缓冲区
					byteBuffer.flip();
					//2.byteBuffer.remaining()返回缓冲区剩余元素数
					//根据缓冲区可读的字节个数创建字节数组
					byte[] bytes = new byte[byteBuffer.remaining()];
					//3.调用ByteBuffer的get操作将缓冲区可读的字节数组复制到新创建的字节数组中
					byteBuffer.get(bytes);
					String body = new String(bytes,"UTF-8");
                    System.out.println("The time server receive order: "+body);
                    //如果请求指令是"QUERY TIME ORDER"则把服务器的当前时间编码后返回给客户端
                    String currentTime="QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(
                            System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
				}else if(readBytes<0){
					//
					key.cancel();
					sc.close();
				}else{
					//读到0字节
				}
			}
		}
	}
	
	private void doWrite(SocketChannel channel,String response) throws IOException{
        if(response!=null&& response.trim().length()>0){
            byte[] bytes=response.getBytes();
            ByteBuffer writeBuffer=ByteBuffer.allocate(bytes.length);
            //调用ByteBuffer的put操作将字节数组复制到缓冲区
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
            
            /*
             * 需要指出的是，由于SocketChannel是异步非阻塞的，它并不保证一次性能够把需要发送的字节数组发送完，
             * 此时会出现“写半包”问题，我们需要注册写操作，不断轮询Selector，将没有发送完毕的ByteBuffer发送完毕，
             * 可以通过ByteBuffer的hasRemaining()方法判断消息是否发送完成。
             * 此处仅仅是各简单的入门级例程，没有演示如何处理“写半包”场景，后面会说到。
             */
        }
    }
}


























