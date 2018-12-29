/**
 * ScheduleExecutorServiceDemo.java	  V1.0   2018年12月15日 下午11:00:02
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorServiceDemo {
	public static void main(String[] args) {
		 ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
		 //如果前面的任务没有完成，则调度不会启动
		 ses.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println(System.currentTimeMillis()/1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 0, 2, TimeUnit.SECONDS);
		 /*ses.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.err.println(System.currentTimeMillis()/1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}, 0, 2, TimeUnit.SECONDS);*/
	}
}
