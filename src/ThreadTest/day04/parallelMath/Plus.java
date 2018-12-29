/**
 * Plus.java	  V1.0   2018��12��18�� ����10:40:05
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.parallelMath;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * 
 * ����������p1
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class Plus implements Runnable{
	public static BlockingQueue<Msg> bq = new LinkedBlockingQueue<Msg>();
	
	@Override
	public void run() {
		while(true){
			try {
				Msg msg = bq.take();
				msg.j = msg.i+msg.j;
				Multiply.bq.add(msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
