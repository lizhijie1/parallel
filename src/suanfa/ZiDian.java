/**
 * ZiDian.java	  V1.0   2018年11月2日 下午3:02:36
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package suanfa;

import java.util.Arrays;

/**
 * 
 * 功能描述：
 * 	给定一个正整数，实现一个方法来求出离该整数最近的大于自身的“换位数”。
	什么是换位数呢？
	就是把一个整数各个数位的数字进行全排列，从而得到新的整数。例如53241和23541。
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class ZiDian {
	//主流程，返回最近一个大于自身的相同数字组成的整数
	public static int[] findNearestNumber(int[] numbers){
		//拷贝入参，避免直接修改入参
		int [] numbersCopy = Arrays.copyOf(numbers,numbers.length);
		//1.从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
		int index = findTransferPoint(numbersCopy);
		//如果数字置换边界是0，说明整个数组已经逆序，无法得到更大的相同数字组成的整数
		if(index == 0){
			return null;
		}
		//2.把逆序区域的前一位和逆序区域中刚刚大于他的数字交换位置
		exchangeHead(numbersCopy,index);
		//3.把原来的逆序区域转为顺序
		reverse(numbers,index);
		return numbersCopy;
	}
	
	private static int findTransferPoint(int[] numbers){
		for(int i=numbers.length-1;i>0;i--){
			if(numbers[i]>numbers[i-1]){
				return i;
			}
		}
		return 0;
	}
	
	private static int [] exchangeHead(int[] numbers,int index){
		int head = numbers[index-1];
		for(int i=numbers.length-1;i>0;i--){
			if(head<numbers[i]){
				numbers[index-1] =numbers[i];
				numbers[i] = head;
				break;
			}
		}
		return numbers;
	}
	
	private static int[] reverse(int[] num,int index){
		for(int i=index,j=num.length-1;i<j;i++,j--){
			int temp = num[i];
			num[i] = num[j];
			num[j] = temp;
		}
		return num;
	}
	
	public static void main(String[] args) {
		int[] numbers = {1,2,3,4,5};
		for(int i=0;i<10;i++){
			numbers = findNearestNumber(numbers);
			outputNumbers(numbers);
		}
	}
	//输出数组
	private static void outputNumbers(int[] numbers){
		System.out.println(Arrays.toString(numbers));
	}
}
