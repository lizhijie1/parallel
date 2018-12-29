/**
 * PBestMsg.java	  V1.0   2018年12月24日 下午9:13:35
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.pso;
/**
 * 
 * 功能描述:个体最优
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public final class PBestMsg {
	final PsoValue value;

	public PBestMsg(PsoValue value) {
		this.value = value;
	}

	public PsoValue getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
	
}
