/**
 * FutureData.java	  V1.0   2018年12月18日 下午7:22:27
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.future;

public class FutureData implements Data{
	protected RealData realdata = null;
	
	protected boolean isReady = false;

	public synchronized void setRealdata(RealData realdata) {
		if(isReady){
			return;
		}
		this.realdata = realdata;
		isReady = true;
		notifyAll();//RealData被注入，通知getResult;
	}

	@Override
	public synchronized String getResult() {
		while(!isReady){
			try {
				wait();//一直等待，直到RealData被注入
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realdata.result;
	}

}
