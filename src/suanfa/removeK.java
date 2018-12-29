/**
 * removeKDigits.java	  V1.0   2018年11月12日 上午10:04:47
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package suanfa;

import java.util.Arrays;

public class removeK {
	public static void main(String[] args) {
		//System.out.println(removeKDigits("1593213",3));
		System.out.println(removeKDigits1("1593213",3));
	}
	
	public static String removeKDigits(String num,int k){
		//删掉k个数字
		for (int i = 0; i < k; i++) {
			boolean flag = false;
			for(int j=0;j<num.length()-1;j++){
				if(num.charAt(j)>num.charAt(j+1)){
					num = num.substring(0,j)+num.substring(j+1,num.length());
					flag = true;
					break;
				}
			}
			
			if(!flag){
				//删除最后一位
				num.substring(0, num.length()-1);
			}
			//清除整数左侧的数字0
			removeZero(num);
		}
		return num;
	}
	
	public static String removeZero(String num){
		for (int i = 0; i < num.length()-1; i++) {
			if(num.charAt(0) != '0'){
				break;
			}
			num = num.substring(1,num.length());
		}
		return num;
	}
	
	public static String removeKDigits1(String num,int k){
		//新整数的长度
		int newLength = num.length() - k;
		//创建一个栈，用于接收所有数字
		char[] stack = new char[num.length()];
		int top = 0;
		for (int i = 0; i < num.length(); i++) {
			//遍历当前数字
			char c = num.charAt(i);
			//当栈顶数字大于遍历到的当前数字，栈顶数字出栈（相当于删除数字）
			while(top>0 && stack[top-1]>c && k>0){
				top -= 1;
				k -= 1;
			}
			//遍历到的当前数字入栈
			stack[top++] = c;
			System.out.println(Arrays.toString(stack));
		}
		//找到栈中第一个非零数字的位置，以此构建新的整数字符串
		int offset = 0;
		while(offset <newLength && stack[offset] == '0'){
			offset++;
		}
		return offset == newLength?"0":new String(stack,offset,newLength-offset);
	}
}
