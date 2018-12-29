/**
 * NoVisibility.java	  V1.0   2018年12月15日 上午9:28:36
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day01;

public class NoVisibility {
	private  static volatile boolean ready;
	private  static volatile int number;
	
	private static class ReaderThread extends Thread{
		@Override
		public void run() {
			while(!ready);
			System.out.println(System.currentTimeMillis()+":"+number);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new ReaderThread().start();
		System.out.println(System.currentTimeMillis());
		Thread.sleep(1000);
		number = 42;
		ready = true;
		Thread.sleep(10000);
	}
}
