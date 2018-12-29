/**
 * ThreadPoolDemo.java	  V1.0   2018年12月15日 下午10:43:18
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * 功能描述：固定大小的线程池
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class ThreadPoolDemo {
	public static class MyTask implements Runnable{
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis()+":Thread ID:"+Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public static void main(String[] args) {
		MyTask task = new MyTask();
		ExecutorService es= Executors.newFixedThreadPool(5);
		//ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			es.submit(task);
		}
	}
}
