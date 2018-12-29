/**
 * RealDate.java	  V1.0   2018��12��18�� ����7:24:33
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
		//RealData�Ĺ�����ܺ�������Ҫ�û��ȴ��ܾã�����ʹ��sleepģ��
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(para);
			try {
				//��������Ĳ�������
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
