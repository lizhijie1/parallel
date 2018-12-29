/**
 * Shell.java	  V1.0   2018��12��19�� ����4:17:34
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
 * ����������ϣ������
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class Shell {
	public static int[] shellSort(int[] arr){
		//���������Hֵ
		int h = 1;
		while(h<arr.length/3){
			h = h*3+1;
		}
		while(h>0){
			System.out.println("h="+h);
			for (int i = h; i < arr.length; i++) {
				if(arr[i]<arr[i-h]){
					//Ҫ׼�������Ԫ��
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
			//�������һ��hֵ
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
