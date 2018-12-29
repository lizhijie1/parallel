/**
 * PStreamMain.java	  V1.0   2018年12月18日 下午11:11:20
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.parallelMath;

public class PStreamMain {
	public static void main(String[] args) {
		new Thread(new Plus()).start();
		new Thread(new Multiply()).start();
		new Thread(new Div()).start();
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				Msg msg = new Msg();
				msg.i = i;
				msg.j = j;
				msg.orgStr = "(("+i+"+"+j+")*"+i+")/2";
				Plus.bq.add(msg);
			}
		}
	}
}
