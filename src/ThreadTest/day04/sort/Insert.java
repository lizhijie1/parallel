/**
 * Insert.java	  V1.0   2018��12��19�� ����3:48:54
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
 * ������������������
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class Insert {
	public static int[] insertSort(int[] arr){
		int length = arr.length;
		int i,j,key;
		for ( i=1; i < length;i++) {
			//keyΪ����׼���Ĳ��������Ԫ��
			key = arr[i];
			j = i-1;
			while(j>=0 && arr[j]>key){
				arr[j+1] = arr[j]; 
				j--;
			}
			//�ҵ����ʵ�λ�ò���key
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
