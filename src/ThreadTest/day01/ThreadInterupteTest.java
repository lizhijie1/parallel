/**
 * ThreadInterupteTest.java	  V1.0   2018年12月14日 下午11:07:06
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day01;

public class ThreadInterupteTest {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(){
			@Override
			public void run() {
				while(true){
					if(Thread.currentThread().isInterrupted()){
						System.out.println("exit thread by Interrupte");
						break;
					}
					try {
						Thread.sleep(2000);//会清除中断标志
					} catch (InterruptedException e) {
						System.out.println("Interrupte when Interrupte");
						Thread.currentThread().interrupt();
					}
					Thread.yield();
				}
			}
		};
		t1.start();
		Thread.sleep(2000);
		t1.interrupt();
	}
}
