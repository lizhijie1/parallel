/**
 * BadSuspend.java	  V1.0   2018年12月14日 下午11:39:41
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day01;

public class BadSuspend {
	public static Object object = new Object();
	static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	static ChangeObjectThread t2 = new ChangeObjectThread("t2");
	public static class ChangeObjectThread extends Thread{
		public ChangeObjectThread(String name){
			super.setName(name);
		}
		
		@Override
		public void run() {
			synchronized (object) {
				System.out.println(System.currentTimeMillis()+": in :"+getName());
				Thread.currentThread().suspend();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(2000);
		t2.start();
		t1.resume();
		t2.resume();
		t1.join();
		t2.join();
	}
}
