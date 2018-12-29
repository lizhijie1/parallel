/**
 * Producer.java	  V1.0   2018��12��18�� ����12:08:41
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.producerAndConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{

	private volatile boolean isRunning = true;
	private BlockingQueue<PCDate> queue;  //�ڴ滺����
	private static AtomicInteger count = new AtomicInteger();//������ԭ�Ӳ���
	private static final int SLEEPTIME = 1000;
	
	public Producer(BlockingQueue<PCDate> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		PCDate data = null;
		Random r = new Random();
		System.out.println("start producer id"+Thread.currentThread().getId());
		try {
			while(isRunning){
				Thread.sleep(r.nextInt(SLEEPTIME));
				data = new PCDate(count.incrementAndGet());//����������
				System.out.println(data+"is put into queue");
				if(!queue.offer(data,2,TimeUnit.SECONDS)){//�ύ�����ݻ�����
					System.err.println("fail to put data:"+data);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	public void stop(){
		isRunning = false;
	}

}
