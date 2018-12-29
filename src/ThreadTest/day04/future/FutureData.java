/**
 * FutureData.java	  V1.0   2018��12��18�� ����7:22:27
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
		notifyAll();//RealData��ע�룬֪ͨgetResult;
	}

	@Override
	public synchronized String getResult() {
		while(!isReady){
			try {
				wait();//һֱ�ȴ���ֱ��RealData��ע��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realdata.result;
	}

}
