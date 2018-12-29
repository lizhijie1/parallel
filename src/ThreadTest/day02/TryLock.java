/**
 * TryLock.java	  V1.0   2018年12月15日 下午4:48:18
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02;

import java.util.concurrent.locks.ReentrantLock;

public class TryLock implements Runnable{
	
	public static ReentrantLock lock1 = new ReentrantLock();
	
	public static ReentrantLock lock2 = new ReentrantLock();
	
	int lock;
	
	public TryLock(int lock) {
		super();
		this.lock = lock;
	}


	@Override
	public void run() {
		if (lock == 1) {
			while(true){
				if(lock1.tryLock()){
					try {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(lock2.tryLock()){
							try {
								System.out.println(Thread.currentThread().getId()+":My JOB FINISH!");
								return;
							} finally {
								lock2.unlock();
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}finally {
						lock1.unlock();
					}
				}
			}
		}else{
			while(true){
				if(lock2.tryLock()){
					try {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(lock1.tryLock()){
							try {
								System.out.println(Thread.currentThread().getId()+"：my job finish!");
								return;
							} finally {
								lock1.unlock();
							}
						}
					} finally {
						// TODO: handle finally clause
						lock2.unlock();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Thread t1 =new Thread(new TryLock(1));
		Thread t2 = new Thread(new TryLock(2));
		t1.start();
		t2.start();
	}
}
