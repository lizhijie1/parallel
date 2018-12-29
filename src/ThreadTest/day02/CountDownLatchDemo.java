/**
 * CountDownLatchDemo.java	  V1.0   2018年12月15日 下午8:50:47
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 功能描述：倒计时器
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class CountDownLatchDemo implements Runnable{
	public static CountDownLatchDemo demo = new CountDownLatchDemo();
	public static CountDownLatch end = new CountDownLatch(10);

	@Override
	public void run() {
		try {
			//模拟检查任务
			Thread.sleep(new Random().nextInt(10)*1000);
			System.out.println(Thread.currentThread().getName()+":check complete");
			end.countDown();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			exec.submit(demo);
		}
		//等待检查
		end.await();
		//发射火箭
		System.out.println("Fire!");
		exec.shutdown();
	}
}
