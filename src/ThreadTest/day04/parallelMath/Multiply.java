/**
 * Multiply.java	  V1.0   2018年12月18日 下午11:04:02
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

public class Multiply implements Runnable{
	public static BlockingQueue<Msg> bq = new LinkedBlockingQueue<Msg>();
	@Override
	public void run() {
		while(true){
			try {
				Msg msg = bq.take();
				msg.i = msg.i*msg.j;
				Div.bq.add(msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
