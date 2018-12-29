/**
 * SemapDemo.java	  V1.0   2018年12月15日 下午5:42:38
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemapDemo implements Runnable{

	final Semaphore semp = new Semaphore(5);
	
	@Override
	public void run() {
		try {
			//获取许可
			semp.acquire();
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getId()+":done!");
			//释放许可
			semp.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		 ExecutorService exec= Executors.newFixedThreadPool(20);
		 final SemapDemo demo = new SemapDemo();
		 for (int i = 0; i < 20; i++) {
			exec.submit(demo);
		}
	}
}
