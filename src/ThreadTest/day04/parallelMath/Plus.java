/**
 * Plus.java	  V1.0   2018年12月18日 下午10:40:05
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
 * 功能描述：p1
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
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
