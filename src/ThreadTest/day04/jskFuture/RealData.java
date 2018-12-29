/**
 * RealData.java	  V1.0   2018年12月18日 下午10:17:11
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.jskFuture;

import java.util.concurrent.Callable;

public class RealData implements Callable<String>{
	private String para;
	
	public RealData(String para) {
		this.para = para;
	}

	@Override
	public String call() throws Exception {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(para);
			Thread.sleep(1000);
		}
		return sb.toString();
	}

}
