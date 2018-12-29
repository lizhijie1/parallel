/**
 * Main.java	  V1.0   2018年12月18日 下午3:14:25
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();
		PCDataFactory factory = new PCDataFactory();
		int bufferSize = 1024;
		Disruptor disruptor = new Disruptor(factory, bufferSize, executor, ProducerType.MULTI,new BlockingWaitStrategy());
		disruptor.handleEventsWithWorkerPool(new Consumer(),new Consumer(),new Consumer(),new Consumer());
		disruptor.start();
		
		RingBuffer ringBuffer = disruptor.getRingBuffer();
		Producer producer=new Producer(ringBuffer);
		ByteBuffer bb = ByteBuffer.allocate(8);
		for (long l = 0;true; l++) {
			bb.putLong(0,1);
			producer.pushData(bb);
			Thread.sleep(100);
			System.out.println("add data:"+l);
		}
	}
}
