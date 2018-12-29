/**
 * PCDate.java	  V1.0   2018��12��18�� ����12:12:01
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.producerAndConsumer;
/**
 * 
 * ����������
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 * 
 * final�����࣬�����޷����̳�
 */
public final class PCDate {
	private final int intDate;

	public PCDate(int intDate) {
		this.intDate = intDate;
	}
	
	public PCDate(String d){
		this.intDate = Integer.valueOf(d);
	}

	
	public int getIntDate() {
		return intDate;
	}

	@Override
	public String toString() {
		return "PCDate [intDate=" + intDate + "]";
	}
	
	
}
