/**
 * Div.java	  V1.0   2018年12月18日 下午11:07:21
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

public class Div implements Runnable{

	public static BlockingQueue<Msg> bq = new LinkedBlockingQueue<Msg>();
	@Override
	public void run() {
		while(true){
			try {
				Msg msg = bq.take();
				msg.i = msg.i/2;
				System.out.println(msg.orgStr+"="+msg.i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
