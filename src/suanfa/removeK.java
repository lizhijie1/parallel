/**
 * removeKDigits.java	  V1.0   2018��11��12�� ����10:04:47
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
		//ɾ��k������
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
				//ɾ�����һλ
				num.substring(0, num.length()-1);
			}
			//���������������0
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
		//�������ĳ���
		int newLength = num.length() - k;
		//����һ��ջ�����ڽ�����������
		char[] stack = new char[num.length()];
		int top = 0;
		for (int i = 0; i < num.length(); i++) {
			//������ǰ����
			char c = num.charAt(i);
			//��ջ�����ִ��ڱ������ĵ�ǰ���֣�ջ�����ֳ�ջ���൱��ɾ�����֣�
			while(top>0 && stack[top-1]>c && k>0){
				top -= 1;
				k -= 1;
			}
			//�������ĵ�ǰ������ջ
			stack[top++] = c;
			System.out.println(Arrays.toString(stack));
		}
		//�ҵ�ջ�е�һ���������ֵ�λ�ã��Դ˹����µ������ַ���
		int offset = 0;
		while(offset <newLength && stack[offset] == '0'){
			offset++;
		}
		return offset == newLength?"0":new String(stack,offset,newLength-offset);
	}
}
