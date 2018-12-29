/**
 * Consumer.java	  V1.0   2018年12月18日 下午12:35:13
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.producerAndConsumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
	private BlockingQueue<PCDate> queue; //缓冲区
	private static final int SLEEPTIME = 1000;
	
	public Consumer(BlockingQueue<PCDate> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println("start Comsumer id="+Thread.currentThread().getId());
		Random r = new Random(); //随机等待时间
		try {
			while(true){
				PCDate data = queue.take();//提取任务
				if(null != data){
					int re = data.getIntDate()*data.getIntDate();//计算平方
					System.out.println(MessageFormat.format("{0}*{1}={2}",data.getIntDate(),data.getIntDate(),re));
					Thread.sleep(r.nextInt(SLEEPTIME));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

}
