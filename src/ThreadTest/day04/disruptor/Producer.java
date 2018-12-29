/**
 * Producer.java	  V1.0   2018年12月18日 下午3:06:51
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class Producer {
	private final RingBuffer<PCData> ringBuffer;

	public Producer(RingBuffer<PCData> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}
	
	public void pushData(ByteBuffer bb){
		long sequence = ringBuffer.next();
		PCData event = ringBuffer.get(sequence);
		event.set(bb.getLong(0));
		ringBuffer.publish(sequence);
	}
}
