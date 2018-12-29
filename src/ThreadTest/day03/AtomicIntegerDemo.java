/**
 * AtomicIntegerDemo.java	  V1.0   2018��12��17�� ����8:09:51
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day03;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 
 * ����������atomicInteger
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class AtomicIntegerDemo {
	static AtomicInteger i = new AtomicInteger();
	
	public static class AddThread implements Runnable{

		@Override
		public void run() {
			for (int k = 0; k < 10000; k++) {
				i.incrementAndGet();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Thread[] ts = new Thread[10];
		for (int k = 0; k < 10; k++) {
			ts[k] = new Thread(new AddThread());
		}
		for (int k = 0; k < 10; k++) {
			ts[k].start();
		}
		for (int k = 0; k < 10; k++) {
			ts[k].join();
		}
		System.out.println(i);
	}
}
