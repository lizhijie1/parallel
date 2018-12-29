/**
 * Shell.java	  V1.0   2018年12月19日 下午4:17:34
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
 * 功能描述：希尔排序
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class Shell {
	public static int[] shellSort(int[] arr){
		//计算出最大的H值
		int h = 1;
		while(h<arr.length/3){
			h = h*3+1;
		}
		while(h>0){
			System.out.println("h="+h);
			for (int i = h; i < arr.length; i++) {
				if(arr[i]<arr[i-h]){
					//要准备插入的元素
					int tmp = arr[i];
					int j = i-h;
					while(j>=0 && arr[j]>tmp){
						arr[j+h] = arr[j];
						j-=h;
					}
					arr[j+h] = tmp;
				}
				System.out.println("i="+i+"........"+Arrays.toString(arr));
			}
			//计算出下一个h值
			h = (h-1)/3;
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = {58,25,14,36,25,14,25,47,58,92,102,25,14,34,65,43,67,20,50,90,101,20,25};
		System.out.println(arr.length);
		System.out.println(Arrays.toString(shellSort(arr)));
	}
}
