/**
 * Client.java	  V1.0   2018年12月18日 下午7:37:09
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
 * 功能描述：future模式的简单实现
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
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
		System.out.println("请求完毕");
		try {
			//在这里可以用一个sleep代替了对其他业务的逻辑处理
			//在处理这些业务逻辑的过程中，RealData被创建，从而充分利用了等待时间
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//使用真实数据
		System.out.println("数据 = "+data.getResult());
	}
}
