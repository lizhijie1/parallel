/**
 * GBestMsg.java	  V1.0   2018��12��24�� ����9:03:34
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.pso;
/***
 * 
 * ����������ȫ������
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */

public final class GBestMsg {
	final PsoValue value;

	public GBestMsg(PsoValue value) {
		this.value = value;
	}

	public PsoValue getValue() {
		return value;
	}
}
