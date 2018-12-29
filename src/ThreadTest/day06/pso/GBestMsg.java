/**
 * GBestMsg.java	  V1.0   2018年12月24日 下午9:03:34
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.pso;
/***
 * 
 * 功能描述：全局最优
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */

public final class GBestMsg {
	final PsoValue value;

	public GBestMsg(PsoValue value) {
		this.value = value;
	}

	public PsoValue getValue() {
		return value;
	}
}
