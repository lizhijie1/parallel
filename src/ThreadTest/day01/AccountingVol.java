/**
 * AccountingVol.java	  V1.0   2018年12月15日 上午10:22:16
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day01;

public class AccountingVol implements Runnable{
	static AccountingVol instance = new AccountingVol();
	
	static volatile int i = 0;
	
	public static synchronized void increase(){
		i++;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10000000; i++) {
			increase();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AccountingVol());
		Thread t2 = new Thread(new AccountingVol());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
