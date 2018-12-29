/**
 * ReenterLock.java	  V1.0   2018��12��15�� ����11:20:29
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02;

import java.util.concurrent.locks.ReentrantLock;
/**
 * 
 * ����������������
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class ReenterLock implements Runnable{

	public static ReentrantLock lock = new ReentrantLock();
	
	public static int i = 0;
	
	@Override
	public void run() {
		for (int j = 0; j < 10000000; j++) {
			lock.lock();
			lock.lock();
			try {
				i++;
			} finally {
				// TODO: handle finally clause
				lock.unlock();
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReenterLock tl = new ReenterLock();
		Thread t1 = new Thread(tl);
		Thread t2 = new Thread(tl);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
