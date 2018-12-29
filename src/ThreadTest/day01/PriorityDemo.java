/**
 * PriorityDemo.java	  V1.0   2018年12月15日 上午10:11:49
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day01;

public class PriorityDemo {
	public static class HighPriority extends Thread{
		static int count = 0;
		@Override
		public void run() {
			while(true){
				synchronized (PriorityDemo.class) {
					count++;
					if(count>10000000){
						System.out.println("HighPriority is complete");
						break;
					}
				}
			}
		}
	}
	
	public static class LowPriority extends Thread{
		static int count = 0;
		@Override
		public void run() {
			while(true){
				synchronized (PriorityDemo.class) {
					count++;
					if(count>10000000){
						System.out.println("LowPriority is complete");
						break;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Thread high = new HighPriority();
		Thread low = new LowPriority();
		high.setPriority(Thread.MAX_PRIORITY);
		low.setPriority(Thread.MIN_PRIORITY);
		low.start();
		high.start();
	}
}
