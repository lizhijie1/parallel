/**
 * RealDate.java	  V1.0   2018年12月18日 下午7:24:33
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.future;

public class RealData implements Data{
	protected final String result;
	
	public RealData(String para) {
		//RealData的构造可能很慢，需要用户等待很久，这里使用sleep模拟
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(para);
			try {
				//代替很慢的操作过程
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		result = sb.toString();
	}

	@Override
	public String getResult() {
		return result;
	}

}
