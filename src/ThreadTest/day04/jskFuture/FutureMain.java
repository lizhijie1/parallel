/**
 * FutureMain.java	  V1.0   2018��12��18��zhe ����10:20:51
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.jskFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/**
 * 
 * ����������jdk��FUTURE��Ӧ�þ���
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class FutureMain {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//����FutureTask
		FutureTask<String> future = new FutureTask<String>(new RealData("a"));
		ExecutorService service = Executors.newFixedThreadPool(1);
		//�����￪���߳̽���RealData��callִ��
		service.submit(future);
		
		System.out.println(System.currentTimeMillis()+"�������");
		try {
			//������Ȼ����������������ݲ���������ʹ��sleep��������ҵ���߼��Ĵ���
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()+"���� ="+future.get());
	}
}
