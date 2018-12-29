/**
 * Client.java	  V1.0   2018��12��18�� ����7:37:09
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.future;
/**
 * 
 * ����������futureģʽ�ļ�ʵ��
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class Client {
	public Data request(final String queryStr){
		final FutureData future = new FutureData();
		new Thread(){
			@Override
			public void run() {
				RealData realdata = new RealData(queryStr);
				future.setRealdata(realdata);
			}
		}.start();
		return future;
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		Data data = client.request("name");
		System.out.println("�������");
		try {
			//�����������һ��sleep�����˶�����ҵ����߼�����
			//�ڴ�����Щҵ���߼��Ĺ����У�RealData���������Ӷ���������˵ȴ�ʱ��
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//ʹ����ʵ����
		System.out.println("���� = "+data.getResult());
	}
}
