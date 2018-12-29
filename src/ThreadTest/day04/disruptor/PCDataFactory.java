/**
 * PCDataFactory.java	  V1.0   2018年12月18日 下午3:04:37
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.disruptor;

import com.lmax.disruptor.EventFactory;

public class PCDataFactory implements EventFactory<PCData> {
	@Override
	public PCData newInstance() {
		return new PCData();
	}
}
