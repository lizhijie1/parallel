/**
 * IntLock.java	  V1.0   2018年12月15日 上午11:33:01
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
 * 功能描述：重入锁：中断响应
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class IntLock implements Runnable{
	public static ReentrantLock lock1 = new ReentrantLock();
	
	public static ReentrantLock lock2 = new ReentrantLock();
	
	int lock;
	
	public IntLock(int lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			if(lock == 1){
				//对锁请求，lockInterruptobly是一个可以对中断进行响应的锁申请动作
				lock1.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
				lock2.lockInterruptibly();
			}else{
				lock2.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
				lock1.lockInterruptibly();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(lock1.isHeldByCurrentThread()){
				lock1.unlock();
			}
			if(lock2.isHeldByCurrentThread()){
				lock2.unlock();
			}
			System.out.println(Thread.currentThread().getId()+":线程退出！");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		IntLock r1 = new IntLock(1);
		IntLock r2 = new IntLock(2);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		Thread.sleep(1000);
		t2.interrupt();
	}
}
