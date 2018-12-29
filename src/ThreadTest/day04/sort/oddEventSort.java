/**
 * oddEventSort.java	  V1.0   2018��12��19�� ����10:32:29
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
 * �������������е���ż����
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
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
