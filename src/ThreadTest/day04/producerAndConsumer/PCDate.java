/**
 * PCDate.java	  V1.0   2018年12月18日 下午12:12:01
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.producerAndConsumer;
/**
 * 
 * 功能描述：
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 * 
 * final修饰类，该类无法被继承
 */
public final class PCDate {
	private final int intDate;

	public PCDate(int intDate) {
		this.intDate = intDate;
	}
	
	public PCDate(String d){
		this.intDate = Integer.valueOf(d);
	}

	
	public int getIntDate() {
		return intDate;
	}

	@Override
	public String toString() {
		return "PCDate [intDate=" + intDate + "]";
	}
	
	
}
