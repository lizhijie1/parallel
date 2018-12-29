/**
 * UnsafeArrayList.java	  V1.0   2018年12月25日 上午10:32:49
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day07;

import java.util.ArrayList;

public class UnsafeArrayList {
	static ArrayList<Object> al = new ArrayList<Object>();
	static class AddTask implements Runnable{
		@Override
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			for (int i = 0; i < 1000000; i++) {
				al.add(new Object());
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new AddTask());
		Thread t2 = new Thread(new AddTask());
		t1.start();
		t2.start();
		Thread t3 = new Thread(new Runnable(){
			@Override
			public void run() {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
			}
		},"t3");
		t3.start();
	}
}
