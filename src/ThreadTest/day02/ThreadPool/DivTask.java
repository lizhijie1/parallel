/**
 * DivTask.java	  V1.0   2018��12��16�� ����7:26:35
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 
 * �������������̳߳���Ѱ�Ҷ�ջ
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class DivTask implements Runnable{

	private int a;
	private int b;
	
	public DivTask(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		double re =a/b;
		System.out.println(re);
	}

	
	public static void main(String[] args) {
		ExecutorService es = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0l, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>());
		for (int i = 0; i < 5; i++) {
			//es.execute(new DivTask(500,i));--���Կ����쳣��ջ��Ϣ
			es.submit(new DivTask(500,i));//�������쳣��ջ��Ϣ
		}
	}
}
