/**
 * LockSupportIntDemo.java	  V1.0   2018年12月15日 下午10:06:55
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02;

import java.util.concurrent.locks.LockSupport;

/**
 * 
 * 功能描述：线程阻塞工具类的使用:LockSupport
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class LockSupportIntDemo {
	public static Object u = new Object();
	public static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	public static ChangeObjectThread t2 = new ChangeObjectThread("t2");
	public static class ChangeObjectThread extends Thread{
		
		public ChangeObjectThread(String name){
			super.setName(name);
		}
		@Override
		public void run() {
			synchronized (u) {
				System.out.println("in "+getName());
				LockSupport.park();
				if(Thread.interrupted()){
					System.out.println(getName()+"被中断了");
				}
			}
			System.err.println(getName()+"执行结束");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		t1.interrupt();
		LockSupport.unpark(t2);
	}

}
