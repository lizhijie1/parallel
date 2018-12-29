/**
 * Fitness.java	  V1.0   2018��12��24�� ����9:16:01
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.pso;

import java.util.List;

/**
 * 
 * ����������������Ӧ��
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class Fitness {
	public static double fitness(List<Double> x){
		double sum =0;
		for (int i = 1; i <x.size() ; i++) {
			sum+=Math.sqrt(x.get(i));
		}
		return sum;
	}
}
