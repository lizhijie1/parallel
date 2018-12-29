/**
 * FairLock.java	  V1.0   2018��12��15�� ����5:13:04
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
 * ������������ƽ��
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class FairLock implements Runnable{
	//
	public static ReentrantLock fairLock = new ReentrantLock(true);
	
	@Override
	public void run() {
		while(true){
			try {
				fairLock.lock();
				System.out.println(Thread.currentThread().getName()+"�������");
			} finally {
				// TODO: handle finally clause
			}
			fairLock.unlock();
		}
	}

	public static void main(String[] args) {
		FairLock r1 = new FairLock();
		Thread t1 = new Thread(r1,"Thread-T1");
		Thread t2 = new Thread(r1,"Thread-T2");
		t1.start();
		t2.start();
	}
}
