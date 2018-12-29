/**
 * parallelOddEventSort.java	  V1.0   2018年12月19日 上午11:16:17
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
 * 功能描述：并行排序--奇偶排序
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class parallelOddEventSort {
	static int[] arr = {5,52,6,3,4};
	
	static int exchFlag =1;

	public static synchronized int getExchFlag() {
		return exchFlag;
	}

	public static synchronized void setExchFlag(int exchFlag) {
		parallelOddEventSort.exchFlag = exchFlag;
	}
	
	
	public static int[] getArr() {
		return arr;
	}

	
	public static class OddEventSortTask implements Runnable{
		int i;
		CountDownLatch latch;
		
		public OddEventSortTask(int i, CountDownLatch latch) {
			super();
			this.i = i;
			this.latch = latch;
		}

		@Override
		public void run() {
			if(arr[i]>arr[i+1]){
				int temp = arr[i];
				arr[i] = arr[i+1];
				arr[i+1] = temp;
				setExchFlag(1);
			} 
			latch.countDown();
		}
		
	}
	
	public static int[] pOddEventSort(int[] arr) throws InterruptedException{
		int start = 0;
		ExecutorService pool = Executors.newFixedThreadPool(1);
		while(getExchFlag() == 1 || start ==1){
			setExchFlag(0);
			//偶数的数组长度，当start为1时，只有len/2-1个线程
			CountDownLatch latch = new CountDownLatch(arr.length/2-(arr.length%2==0?start:0));
			for (int i = start; i < arr.length-1; i++) {
				pool.submit(new OddEventSortTask(i,latch));
			}
			//等待所有线程结束
			latch.await();
			if(start == 0){
				start = 1;
			}else{
				start = 0;
			}
		}
		return arr;
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Arrays.toString(pOddEventSort(arr)));
	}
}
