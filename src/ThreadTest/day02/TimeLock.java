/**
 * TimeLock.java	  V1.0   2018年12月15日 下午4:36:16
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 功能描述：限时等待锁
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class TimeLock implements Runnable{
	
	//重入锁
	private static ReentrantLock lock = new ReentrantLock();
	@Override
	public void run() {
		try {
			//锁的限时等待
			if(lock.tryLock(5, TimeUnit.SECONDS)){
				Thread.sleep(6000);
			}else{
				System.out.println("get lock failed");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if(lock.isHeldByCurrentThread()){
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		TimeLock r1 = new TimeLock();
		TimeLock r2 = new TimeLock();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
}
