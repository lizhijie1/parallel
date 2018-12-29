/**
 * DaemonDemo.java	  V1.0   2018��12��15�� ����9:51:16
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day01;

public class DaemonDemo {
	public static class DaemonT extends Thread{
		@Override
		public void run() {
			while(true){
				System.out.println("I am alive");
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		DaemonT t = new DaemonT();
		//�������ػ��̣߳�Ȼ��start
		t.setDaemon(true);
		t.start();
		Thread.sleep(2000);
	}
}
