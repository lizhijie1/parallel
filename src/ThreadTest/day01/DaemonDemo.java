/**
 * DaemonDemo.java	  V1.0   2018年12月15日 上午9:51:16
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day01;

public class DaemonDemo {
	public static class DaemonT extends Thread{
		@Override
		public void run() {
			while(true){
				System.out.println("I am alive");
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		DaemonT t = new DaemonT();
		//先设置守护线程，然后start
		t.setDaemon(true);
		t.start();
		Thread.sleep(2000);
	}
}
