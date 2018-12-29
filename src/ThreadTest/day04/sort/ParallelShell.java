/**
 * ParallelShell.java	  V1.0   2018年12月19日 下午5:12:54
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.sort;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 功能描述：并行排序--希尔排序
 * BUG:目前存在问题，并不能很好的处理并发
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class ParallelShell {
	static int[] arr = {58,25,14,36,25,14,25,47,58,92,102,25,14,34,65,43,67,20,50,90,101,20,25};
	
	public static synchronized int[] getArr() {
		return arr;
	}

	public static synchronized void setArr(int[] arr) {
		ParallelShell.arr = arr;
	}

	public static class ShellSortTask implements Runnable{
		int i = 0;
		int h = 0;
		CountDownLatch l;
		
		public ShellSortTask(int i, int h, CountDownLatch l) {
			super();
			this.i = i;
			this.h = h;
			this.l = l;
		}

		@Override
		public void run() {
			if(arr[i]<arr[i-h]){
				int tmp = arr[i];
				int j =i-h;
				if(j>=0 && arr[j]>tmp){
					arr[j+h] = tmp;
					j-= h;
				}
				arr[j+h] = tmp;
			}
			System.out.println("i="+i+".........."+Arrays.toString(arr));
			l.countDown();
		}
	}
	
	public static void pShellSort(int[] arr) throws InterruptedException{
		//
		ExecutorService pool = Executors.newFixedThreadPool(2);
		//计算出最大的h值
		int h = 1;
		CountDownLatch latch = null;
		while(h<arr.length/3){
			h = h*3+1;
		}
		while(h>0){
			System.out.println("h="+h);
			if(h>=4){
				latch = new CountDownLatch(arr.length-h);
			}
			for (int i = h; i < arr.length; i++) {
				//控制线程数量
				if(h>=4){
					pool.execute(new ShellSortTask(i, h, latch));
				}else{
					if(arr[i]<arr[i-h]){
						int tmp = arr[i];
						int j = i-h;
						if(j>=0 && arr[j]>tmp){
							arr[j+h] =arr[j];
							j-=h;
						}
						arr[j+h] = tmp;
					}
				}
			}
			latch.await();
			System.out.println(Arrays.toString(arr));
			//计算出下一个h值
			h = (h-1)/3;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		pShellSort(ParallelShell.getArr());
		System.out.println(Arrays.toString(arr));
	}
}
