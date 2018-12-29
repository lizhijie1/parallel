/**
 * ZiDian.java	  V1.0   2018��11��2�� ����3:02:36
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
 * ����������
 * 	����һ����������ʵ��һ����������������������Ĵ�������ġ���λ������
	ʲô�ǻ�λ���أ�
	���ǰ�һ������������λ�����ֽ���ȫ���У��Ӷ��õ��µ�����������53241��23541��
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class ZiDian {
	//�����̣��������һ�������������ͬ������ɵ�����
	public static int[] findNearestNumber(int[] numbers){
		//������Σ�����ֱ���޸����
		int [] numbersCopy = Arrays.copyOf(numbers,numbers.length);
		//1.�Ӻ���ǰ�鿴���������ҵ����������ǰһλ��Ҳ���������û��ı߽�
		int index = findTransferPoint(numbersCopy);
		//��������û��߽���0��˵�����������Ѿ������޷��õ��������ͬ������ɵ�����
		if(index == 0){
			return null;
		}
		//2.�����������ǰһλ�����������иոմ����������ֽ���λ��
		exchangeHead(numbersCopy,index);
		//3.��ԭ������������תΪ˳��
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
	//�������
	private static void outputNumbers(int[] numbers){
		System.out.println(Arrays.toString(numbers));
	}
}
