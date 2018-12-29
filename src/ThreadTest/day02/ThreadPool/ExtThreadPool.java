/**
 * ExtThreadPool.java	  V1.0   2018年12月16日 下午7:01:40
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 功能描述：扩展线程池
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class ExtThreadPool {
	
	public static class MyTask implements Runnable{

		private String name;
		
		public MyTask(String name) {
			super();
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println("正在执行"+":Thread ID:"+Thread.currentThread().getId()+",Task Name="+name);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, 
		new LinkedBlockingQueue<Runnable>()){

			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				System.out.println("准备执行"+((MyTask)r).name);
			}

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				System.out.println("执行完成"+((MyTask)r).name);
			}

			@Override
			protected void terminated() {
				System.out.println("线程池退出");
			}
		};
		for (int i = 0; i < 5; i++) {
			MyTask task = new MyTask("TASK-GEYM-"+i);
			es.execute(task);
			Thread.sleep(100);
		}
		es.shutdown();
	}
}
