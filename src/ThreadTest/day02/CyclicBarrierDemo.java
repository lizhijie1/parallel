/**
 * CyclicBarrierDemo.java	  V1.0   2018年12月15日 下午9:14:18
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	public static class Soldier implements Runnable{
		private String soldier;
		private final CyclicBarrier cyclic;
		
		public Soldier(String soldier, CyclicBarrier cyclic) {
			this.soldier = soldier;
			this.cyclic = cyclic;
		}
		
		@Override
		public void run() {
			try {
				//等待所有士兵集合
				cyclic.await();
				doWork();
				//等待所有士兵完成任务
				cyclic.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
		
		void doWork(){
			try {
				Thread.sleep(Math.abs(new Random().nextInt()%10000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(soldier+"：任务完成!");
		}
	}
	
	public static class BarrierRun implements Runnable{
		boolean flag;
		int N;
		 
		public BarrierRun(boolean flag, int n) {
			this.flag = flag;
			N = n;
		}

		@Override
		public void run() {
			if(flag){
				System.out.println("司令:[士兵"+N+"个，任务完成! ]");
			}else{
				System.out.println("司令:[士兵"+N+"个，集合完毕！]");
				flag = true;
			}
		}
	}
	
	public static void main(String[] args) {
		final int N = 10;
		Thread[] allSoldier = new Thread[10];
		boolean flag = false;
		CyclicBarrier cyclic = new CyclicBarrier(N,new BarrierRun(flag,N));
		System.out.println("集合队伍！！！");
		for (int i = 0; i < allSoldier.length; i++) {
			System.out.println("士兵"+i+"报道！");
			allSoldier[i] = new Thread(new Soldier("士兵 "+i,cyclic));
			allSoldier[i].start();
			/*if(i==5){
				allSoldier[i].interrupt();
			}*/
		}
	}
}
