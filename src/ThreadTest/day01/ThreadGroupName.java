/**
 * ThreadGroupName.java	  V1.0   2018年12月15日 上午9:37:38
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day01;

public class ThreadGroupName implements Runnable{
	public static void main(String[] args) {
		//创建线程组
		ThreadGroup tg = new ThreadGroup("PrintGroup");
		//
		Thread t1 = new Thread(tg, new ThreadGroupName(),"t1");
		Thread t2 = new Thread(tg, new ThreadGroupName(),"t2");
		t1.start();
		t2.start();
		System.out.println(tg.activeCount());
		tg.list();
	}
	
	@Override
	public void run() {
		String groupName = Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName();
		while(true){
			System.out.println("I am "+groupName);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
