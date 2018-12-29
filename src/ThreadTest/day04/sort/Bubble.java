/**
 * Order.java	  V1.0   2018年12月19日 上午10:09:22
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
 * 功能描述：冒泡排序
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class Bubble {
	public static int[] bubbleSort(int[] arr){
		//
		for (int i = arr.length-1; i>0; i--) {
			for (int j = 0; j < i; j++) {
				if(arr[j]>arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
			System.out.println(Arrays.toString(arr));
		}
		return arr;
	}
	public static void main(String[] args) {
		int[] arr = {5,52,6,3,4};
		System.out.println(Arrays.toString(bubbleSort(arr)));
	}
}