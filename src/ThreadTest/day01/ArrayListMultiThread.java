/**
 * ArrayListMultiThread.java	  V1.0   2018年12月15日 上午10:35:23
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day01;

import java.util.ArrayList;
import java.util.Vector;

public class ArrayListMultiThread {
	static ArrayList<Integer> al = new ArrayList<Integer>(10);
	//static Vector al = new Vector();
	
	public static synchronized void increase(int i){
		al.add(i);
	}
	public static class AddThread implements Runnable{

		@Override
		public void run() {
			for (int i = 0; i < 10000000; i++) {
				increase(i);
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AddThread());
		Thread t2 = new Thread(new AddThread());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(al.size());
	}
}
