/**
 * oddEventSort.java	  V1.0   2018年12月19日 上午10:32:29
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.sort;

import java.util.Arrays;

/**
 * 
 * 功能描述：串行的奇偶排序
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class oddEventSort {
	public static int[] sort(int[] arr){
		int exchFlag = 1;
		int start = 1;
		while(exchFlag == 1 || start==1 ){
			exchFlag = 0;
			for (int i = start; i < arr.length-1; i+=2) {
				if(arr[i]>arr[i+1]){
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					exchFlag = 1;
				}
			}
			if(start == 0){
				start = 1;
			}else{
				start = 0;
			}
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = {5,52,6,3,4};
		System.out.println(Arrays.toString(oddEventSort.sort(arr)));
	}
}
