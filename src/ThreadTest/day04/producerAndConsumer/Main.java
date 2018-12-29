/**
 * Main.java	  V1.0   2018��12��18�� ����12:46:12
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.producerAndConsumer;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		//����������
		BlockingQueue<PCDate> queue = new LinkedBlockingQueue<PCDate>(10);
		Producer producer1 = new Producer(queue); //����������
		Producer producer2 = new Producer(queue);
		Producer producer3 = new Producer(queue);
		Consumer consumer1 = new Consumer(queue);//����������
		Consumer consumer2 = new Consumer(queue);
		Consumer consumer3 = new Consumer(queue);
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(producer1);
		service.execute(producer2);
		service.execute(producer3);
		service.execute(consumer1);
		service.execute(consumer2);
		service.execute(consumer3);
		Thread.sleep(1000);
		producer1.stop();
		producer2.stop();
		producer3.stop();
		Thread.sleep(3000);
		service.shutdown();
	}
}
