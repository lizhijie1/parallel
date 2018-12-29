/**
 * Fitness.java	  V1.0   2018年12月24日 下午9:16:01
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
 * 功能描述：计算适应度
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
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
