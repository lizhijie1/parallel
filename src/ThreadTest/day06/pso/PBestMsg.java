/**
 * PBestMsg.java	  V1.0   2018��12��24�� ����9:13:35
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.pso;
/**
 * 
 * ��������:��������
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public final class PBestMsg {
	final PsoValue value;

	public PBestMsg(PsoValue value) {
		this.value = value;
	}

	public PsoValue getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
	
}
