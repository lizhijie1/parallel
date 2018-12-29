/**
 * Consumer.java	  V1.0   2018年12月18日 下午2:59:14
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.disruptor;

import com.lmax.disruptor.WorkHandler;

public class Consumer implements WorkHandler<PCData>{

	@Override
	public void onEvent(PCData event) throws Exception {
		System.out.println(Thread.currentThread().getId()+":Event:--"+event.get()*event.get()+"--");
	}
}
