/**
 * Insert.java	  V1.0   2018年12月19日 下午3:48:54
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
 * 功能描述：插入排序
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class Insert {
	public static int[] insertSort(int[] arr){
		int length = arr.length;
		int i,j,key;
		for ( i=1; i < length;i++) {
			//key为正在准备的插入的数组元素
			key = arr[i];
			j = i-1;
			while(j>=0 && arr[j]>key){
				arr[j+1] = arr[j]; 
				j--;
			}
			//找到合适的位置插入key
			arr[j+1] = key;
			System.out.println("i="+i+"....."+Arrays.toString(arr));
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = {5,52,6,3,4};
		System.out.println(Arrays.toString(insertSort(arr)));
	}
}
